package com.example.loginjae

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

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
        buttonLogin=findViewById<ImageButton>(R.id.buttonLogin)
        btnRegister=findViewById<Button>(R.id.btnRegister)

        buttonLogin.setOnClickListener{
            val user =editTextEmail.text.toString()
            val pass=editTextPassword.text.toString()
            if (user == "" || pass == "") {
                Toast.makeText(this@MainActivity, "아이디 비밀번호 입력하세요",Toast.LENGTH_SHORT).show() }


            else  {
                val checkUserpass = DB!!.checkUserpass(user, pass)
                if (checkUserpass == true) {

                    Toast.makeText(this@MainActivity, "로그인", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                else{
                    if(checkUserpass == false){
                        Toast.makeText(this@MainActivity, "비밀번호 와 아이디가 맞지않습니다.",Toast.LENGTH_SHORT).show()

                    }
                }
            }

            btnRegister.setOnClickListener{
                val loginIntent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(loginIntent)
            }
        }
    }
}