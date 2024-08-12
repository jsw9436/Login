package com.example.loginjae

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var buttonLogin: ImageButton
    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText
    lateinit var btnRegister: Button
    var DB:DBHelper?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DB = DBHelper(this)

        editTextEmail=findViewById(R.id.editTextEmail)
        editTextPassword=findViewById(R.id.editTextPassword)
        buttonLogin=findViewById(R.id.buttonLogin)
        btnRegister=findViewById(R.id.btnRegister)

        buttonLogin.setOnClickListener{
            val user =editTextEmail.text.toString()
            val pass=editTextPassword.text.toString()


        }



    }


}