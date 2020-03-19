package com.kfu.itis.view_model.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class DisputeItemHolder(
    override val containerView: View,
    private val clickLambda: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(weather: Weather) {
        containerView.apply {
            tv_city.text = weather.name
            val service = WeatherDataSetService()
            if (weather.main.temp.toInt() > 0) tv_temperature.text =
                "+" + weather.main.temp.toInt().toString()
            else tv_temperature.text = weather.main.temp.toInt().toString()
            tv_temperature.setTextColor(
                ContextCompat.getColor(
                    context,
                    service.setTempColor(weather.main.temp)
                )
            )
            itemView.setOnClickListener {
                clickLambda(weather.id)
            }
        }
    }

    fun updateFromBundle(bundle: Bundle?) {
        containerView.apply {
            bundle?.apply {
                tv_city.text = getString(KEY_CITY)
                val service =
                    WeatherDataSetService()
                tv_temperature.setTextColor(service.setTempColor(getDouble(KEY_TEMP)))
                tv_temperature.text = getDouble(KEY_TEMP).toInt().toString()
            }
        }
    }

    companion object {
        const val KEY_CITY = "city"
        const val KEY_TEMP = "temp"
        const val KEY_CLOUDS = "clouds"
        const val KEY_WIND = "wind"
        const val KEY_FEELS_LIKE = "feels like"

        fun create(parent: ViewGroup, clickLambda: (Int) -> Unit) =
            WeatherItemHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false),
                clickLambda
            )
    }
}
