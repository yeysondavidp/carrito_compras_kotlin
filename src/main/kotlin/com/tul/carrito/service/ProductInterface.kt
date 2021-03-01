package com.tul.carrito.service

import com.tul.carrito.model.Product

interface ProductInterface {
    fun list(): List<Product>
    fun load(idProduct:Long): Product
    fun save(product:Product):Product
    fun remove(idProduct: Long)
}