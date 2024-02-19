package com.example.cantinaapp.model

class Produto(val id: Int = 0, var name: String = "", var price: Float = 0.0F, var imageId: Int = 0, var categoriaId: Int = 0) {
    override fun toString(): String {
        return name
    }
}