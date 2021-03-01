package com.tul.carrito.model

import java.io.Serializable

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class ProductCartId(
    @Column(name = "product_id")
    var productId: Int = -1,

    @Column(name = "cart_id")
    var cartId: Int = -1
): Serializable