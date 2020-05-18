package com.example.wheaterapp2

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.view.*
import kotlinx.android.synthetic.main.cardview.view.*
import kotlinx.android.synthetic.main.cardview.view.location
import kotlinx.android.synthetic.main.cardview.view.share
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TemperatureAdapter(val context: Context, val temperatures:List<Temperature>) : RecyclerView.Adapter<TemperatureAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperatureAdapter.MyViewHolder {
        val view  = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return temperatures.size
    }

    override fun onBindViewHolder(holder: TemperatureAdapter.MyViewHolder, position: Int) {
        val temperature = temperatures[position]
        holder.setData(temperature,position);
    }
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private var currentpostion: Int =0
        init{
            itemView.share.setOnClickListener {
                val msg ="Zipcode : ${itemView.secondzipcode},\n Country Code :${itemView.secondcountryCode}, \n Temperature${itemView.temp}"
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT,msg)
                intent.type = "text/plain"
                context.startActivity(Intent.createChooser(intent,"Share to"))
            }
        }
        fun setData(temperature:Temperature?,pos:Int){
            itemView.location.text = temperature!!.location
            itemView.temperature.text = temperature!!.countryCode
            val zipcode = "${itemView.location.text},${itemView.temperature.text}"
            val network = WeatherNetworkClient(context)
            val call = network.getWeatherForZipCode(zipcode)
            call.enqueue(object: Callback<Weather> {
                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    t?.printStackTrace()
                }

                override fun onResponse(call: Call<Weather>, response: Response<Weather>) =
                    if(response != null) {
                        val weather: Weather? = response.body()
                        val main = weather?.main
                    itemView.temp.text = " ${main?.temp}℉"
                    }
                    else{

                    }
            })
            this.currentpostion= pos
        }

    }


}