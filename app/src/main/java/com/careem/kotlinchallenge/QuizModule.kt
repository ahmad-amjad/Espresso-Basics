package com.careem.kotlinchallenge

import com.careem.kotlinchallenge.presenter.ChallengePresenter
import com.careem.kotlinchallenge.usecase.GetQuizUseCase
import com.careem.kotlinchallenge.usecase.GetQuizUseCaseImpl

class QuizModule {
    companion object {
        private fun provideGetQuizUseCase(): GetQuizUseCase {
            return GetQuizUseCaseImpl(NetworkProvider().provideRetrofitQuizService())
        }

        fun provideChallengePresenter(): ChallengePresenter {
            return ChallengePresenter(provideGetQuizUseCase())
        }
    }
}