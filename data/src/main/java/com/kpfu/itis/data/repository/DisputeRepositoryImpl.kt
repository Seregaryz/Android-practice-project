package com.kpfu.itis.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.database.*
import com.kfu.itis.domain.model.dispute.Dispute
import com.kfu.itis.domain.reposirory.DisputeRepository
import com.kfu.itis.domain.reposirory.UserRepository
import com.kpfu.itis.core_db.dao.DisputeDAO
import com.kpfu.itis.core_db.model.DisputeLocal
import com.kpfu.itis.data.mappers.DisputeMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject


class DisputeRepositoryImpl @Inject constructor(
    private val disputeDAO: DisputeDAO,
    private val userRepository: UserRepository,
    private val database: FirebaseDatabase
) : DisputeRepository {

    private var myRef = database.getReference("dispute")
    private var listRef = database.getReference("dispute").child("list")

    override fun getDisputes(): Observable<List<Dispute>> {
        return disputeDAO.getDisputes().map { t -> t.toList() }
            .flatMap { list ->
                Observable.fromIterable(list)
                    .map { item -> DisputeMapper.toDispute(item) }
                    .toList()
                    .toObservable()
            }
    }

    override fun getDisputesFromFb(): Observable<List<Dispute>> {
        val query = myRef
        val indicator = object :
            GenericTypeIndicator<Map<String, @kotlin.jvm.JvmSuppressWildcards DisputeLocal>>() {}
        return RxFirebaseDatabase.observeSingleValueEvent(query)
            .toObservable()
            .flatMap { list ->
                Observable.fromIterable(list.getValue(indicator)?.values)
                    .map { item -> DisputeMapper.toDispute(item) }
                    .toList()
                    .toObservable()
            }
    }

    override fun createDisputeInLocalBd(
        id: String, title: String, description1: String,
        description2: String, disputeType: String
    ): Completable {
        val currentUserId = userRepository.getCurrentUserId()
        val descriptions = description1 + "_" + description2
        val disputeLocal = DisputeLocal(
            id, currentUserId, title, descriptions,
            disputeType, CREATING_COUNT, CREATING_COUNT, CREATING_STATUS
        )
        return disputeDAO.insertDispute(disputeLocal)
    }

    override fun createDisputeInFirebase(
        title: String, description1: String,
        description2: String, disputeType: String
    ): String {
        val currentUserId = userRepository.getCurrentUserId()
        val descriptions = description1 + "_" + description2
        val key = listRef.push().key ?: "null"
        myRef.child(key)
            .setValue(
                DisputeLocal(
                    key, currentUserId, title, descriptions,
                    disputeType, CREATING_COUNT, CREATING_COUNT, CREATING_STATUS
                )
            )
        return key
    }

    override fun updateDispute(dispute: Dispute, key: String): Completable {
        var isSuccess = true
        var errorMessage = ""
        var count = 0
        //Не обновляются данные для локальной бд
        var updatedDisputeLocal = DisputeMapper.toDisputeLocal(dispute)
        val query = myRef
        var countQuery = myRef
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val indicator = object :
                    GenericTypeIndicator<Map<String, @kotlin.jvm.JvmSuppressWildcards DisputeLocal>>() {}
                val disputeSnapshotFromFb = dataSnapshot.getValue(indicator)
                val disputeFromFb = disputeSnapshotFromFb!![updatedDisputeLocal.id] ?: error("")
                Log.d(TAG, "Value is: $disputeFromFb")
                if (disputeFromFb.firstPosVoicesCount < 50 && disputeFromFb.secondPosVoicesCount < 50) {
                    when (key) {
                        IS_FIRST_PLUS_KEY -> {
                            count = disputeFromFb.firstPosVoicesCount.plus(1)
                            updatedDisputeLocal.firstPosVoicesCount.plus(1)
                            Log.e(TAG, updatedDisputeLocal.firstPosVoicesCount.toString())
                            countQuery =
                                query.child(updatedDisputeLocal.id).child("firstPosVoicesCount")
                            countQuery.setValue(count)
                        }
                        IS_FIRST_MINUS_KEY -> {
                            count = disputeFromFb.firstPosVoicesCount.minus(1)
                            updatedDisputeLocal.firstPosVoicesCount.minus(1)
                            Log.e(TAG, updatedDisputeLocal.firstPosVoicesCount.toString())
                            countQuery =
                                query.child(updatedDisputeLocal.id).child("firstPosVoicesCount")
                            countQuery.setValue(count)
                        }
                        IS_SECOND_PLUS_KEY -> {
                            count = disputeFromFb.secondPosVoicesCount.plus(1)
                            updatedDisputeLocal.secondPosVoicesCount.plus(1)
                            countQuery =
                                query.child(updatedDisputeLocal.id).child("secondPosVoicesCount")
                            countQuery.setValue(count)
                        }
                        IS_SECOND_MINUS_KEY -> {
                            count = disputeFromFb.secondPosVoicesCount.minus(1)
                            updatedDisputeLocal.secondPosVoicesCount.minus(1)
                            countQuery =
                                query.child(updatedDisputeLocal.id).child("secondPosVoicesCount")
                            countQuery.setValue(count)
                        }
                    }
                    if (disputeFromFb.secondPosVoicesCount >= 50 || disputeFromFb.firstPosVoicesCount >= 50) {
                        val isFinishedQuery = query.child("finished")
                        isFinishedQuery.setValue(true)
                        disputeFromFb.isFinished = true
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                isSuccess = false
                errorMessage = error.message
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        return if (isSuccess) {
            Log.e(TAG, updatedDisputeLocal.firstPosVoicesCount.toString() + " in local bd")
            disputeDAO.updateDispute(updatedDisputeLocal)
        } else Completable.error(Throwable(errorMessage))
    }

    override fun getDisputeFromDb(id: String): Observable<Dispute> {
        return disputeDAO.getDispute(id).map { item -> DisputeMapper.toDispute(item) }
    }

    override fun getDisputeFromFb(id: String): Observable<Dispute> {
        val query = myRef.child(id)
        return RxFirebaseDatabase.observeSingleValueEvent(query, DisputeLocal::class.java)
            .toObservable()
            .map { item -> DisputeMapper.toDispute(item) }
        //        query.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val dispute = dataSnapshot.value as Observable<*>
//                Log.d(TAG, "Value is: $dispute")
//                return dispute
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Failed to read value
//
//                Log.w(TAG, "Failed to read value.", error.toException())
//            }
//        })
    }

    companion object {
        const val CREATING_COUNT = 0
        const val CREATING_STATUS = false
        const val IS_FIRST_PLUS_KEY = "first plus"
        const val IS_FIRST_MINUS_KEY = "first minus"
        const val IS_SECOND_PLUS_KEY = "second plus"
        const val IS_SECOND_MINUS_KEY = "second minus"
    }

}
