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

class GetOneZippo {
    companion object {
        suspend fun send2(mainActivity : MainActivityInterface?) {

            val client = OkHttpClient()
            val url = "https://api.zippopotam.us/"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    Log.e("GetOneZippo", call.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val bodyInString = "{\"results\": [{\"post code\":\"28003\",\"country\":\"Spain\",\"country abbreviation\":\"ES\"}]}"
                        bodyInString?.let {
                            Log.w("GetOneZippo", bodyInString)
                            val JsonObject = JSONObject(bodyInString)

                            val results = JsonObject.optJSONArray("results")

                            Log.w("GetOneZippo", results.toString())

                            results?.let {
                                Log.w("GetOneZippo", results.toString())
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
