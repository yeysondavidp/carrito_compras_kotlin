package com.tul.carrito.service

import com.tul.carrito.model.Cart

interface CartInterface {
    fun list(): List<Cart>
    fun load(idCart:Long): Cart
    fun save(cart: Cart): Cart
    fun remove(idCart: Long)
}