package com.careem.kotlinchallenge

import androidx.annotation.StringRes
import com.careem.kotlinchallenge.models.Question

interface ChallengeView {
    fun updateView(question: Question)
    fun showMessage(@StringRes resId: Int)
    fun showMessage(message: String)
}