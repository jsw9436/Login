package com.example.loginjae

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle




class HomeActivity : AppCompatActivity() {
    lateinit var button_back:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginafter)

        button_back=findViewById(R.id.Button_back)


        button_back.setOnClickListener{
            val button_backIntent = Intent(this@HomeActivity,MainActivity::class.java)
            startActivity(button_backIntent)


        }






    }




}