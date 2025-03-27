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
    var isLoading by mutableStateOf(false)
        private set
    // 検索に使用する文言
    var searchQuery by mutableStateOf("")
        private set
    // 現在表示されている検索結果を表示するために使用した文言
    // searchQueryをViewの表示するしないに使用すると、TextFieldの文言に変更があった場合、
    // 表示されている検索結果が非表示になるためこのParamを用意
    var currentSearchedQuery by mutableStateOf("")
        private set

    fun searchRepository() {
        // Coroutineを使用するため、ViewModelScopeを使用
        viewModelScope.launch {
            isLoading = true
            currentSearchedQuery = searchQuery
            // 注入したUseCaseからデータを取得
            repos = apiTestUseCase.getRepository(searchQuery)?.items ?: emptyList()
            isLoading = false
        }
    }

    fun setQuery(newQuery: String) {
        searchQuery = newQuery
    }
}