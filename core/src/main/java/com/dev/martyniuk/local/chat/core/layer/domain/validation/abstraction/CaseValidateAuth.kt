package com.dev.martyniuk.local.chat.core.layer.domain.validation.abstraction

import com.dev.martyniuk.local.chat.core.layer.domain.UseCase

abstract class CaseValidateString(template: String) : UseCase<String, Boolean>() {
    private val regex = Regex(template)
    override fun execute(args: String) = args.matches(regex)
}