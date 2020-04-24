package com.kfu.itis.androidpracticeproject.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kfu.itis.androidpracticeproject.R
import com.kfu.itis.domain.model.dispute.Dispute
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dispute_item.view.*

class DisputeItemHolder(
    override val containerView: View,
    private val clickLambda: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(dispute: Dispute) {
        containerView.apply {
            tv_title.text = dispute.title
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
        const val KEY_CLOUDS = "clouds"
        const val KEY_WIND = "wind"
        const val KEY_FEELS_LIKE = "feels like"

        fun create(parent: ViewGroup, clickLambda: (Int) -> Unit) =
            DisputeItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.dispute_item, parent, false),
                clickLambda
            )
    }
}
