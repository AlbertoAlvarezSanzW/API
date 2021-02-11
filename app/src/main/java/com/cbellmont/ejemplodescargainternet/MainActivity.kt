package com.cbellmont.ejemplodescargainternet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.ejemplodescargainternet.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


interface MainActivityInterface {

    suspend fun onZippoReceived(listZippo: List<Zippo>)
}


class MainActivity : AppCompatActivity(), MainActivityInterface {
    companion object{
        const val ejemplo = "VAR1"
        const val OBJETOSECOND=2343
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setContentView(R.layout.activity_main)
        CoroutineScope(Dispatchers.IO).launch {
            GetAllZippopotamus.send(this@MainActivity)
        }
        val boton = findViewById<Button>(R.id.button)
        boton.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            startActivityForResult(intent,OBJETOSECOND)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == OBJETOSECOND && resultCode == RESULT_OK){
            data?.let {
                var respuesta= it.getStringExtra(ejemplo)
                respuesta?.let {
                    CoroutineScope(Dispatchers.IO).launch {
                        GetAllZippopotamus.send(this@MainActivity)
                    }
                }
            }
        }
    }


    override suspend fun onZippoReceived(listZippo:  List<Zippo>) {
        withContext(Dispatchers.Main){
            binding.Zippo.text = ""
            listZippo.forEach {
                Zippo.append(it.toString())
            }
        }

    }
}