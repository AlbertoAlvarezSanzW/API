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

class GetAllZippopotamus {
    companion object {
        suspend fun send(mainActivity : MainActivityInterface?) {

            val client = OkHttpClient()
            val url = "https://api.zippopotam.us/country/postal-code"
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
                        //val bodyInString = response.body?.string()
                        val bodyInString = "{\"results\": [{\"post code\":\"90210\",\"country\":\"United States\",\"country abbreviation\":\"US\"}," +
                                "\n"+"{\"post code\":\"28003\",\"country\":\"Spain\",\"country abbreviation\":\"ES\"}," +
                                "\n"+"{\"post code\":\"85153\",\"country\":\"Germany\",\"country abbreviation\":\"DE\"}," +
                                "\n"+"{\"post code\":\"887423\",\"country\":\"Andorra\",\"country abbreviation\":\"DE\"}," +
                                "\n"+"{\"post code\":\"987425\",\"country\":\"France\",\"country abbreviation\":\"FR\"}," +
                                "\n"+"{\"post code\":\"000234\",\"country\":\"Russia\",\"country abbreviation\":\"RUS\"}]}"
                        bodyInString?.let {
                            Log.w("GetAllZippos", bodyInString)
                            val JsonObject = JSONObject(bodyInString)

                            val results = JsonObject.optJSONArray("results")
                            results?.let {
                                Log.w("GetAllZippos", results.toString())
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
