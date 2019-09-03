package com.ascstb.mangaapp5.repository

sealed class RepositoryEmptyResponse {
    class Error(val error: Exception) : RepositoryEmptyResponse()
    object Ok: RepositoryEmptyResponse()
}