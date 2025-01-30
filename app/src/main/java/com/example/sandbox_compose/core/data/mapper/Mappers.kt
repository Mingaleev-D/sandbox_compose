package com.example.sandbox_compose.core.data.mapper

interface Mappers<T : Any?, Model : Any> {

    fun toModel(value: T): Model
    fun fromModel(value: Model): T
}
