package com.ascstb.mangaapp5.repository

sealed class RepositoryResponse <out R> {
    class Error(val error: Exception) : RepositoryResponse<Nothing>()
    class Ok<out R>(val result: R) : RepositoryResponse<R>()
}