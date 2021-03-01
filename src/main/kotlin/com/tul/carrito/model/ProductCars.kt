package com.tul.carrito.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "product_cars")
data class ProductCars (

    @JsonIgnore
    @EmbeddedId
    var productCartId:ProductCartId?=null,

    var quantity:Int,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    var product:Product? =null,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id",insertable = false, updatable = false)
    var  cart:Cart? = null

){}