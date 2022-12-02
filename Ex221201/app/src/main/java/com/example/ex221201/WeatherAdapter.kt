package com.example.ex221201

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(val context:Context,val weatherList:ArrayList<WeatherVO>):RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val tvCity : TextView
        val tvState : TextView
        val tvTemp: TextView
        val tvHumidity : TextView
        val tvSpeed : TextView
        val tvSunrise : TextView
        val tvSunset : TextView

        init{
            tvCity = itemView.findViewById(R.id.tvCity)
            tvState = itemView.findViewById(R.id.tvState)
            tvTemp = itemView.findViewById(R.id.tvTemp)
            tvHumidity = itemView.findViewById(R.id.tvHumidity)
            tvSpeed = itemView.findViewById(R.id.tvSpeed)
            tvSunrise = itemView.findViewById(R.id.tvSunrise)
            tvSunset = itemView.findViewById(R.id.tvSunset)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.weather_list, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCity.setText("도시 : "+weatherList.get(position).city)
        holder.tvState.setText("상태 : "+weatherList.get(position).State)
        holder.tvTemp.setText("온도 : "+weatherList.get(position).temp+"도")
        holder.tvHumidity.setText("습도 : "+weatherList.get(position).humidity+"%")
        holder.tvSpeed.setText("풍속 : "+weatherList.get(position).speed+"/ms")
        holder.tvSunrise.setText("일출 : "+weatherList.get(position).sunrise)
        holder.tvSunset.setText("일몰 : "+weatherList.get(position).sunset)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

}