package com.fukuchi.restapitestproject.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fukuchi.restapitestproject.model.response.SearchRepositoriesResponseItem
import com.fukuchi.restapitestproject.usecase.RestApiTestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Hiltに当ViewModelを注入
 */
@HiltViewModel
class RestApiTestViewModel @Inject constructor(private val apiTestUseCase: RestApiTestUseCase): ViewModel() {

    // 検索結果一覧
    var repos by mutableStateOf<List<SearchRepositoriesResponseItem>>(emptyList())
        private set
    // データの取得中かどうかのフラグ
    var isLoading by mutableStateOf(true)
        private set

    fun searchRepository(query: String) {
        // Coroutineを使用するため、ViewModelScopeを使用
        viewModelScope.launch {
            isLoading = true
            // 注入したUseCaseからデータを取得
            repos = apiTestUseCase.getRepository(query)?.items ?: emptyList()
            isLoading = false
        }
    }
}