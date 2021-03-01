package com.tul.carrito.util

class Constants {
    companion object{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private  const val URL_BASE = URL_API_BASE + URL_API_VERSION

        //Base API endpoint Products
        const val URL_BASE_PRODUCTS = "$URL_BASE/products"

        //Base API endpoint Products
        const val URL_BASE_CARTS = "$URL_BASE/carts"
    }
}