package com.dev.martyniuk.local.chat.core.layer.domain.interactor.validation

import com.dev.martyniuk.local.chat.core.layer.domain.interactor.UseCase

abstract class CaseValidateString(template: String) : UseCase<String, Boolean>() {
    private val regex = Regex(template)
    override fun invoke(args: String) = args.matches(regex)
}