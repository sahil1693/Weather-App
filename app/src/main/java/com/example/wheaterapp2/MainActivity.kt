package com.example.wheaterapp2

import android.content.Intent
import android.icu.util.MeasureUnit.DEGREE
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSearch.setOnClickListener{
            val code = "${zipcode.text},${countryCode.text}"
           getRequest(code)
        }

        btnRefresh.setOnClickListener{
            val code = "${zipcode.text},${countryCode.text}"
            getRequest(code)
        }
        Share.setOnClickListener {
           shareDataToNextActivity()
        }
        Settings.setOnClickListener {
            goToSettings()
        }

    }
    private fun shareDataToNextActivity(){
        val msg ="Zipcode : ${zipcode.text}, \nCurrent Temperature : ${currentTemp.text},\n MinTemperature : ${lowTemp.text},\n MaxTemperature : ${highTemp.text}"
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,msg)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent,"Share to"))
    }
    private fun goToSettings(){
        val intent  = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }
    private fun getRequest(zipcode:String){
        val network = WeatherNetworkClient(applicationContext)
        val call = network.getWeatherForZipCode(zipcode)
        call.enqueue(object: Callback<Weather>{
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) =
                if(response != null) {
                    val weather: Weather? = response.body()
                    val main = weather?.main
                    Log.i("MainActivity1", "${main?.temp}")
                    lowTemp.text = "Low ${main?.minTemp}℉"
                    highTemp.text = "High ${main?.maxTemp}℉"
                    println(response)
                    currentTemp.text = " ${main?.temp}℉"
                }
                else{
                   // Toast.makeText(this,"Please enter the valid zipcode and country code",Toast.LENGTH_SHORT).show()
                }
        })
    }
}
