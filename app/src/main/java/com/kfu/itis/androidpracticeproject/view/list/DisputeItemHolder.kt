package com.kfu.itis.androidpracticeproject.view.list

import android.graphics.Color
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
            if (dispute.description.length > 40) {
                val desc = dispute.description.substring(0, 40) + "..."
                tv_description.text = desc
            } else tv_description.text = dispute.description
            tv_tag.text = dispute.tag
            tv_tag.setBackgroundColor(getTagColor(dispute.tag))
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

    private fun getTagColor(tag: String): Int {
        when (tag) {
            "Cars" -> return Color.parseColor("#C71585")
            "Geographic" -> return Color.parseColor("#00FF00")
            "Love" -> return Color.parseColor("#FF0000")
            "Politic" -> return Color.parseColor("#0000CD")
            "Sport" -> return Color.parseColor("#800080")
            "Cinema" -> return Color.parseColor("#00CED1")
            "Art" -> return Color.parseColor("#FF8C00")
        }
        return 0
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
