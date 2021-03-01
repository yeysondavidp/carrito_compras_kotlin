package com.tul.carrito.rest

import com.tul.carrito.exception.NotFoundException
import com.tul.carrito.exception.ServiceException
import com.tul.carrito.model.Product
import com.tul.carrito.service.ProductService
import com.tul.carrito.util.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PRODUCTS)
class ProductRestController {

    @Autowired
    val productService: ProductService? = null

    @GetMapping("")
    fun list():ResponseEntity<List<Product>>{
        return try {
            ResponseEntity(productService!!.list(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load (@PathVariable("id")idProduct:Long): ResponseEntity<Product>{
        return try {
            ResponseEntity(productService!!.load(idProduct),HttpStatus.OK)
        }catch (e:ServiceException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping ("")
    fun insert(@RequestBody product: Product): ResponseEntity<Any>{
        return try {
            productService!!.save(product)
            var responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PRODUCTS+"/"+ product.id)
            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e:ServiceException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody product: Product): ResponseEntity<Any>{
        return try {
            productService!!.save(product)
            ResponseEntity(HttpStatus.OK)
        }catch (e:ServiceException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete (@PathVariable("id") idProduct: Long):ResponseEntity<Any>{
        return try {
            productService!!.remove(idProduct)
            ResponseEntity(HttpStatus.OK)
        }catch (e:ServiceException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


}