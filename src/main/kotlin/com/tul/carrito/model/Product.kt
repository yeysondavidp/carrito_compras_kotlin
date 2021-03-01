package com.tul.carrito.model

import javax.persistence.*

@Entity
@Table(name="products")
data class Product(
    var nombre:String ="",
    var sku:String ="",
    var descripcion:String="",

) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long =0


}