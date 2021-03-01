package com.tul.carrito.service

import com.tul.carrito.dao.ProductRespository
import com.tul.carrito.exception.NotFoundException
import com.tul.carrito.exception.ServiceException
import com.tul.carrito.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

@Service
class ProductService: ProductInterface {

    @Autowired
    val productRespository: ProductRespository? = null

    @Throws(ServiceException::class)
    override fun list(): List<Product> {
       try {
           return productRespository!!.findAll()
       }catch (e:Exception){
           throw ServiceException(e.message)
       }
    }

    @Throws(ServiceException::class, NotFoundException::class)
    override fun load(idProduct: Long): Product {
        var product: Optional<Product>
        try {
            product = productRespository!!.findById(idProduct)
        }catch (e:Exception){
            throw ServiceException(e.message)
        }
        if(!product.isPresent){
            throw NotFoundException("Product not found")
        }
        return product.get()
    }

    @Throws(ServiceException::class)
    override fun save(product: Product): Product {
        try {
            return productRespository!!.save(product)
        }catch (e:Exception){
            throw ServiceException(e.message)
        }
    }

    override fun remove(idProduct: Long) {
        var product: Optional<Product>
        try {
            product = productRespository!!.findById(idProduct)
        }catch (e:Exception){
            throw ServiceException(e.message)
        }
        if(!product.isPresent){
            throw NotFoundException("Product not found")
        }else{
            try {
                productRespository!!.deleteById(idProduct)
            }catch (e:Exception){
                throw ServiceException(e.message)
            }
        }
    }
}