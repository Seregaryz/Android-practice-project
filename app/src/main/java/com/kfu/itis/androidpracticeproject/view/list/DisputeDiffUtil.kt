package com.kfu.itis.androidpracticeproject.view.list

import androidx.recyclerview.widget.DiffUtil
import com.kfu.itis.domain.model.dispute.Dispute

class DisputeDiffUtil(
    private var oldItems: List<Dispute>,
    private var newItems: List<Dispute>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].title == newItems[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}