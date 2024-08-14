package com.example.loginjae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    var DB: DBHelper? = null
    lateinit var editTextId_Reg: EditText
    lateinit var editTextPass_Reg: EditText
    lateinit var editTextRePass_Reg: EditText
    lateinit var editTextNick_Reg: EditText
    lateinit var editTextPhone: EditText
    lateinit var btnRegister: Button
    lateinit var back_button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        back_button = findViewById<Button>(R.id.back_button)
        editTextId_Reg = findViewById(R.id.editTextId_Reg)
        editTextPass_Reg = findViewById(R.id.editTextPass_Reg)
        editTextRePass_Reg = findViewById(R.id.editTextRePass_Reg)
        editTextPhone = findViewById(R.id.editTextPhone_Reg)
        editTextNick_Reg = findViewById(R.id.editTextNick_Reg)
        btnRegister=findViewById<Button>(R.id.btnRegister)
        back_button.setOnClickListener{

            val back_buttonIntent =  Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(back_buttonIntent)
        }


        DB = DBHelper(this)



        btnRegister.setOnClickListener {
            val user = editTextId_Reg.text.toString()
            val pass = editTextPass_Reg.text.toString()
            val repass = editTextRePass_Reg.text.toString()
            val nick = editTextNick_Reg.text.toString()
            val phone = editTextPhone.text.toString()
            val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,15}$"
            val idPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,15}$"
            val nickPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,15}$"
            val phonePattern = "^(\\+[0-9]+)?[0-9]{10,15}$"
            // 사용자 입력이 비었을 때
            if (user == "" || pass == "" || repass == "" || nick == "" || phone == "") Toast.makeText(
                this@RegisterActivity,
                "회원정보를 모두 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show()
            else {
                // 아이디 형식이 맞을 때
                if (Pattern.matches(idPattern, user)) {
                    val checkUsername = DB!!.checkUser(user)
                    // 비밀번호 형식이 맞을 때
                    if (Pattern.matches(pwPattern, pass)) {
                        // 비밀번호 재확인 성공
                        if (pass == repass) {

                            // 닉네임 형식 체크
                            if (Pattern.matches(nickPattern, nick)) {
                                val checkNick = DB!!.checkNick(nick)
                                // 번호 형식
                                if (Pattern.matches(phonePattern, phone)) {
                                    // 새로운 아이디일 때
                                    if (checkUsername == false) {
                                        if (checkNick == false) {
                                            val insert = DB!!.insertData(user, pass, nick, phone)
                                            // 가입 성공 시 Toast를 띄우고 메인 화면으로 전환
                                            if (insert == true) {
                                                Toast.makeText(
                                                    this@RegisterActivity,
                                                    "가입되었습니다.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                val intent = Intent(
                                                    applicationContext,
                                                    MainActivity::class.java
                                                )
                                                startActivity(intent)
                                            }
                                            // 가입 실패 시
                                            else {
                                                Toast.makeText(
                                                    this@RegisterActivity,
                                                    "가입 실패하였습니다.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                        // 존재하는 닉네임일 경우
                                        else {
                                            Toast.makeText(
                                                this@RegisterActivity,
                                                "이미 존재하는 닉네임입니다.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                    // 존재하는 id일 경우
                                    else {
                                        Toast.makeText(
                                            this@RegisterActivity,
                                            "이미 존재하는 아이디입니다.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                } else {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "전화번호 형식이 옳지 않습니다.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "닉네임 형식이 옳지 않습니다.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        // 비밀번호 재확인 실패
                        else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "비밀번호가 일치하지 않습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    // 비밀번호 형식이 맞지 않을 때
                    else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "비밀번호 형식이 옳지 않습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                // 아이디 형식이 맞지 않을 때
                else {
                    Toast.makeText(this@RegisterActivity, "아이디 형식이 옳지 않습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
