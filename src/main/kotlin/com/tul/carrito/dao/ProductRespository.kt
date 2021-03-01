package com.tul.carrito.dao

import com.tul.carrito.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRespository: JpaRepository<Product, Long> {
}