package com.cbellmont.ejemplodescargainternet

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class GetAllPlanets {
    companion object {
        suspend fun send(mainActivity : MainActivityInterface?) {

            val client = OkHttpClient()
            val url = "https://swapi.dev/api/planets/"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Log.e("GetAllPlanets", call.toString())

                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val bodyInString = response.body?.string()
                        bodyInString?.let {
                            Log.w("GetAllPlanets", bodyInString)
                            val JsonObject = JSONObject(bodyInString)

                            val results = JsonObject.optJSONArray("results")
                            results?.let {
                                Log.w("GetAllPlanets", results.toString())
                                val gson = Gson()

                                val itemType = object : TypeToken<List<Planet>>() {}.type

                                val list = gson.fromJson<List<Planet>>(results.toString(), itemType)

                                mainActivity?.onPlanetsReceived(list)
                            }
                        }
                    }
                }
            })
        }
    }
}