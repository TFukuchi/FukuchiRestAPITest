package com.fukuchi.restapitestproject.fragment

import android.graphics.Typeface.BOLD
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
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
        LaunchedEffect(Unit) {
            // "Android"をqueryで検索を実行する
            viewModel.searchRepository("Android")
        }

        // 検索実行中はProgressを表示
        if (viewModel.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            // 取得が完了した場合、取得したデータを一覧で表示する
            CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()) {
                    items(viewModel.repos) { repo : SearchRepositoriesResponseItem->
                        Text(
                            text = repo.name,
                            fontSize = Dimens.SearchResultTextSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.SearchResultTextPadding)
                        )
                        HorizontalDivider()
                    }
                }
            }

        }
    }
}