package com.dev.martyniuk.local.chat.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SourceFS

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SourceOS

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SourceRemote