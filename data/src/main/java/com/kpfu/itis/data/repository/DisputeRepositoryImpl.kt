package com.kpfu.itis.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
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

    override fun updateDispute(dispute: Dispute): Completable {
        val updatedDisputeLocal = DisputeMapper.toDisputeLocal(dispute)
        val query = myRef.child(dispute.id)
        query.setValue(dispute)
        return disputeDAO.updateDispute(updatedDisputeLocal)
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
    }

}
