package br.com.alura.panucci.model

import java.math.BigDecimal
import java.util.*

data class Product(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val price: BigDecimal,
    val description: String,
    val image: String? = null
){
    val presentationPrice
        get() = "R$ " + price.toPlainString()
}