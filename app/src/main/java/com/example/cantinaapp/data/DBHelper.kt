package com.example.cantinaapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.cantinaapp.model.Categoria
import com.example.cantinaapp.model.Produto

class DBHelper(context: Context) : SQLiteOpenHelper(context, "CantinaDB.db", null, 4) {

    val initQueries = arrayOf(
        "CREATE TABLE categoria (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);",
        "INSERT INTO categoria (name) VALUES ('padrão');",
        "INSERT INTO categoria (name) VALUES ('doces');",
        "INSERT INTO categoria (name) VALUES ('salgados');",
        "CREATE TABLE produto (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price FLOAT, imageId INT);",
        "INSERT INTO produto (name, price, imageId) VALUES ('morango', 3.50, -1);",
        "INSERT INTO produto (name, price, imageId) VALUES ('bolo', 2.50, -1);",
        "INSERT INTO produto (name, price, imageId) VALUES ('suco', 1.50, -1);",
    )

    override fun onCreate(db: SQLiteDatabase) {
        initQueries.forEach {
            db.execSQL(it)
        }
    }

    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_key=ON;")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE categoria")
        db.execSQL("DROP TABLE produto")
        onCreate(db)
    }

    fun categoriaInsert(name: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        val result = db.insert("categoria", null, contentValues)
        db.close()
        return result
    }

    fun categoriaListSelectAll(): ArrayList<Categoria> {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM categoria", null)
        val listaCategoria: ArrayList<Categoria> = ArrayList()
        if (c.count > 0) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("name")
            do {
                val id = c.getInt(idIndex)
                val name = c.getString(nameIndex)
                listaCategoria.add(Categoria(id, name))
            } while (c.moveToNext())
        }
        db.close()
        return listaCategoria
    }

    fun produtoInsert(name: String, price: Float, imageId: Int): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("price", price)
        contentValues.put("imageId", imageId)
        val result = db.insert("produto", null, contentValues)
        db.close()
        return result
    }

    fun produtoListSelectAll(): ArrayList<Produto> {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM produto", null)
        val listaProduto: ArrayList<Produto> = ArrayList()
        if (c.count > 0) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("name")
            val priceIndex = c.getColumnIndex("price")
            val imageIndex = c.getColumnIndex("imageId")
            do {
                val id = c.getInt(idIndex)
                val name = c.getString(nameIndex)
                val price = c.getFloat(priceIndex)
                val image = c.getInt(imageIndex)
                listaProduto.add(Produto(id, name, price, image))
            } while (c.moveToNext())
        }
        db.close()
        return listaProduto
    }

}