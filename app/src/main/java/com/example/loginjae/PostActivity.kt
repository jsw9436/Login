package com.example.loginjae

import DBHelper2
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PostActivity:AppCompatActivity() {
    var DB: DBHelper2? = null


    lateinit var editPostTitle:EditText
    lateinit var editPostContent:EditText

    lateinit var submitPostButton:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_post)
        editPostTitle=findViewById<EditText>(R.id.editPostTitle)
        editPostContent=findViewById<EditText>(R.id.editPostContent)

        submitPostButton=findViewById<Button>(R.id.submitPostButton)

        DB = DBHelper2(this)


        submitPostButton.setOnClickListener {
            val title = editPostTitle.text.toString()
            val content = editPostContent.text.toString()

            // 빈 값 검증
            if (title.trim().isEmpty() || content.trim().isEmpty()) {
                Toast.makeText(this@PostActivity, "입력 해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // DB에 게시글 삽입
            val addPost = DB!!.addPost(title, content)
            if (addPost != -1L) {
                Toast.makeText(this@PostActivity, "작성완료", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@PostActivity, "작성 실패", Toast.LENGTH_SHORT).show()
            }


        }






    }






}