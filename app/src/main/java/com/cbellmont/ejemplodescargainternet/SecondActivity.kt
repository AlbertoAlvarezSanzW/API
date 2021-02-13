package com.cbellmont.ejemplodescargainternet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.cbellmont.ejemplodescargainternet.MainActivity.Companion.OBJETOSECOND

class SecondActivity : AppCompatActivity() {
    companion object{
        const val VAR="Envioaactivity1"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity_layout)

        val boton=  findViewById<Button>(R.id.button2)
        val editTexto = findViewById<EditText>(R.id.editText1)
        boton.setOnClickListener {
            if(editTexto.text.toString().isEmpty()){

            }else{
                val intent = Intent()
                intent.putExtra(MainActivity.ejemplo,editTexto.text.toString())
                setResult(OBJETOSECOND,intent)
                finish()
            }
        }
    }
}