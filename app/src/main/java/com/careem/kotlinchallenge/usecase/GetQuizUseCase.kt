package com.careem.kotlinchallenge.usecase

import com.careem.kotlinchallenge.CountingIdlingResourceSingleton
import com.careem.kotlinchallenge.QuizService
import com.careem.kotlinchallenge.models.QuizResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface GetQuizUseCase {
    fun getQuizResponse(): Single<QuizResponse>
}

class GetQuizUseCaseImpl(private val quizService: QuizService): GetQuizUseCase {
    override fun getQuizResponse(): Single<QuizResponse> {
        return quizService.getQuizList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}