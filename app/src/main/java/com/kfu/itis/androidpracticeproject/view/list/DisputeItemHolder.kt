package com.kfu.itis.androidpracticeproject.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.domain.model.dispute.Dispute
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.disputes_item.view.*

class DisputeItemHolder(
    override val containerView: View,
    private val clickLambda: (String) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(dispute: Dispute) {
        containerView.apply {
            tv_title.text = dispute.title
            tv_description.text = dispute.descriptions
            itemView.setOnClickListener {
                clickLambda(dispute.id)
            }
        }
    }

    fun updateFromBundle(bundle: Bundle?) {
        containerView.apply {
            bundle?.apply {
                tv_title.text = getString(KEY_TITLE)
            }
        }
    }

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_CREATOR = "creator"

        fun create(parent: ViewGroup, clickLambda: (String) -> Unit) =
            DisputeItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.disputes_item, parent, false),
                clickLambda
            )
    }
}
