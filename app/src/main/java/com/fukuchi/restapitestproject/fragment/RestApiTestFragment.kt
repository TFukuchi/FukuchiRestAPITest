package com.fukuchi.restapitestproject.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.fukuchi.restapitestproject.model.response.SearchRepositoriesResponseItem
import com.fukuchi.restapitestproject.ui.Dimens
import com.fukuchi.restapitestproject.viewmodel.RestApiTestViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment Class
 */
@OptIn(ExperimentalFoundationApi::class)
@AndroidEntryPoint
class RestApiTestFragment : Fragment() {

   override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                RestApiSearchResultLayout()
            }
        }
    }

    /**
     * RestApiのSearchを実行 + 結果を表示するためのCompose
     * hiltを使用してViewModelを取得
     */
    @Composable
    fun RestApiSearchResultLayout() {
        val viewModel = hiltViewModel<RestApiTestViewModel>()
        // 検索実行中はProgressを表示
        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column {
                Row(modifier = Modifier.fillMaxWidth().padding(Dimens.SearchResultTextPadding)) {
                    TextField(value = viewModel.searchQuery,
                        onValueChange = { newValue -> viewModel.setQuery(newValue)},
                        label = { Text("検索ワード") },
                        modifier = Modifier.weight(1f).padding(end = Dimens.SearchTextFieldPaddingEnd))
                    Button(
                        onClick = { viewModel.searchRepository() },
                        enabled = true) {
                        Text("検索")
                    }
                }

                if (viewModel.currentSearchedQuery.isNotEmpty()) {
                    Text("検索結果 [" + viewModel.currentSearchedQuery + "]"+ viewModel.repos.size + "件です。")
                    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(viewModel.repos) { repo : SearchRepositoriesResponseItem->
                                Text(
                                    text = repo.name,
                                    fontSize = Dimens.SearchResultTextSize,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(Dimens.SearchResultTextPadding))
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}