package com.example.cantinaapp.model

class Categoria(val id: Int = 0, var name: String = "") {
    override fun toString(): String {
        return name
    }
}