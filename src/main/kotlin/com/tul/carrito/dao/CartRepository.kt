package com.tul.carrito.dao

import com.tul.carrito.model.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository: JpaRepository<Cart, Long> {
}