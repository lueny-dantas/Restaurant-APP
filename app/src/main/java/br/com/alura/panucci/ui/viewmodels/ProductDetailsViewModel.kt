package br.com.alura.panucci.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import br.com.alura.panucci.dao.ProductDao
import br.com.alura.panucci.navigation.productIdArgument
import br.com.alura.panucci.ui.uistate.ProductDetailsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class ProductDetailsViewModel(
    private val dao: ProductDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsUiState>(
        ProductDetailsUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle
                .getStateFlow<String?>(productIdArgument, null)
                .filterNotNull()
                .collect { id ->
                    findProductById(id)
                }
        }
    }

    fun findProductById(id: String) {
        _uiState.update { ProductDetailsUiState.Loading }
        viewModelScope.launch {
            val timemillis = Random.nextLong(500, 2000)
            delay(timemillis)
            val dataState = dao.findById(id)?.let { product ->
                ProductDetailsUiState.Success(product = product)
            } ?: ProductDetailsUiState.Failure

            _uiState.update { dataState }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = this[APPLICATION_KEY] as Context
                Log.i("ProductDetailsVM", "context: $context")
                ProductDetailsViewModel(
                    dao = ProductDao(),
                    savedStateHandle = createSavedStateHandle()
                )
            }
        }
    }

}
