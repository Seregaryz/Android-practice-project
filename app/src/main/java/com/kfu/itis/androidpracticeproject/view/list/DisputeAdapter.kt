package com.kfu.itis.androidpracticeproject.view.list

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kfu.itis.androidpracticeproject.presentation.list.DisputeItemHolder.Companion.KEY_CREATOR
import com.kfu.itis.androidpracticeproject.presentation.list.DisputeItemHolder.Companion.KEY_TITLE
import com.kfu.itis.domain.model.dispute.Dispute

class DisputeAdapter(
    var dataSource: List<Dispute>,
    var clickLambda: (Int) -> Unit
) : ListAdapter<Dispute, DisputeItemHolder>(object : DiffUtil.ItemCallback<Dispute>() {

    override fun areItemsTheSame(oldItem: Dispute, newItem: Dispute): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Dispute, newItem: Dispute): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: Dispute, newItem: Dispute): Any? {
        val diffBundle = Bundle()
        if (oldItem.title !== newItem.title) {
            diffBundle.putString(KEY_TITLE, newItem.title)
        }
        if (oldItem.creator.username != newItem.creator.username) {
            diffBundle.putString(KEY_CREATOR, newItem.creator.username)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }
}) {

    override fun onBindViewHolder(holder: DisputeItemHolder, position: Int) =
        holder.bind(dataSource[position])

    override fun getItemCount(): Int = dataSource.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisputeItemHolder =
        DisputeItemHolder.create(
            parent,
            clickLambda
        )

    override fun onBindViewHolder(
        holder: DisputeItemHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads[0] as? Bundle
            holder.updateFromBundle(bundle)
        }
    }

    private fun updateList(newList: List<Dispute>) {
        val result = DiffUtil.calculateDiff(
            DisputeDiffUtil(
                dataSource,
                newList
            ), true
        )
        result.dispatchUpdatesTo(this)
        val temp = dataSource.toMutableList()
        temp.clear()
        temp.addAll(newList)
        dataSource = temp.toList()
    }
}
