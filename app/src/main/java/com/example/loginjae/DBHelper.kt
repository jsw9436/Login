package com.example.loginjae

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Login.db", null, 1) {
    override fun onCreate(MyDB: SQLiteDatabase?) {
        MyDB!!.execSQL("CREATE TABLE users(id TEXT PRIMARY KEY, password TEXT, nick TEXT, phone TEXT)")
    }

    override fun onUpgrade(MyDB: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        MyDB!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(MyDB)
    }

    fun insertData(id: String?, password: String?, nick: String?, phone: String?): Boolean {
        val MyDB = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("id", id)
            put("password", password)
            put("nick", nick)
            put("phone", phone)
        }
        val result = MyDB.insert("users", null, contentValues)
        MyDB.close()
        return result != -1L
    }

    fun checkUser(id: String?): Boolean {
        val MyDB = this.readableDatabase
        val cursor = MyDB.rawQuery("SELECT * FROM users WHERE id =?", arrayOf(id))
        val res = cursor.count > 0
        cursor.close()
        MyDB.close()
        return res
    }

    fun checkNick(nick: String?): Boolean {
        val MyDB = this.readableDatabase
        val cursor = MyDB.rawQuery("SELECT * FROM users WHERE nick =?", arrayOf(nick))
        val res = cursor.count > 0
        cursor.close()
        MyDB.close()
        return res
    }

    fun checkUserpass(id: String, password: String): Boolean {
        val MyDB = this.readableDatabase
        val cursor = MyDB.rawQuery(
            "SELECT * FROM users WHERE id = ? AND password = ?",
            arrayOf(id, password)
        )
        val res = cursor.count > 0
        cursor.close()
        MyDB.close()
        return res
    }

    companion object {
        const val DBNAME = "Login.db"
    }
}