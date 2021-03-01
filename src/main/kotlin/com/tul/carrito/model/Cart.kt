package com.tul.carrito.model

import javax.persistence.*

@Entity
@Table(name ="carts")
data class Cart (var status:String){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long =0

    @OneToMany(mappedBy = "cart", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER, orphanRemoval = true)
    var productCars:Set<ProductCars>?=null

}