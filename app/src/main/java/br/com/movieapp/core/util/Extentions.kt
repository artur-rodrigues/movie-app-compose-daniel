package br.com.movieapp.core.util

import br.com.movieapp.BuildConfig

fun String?.toPostUrl(): String? {
    return if (isNullOrBlank()) {
        null
    } else {
        "${BuildConfig.BASE_URL_IMAGE}$this"
    }
}

fun String.toBackdropUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"