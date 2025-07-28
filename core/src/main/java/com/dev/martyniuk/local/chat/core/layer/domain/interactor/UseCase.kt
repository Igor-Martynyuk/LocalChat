package com.dev.martyniuk.local.chat.core.layer.domain.interactor

abstract class UseCase<in A, out R> {
    abstract fun invoke(args: A): R
}