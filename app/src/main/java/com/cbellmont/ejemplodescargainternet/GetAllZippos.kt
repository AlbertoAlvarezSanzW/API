package com.cbellmont.ejemplodescargainternet


import kotlinx.coroutines.launch
import okhttp3.*
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import java.io.IOException

class GetAllZippopotamus {
    companion object {
        suspend fun send(mainActivity : MainActivityInterface?) {

            val client = OkHttpClient()
            val url = "http://api.zippopotam.us/"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Log.e("GetAllZippopotamus", call.toString())

                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val bodyInString = response.body?.string()
                        bodyInString?.let {
                            Log.w("GetAllZippopotamus", bodyInString)
                            val JsonObject = JSONObject(bodyInString)

                            val results = JsonObject.optJSONArray("results")
                            results?.let {
                                Log.w("GetAllZippopotamus", results.toString())
                                val gson = Gson()

                                val itemType = object : TypeToken<List<Zippo>>() {}.type

                                val list = gson.fromJson<List<Zippo>>(results.toString(), itemType)

                                mainActivity?.onZippoReceived(list)
                            }
                        }
                    }
                }
            })
        }
    }
}
