package com.tul.carrito.service

import com.tul.carrito.dao.CartRepository
import com.tul.carrito.exception.NotFoundException
import com.tul.carrito.exception.ServiceException
import com.tul.carrito.model.Cart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

@Service
class CartService: CartInterface {

    @Autowired
    val cartRepository: CartRepository? = null

    @Throws(ServiceException::class)
    override fun list(): List<Cart> {
        try {
            return cartRepository!!.findAll()
        }catch (e:Exception){
            throw ServiceException(e.message)
        }
    }

    @Throws(ServiceException::class, NotFoundException::class)
    override fun load(idCart: Long): Cart {
        var cart: Optional<Cart>
        try {
            cart = cartRepository!!.findById(idCart)
        }catch (e:Exception){
            throw ServiceException(e.message)
        }
        if(!cart.isPresent){
            throw NotFoundException("Product not found")
        }
        return cart.get()
    }

    @Throws(ServiceException::class)
    override fun save(cart: Cart): Cart {
        try {
            return cartRepository!!.save(cart)
        }catch (e:Exception){
            throw ServiceException(e.message)
        }
    }

    override fun remove(idCart: Long) {
        var cart: Optional<Cart>
        try {
            cart = cartRepository!!.findById(idCart)
        }catch (e:Exception){
            throw ServiceException(e.message)
        }
        if(!cart.isPresent){
            throw NotFoundException("Product not found")
        }else{
            try {
                cartRepository!!.deleteById(idCart)
            }catch (e:Exception){
                throw ServiceException(e.message)
            }
        }
    }
}