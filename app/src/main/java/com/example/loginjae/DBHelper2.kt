import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper2(context: Context) : SQLiteOpenHelper(context, "post.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable =
            "CREATE TABLE posts (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS posts")
        onCreate(db)
    }

    fun addPost(title: String, content: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("content", content)
        }
        return db.insert("posts", null, values)

    }

    fun getAllPosts(): List<addPost>{
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM posts", null)
        val posts = mutableListOf<Post>()

        with(cursor) {
            while (moveToNext()) {

                val title = getString(getColumnIndexOrThrow("title"))
                val content = getString(getColumnIndexOrThrow("content"))
                posts.add(Post(title, content))
            }
        }
        cursor.close()
        return posts
    }


}



