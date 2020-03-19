package com.kfu.itis.view_model.list

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil

class DisputeAdapter(
    var dataSource: List<>,
    var clickLambda: (Int) -> Unit
) : ListAdapter<Weather, DisputeItemHolder>(object : DiffUtil.ItemCallback<Dispute>() {

    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: Weather, newItem: Weather): Any? {
        val diffBundle = Bundle()
        if (oldItem.name !== newItem.name) {
            diffBundle.putString(KEY_CITY, newItem.name)
        }
        if (oldItem.main.temp != newItem.main.temp) {
            diffBundle.putString(KEY_TEMP, newItem.main.temp.toString())
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }
}) {

    override fun onBindViewHolder(holder: DisputeItemHolder, position: Int) =
        holder.bind(dataSource[position])

    override fun getItemCount(): Int = dataSource.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemHolder =
        WeatherItemHolder.create(
            parent,
            clickLambda
        )

    override fun onBindViewHolder(
        holder: WeatherItemHolder,
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

    private fun updateList(newList: List<Weather>) {
        val result = DiffUtil.calculateDiff(
            WeatherDiffUtil(
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
