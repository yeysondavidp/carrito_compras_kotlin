package com.tul.carrito.rest

import com.tul.carrito.exception.NotFoundException
import com.tul.carrito.exception.ServiceException
import com.tul.carrito.model.Cart
import com.tul.carrito.service.CartService
import com.tul.carrito.util.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CARTS)
class CartRestController {
    @Autowired
    var cartService: CartService? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Cart>> {
        return try {
            ResponseEntity(cartService!!.list(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load (@PathVariable("id")idCart:Long): ResponseEntity<Cart> {
        return try {
            ResponseEntity(cartService!!.load(idCart), HttpStatus.OK)
        }catch (e: ServiceException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody cart: Cart): ResponseEntity<Any> {
        return try {
            cart.status = "PENDING"
            cartService!!.save(cart)
            var responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_CARTS+"/"+ cart.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e: ServiceException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody cart: Cart): ResponseEntity<Any> {
        return try {
            cartService!!.save(cart)
            ResponseEntity(HttpStatus.OK)
        }catch (e: ServiceException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("checkout")
    fun checkout(@RequestBody cart: Cart): ResponseEntity<Any> {
        return try {
            cart.status = "COMPLETED"
            cartService!!.save(cart)
            ResponseEntity(HttpStatus.OK)
        }catch (e: ServiceException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete (@PathVariable("id") idCart: Long): ResponseEntity<Any> {
        return try {
            cartService!!.remove(idCart)
            ResponseEntity(HttpStatus.OK)
        }catch (e: ServiceException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}