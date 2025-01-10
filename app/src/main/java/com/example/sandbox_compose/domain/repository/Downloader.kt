package com.example.sandbox_compose.domain.repository

interface Downloader {
    fun downloadFile(url: String, fileName: String?)
}
