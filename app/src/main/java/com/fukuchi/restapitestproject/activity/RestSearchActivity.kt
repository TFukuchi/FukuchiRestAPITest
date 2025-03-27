package com.fukuchi.restapitestproject.activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.fukuchi.restapitestproject.R
import com.fukuchi.restapitestproject.fragment.RestApiTestFragment
import com.fukuchi.restapitestproject.ui.theme.RestApiTestProjectTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity Class
 * Fragmentを表示するのみ。
 * なるべくxmlを使用しないためにAndroidViewをComposeで作成し、そこへFragmentを表示
 */
@AndroidEntryPoint
class RestSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestSearchActivityCompose()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun RestSearchActivityCompose() {
        RestApiTestProjectTheme {
            // レイアウト枠 Modifier.fillMaxSizeで画面全体サイズ指定
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                // Android Viewの作成。
                // ここにFragmentを表示(containerの役割)
                AndroidView(
                    factory = { context ->
                        FrameLayout(context).apply {
                            id = R.id.fragment_container_view
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize().padding(innerPadding)
                )

                // 初回Compose痔に一度だけ実行
                LaunchedEffect(Unit) {
                    showFragment()
                }
            }
        }
    }

    private fun showFragment() {
        if (supportFragmentManager.findFragmentById(R.id.fragment_container_view) == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, RestApiTestFragment())
                .commit()
        }
    }
}