package br.com.alura.panucci.dao

import br.com.alura.panucci.sampledata.sampleHighlightsProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {
    val products = MutableStateFlow(sampleHighlightsProducts).asStateFlow()

    fun findById(id: String) =
        products.value.firstOrNull {
            it.id == id
        }

}