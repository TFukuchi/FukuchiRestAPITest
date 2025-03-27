package com.fukuchi.restapitestproject.usecase

import com.fukuchi.restapitestproject.model.response.SearchRepositoriesResponse

/**
 * UseCaseのインターフェース
 * Testを作るときに、仮想UseCaseを注入できるようにするためInterface + Implにわける
 */
interface RestApiTestUseCase {
    suspend fun getRepository(query: String): SearchRepositoriesResponse?
}