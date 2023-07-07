package com.example.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val abpDispatchers:AbpDispatchers)

enum class AbpDispatchers{
    IO
}