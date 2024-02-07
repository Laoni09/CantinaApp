package com.example.cantinaapp.model

class Produto(val id: Int = 0, val name: String = "", val price: Float = 0.0F, var imageId: Int = 0) {
    override fun toString(): String {
        return name
    }
}