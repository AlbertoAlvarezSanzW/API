package com.cbellmont.ejemplodescargainternet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cbellmont.ejemplodescargainternet.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*



interface MainActivityInterface {

    suspend fun onZippoReceived(listZippo: List<Zippo>)
}


class MainActivity : AppCompatActivity(), MainActivityInterface {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setContentView(R.layout.activity_main)


        val launch = CoroutineScope(Dispatchers.IO).launch {
            GetAllZippopotamus.send(this@MainActivity)
        }
    }
    override suspend fun onZippoReceived(listZippo:  List<Zippo>) {
        withContext(Dispatchers.Main){
            binding.tvFilms.text = ""
            listZippo.forEach {
                tvFilms.append(it.toString())
            }
        }

    }
}