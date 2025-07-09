package com.dev.martyniuk.local.chat.core.layer.domain

abstract class UseCase<in A, out R> {
    abstract fun invoke(args: A): R
}