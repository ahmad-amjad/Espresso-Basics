package com.careem.kotlinchallenge.presenter

import com.careem.kotlinchallenge.ChallengeView
import com.careem.kotlinchallenge.CountingIdlingResourceSingleton
import com.careem.kotlinchallenge.R
import com.careem.kotlinchallenge.base.BasePresenter
import com.careem.kotlinchallenge.models.Answer
import com.careem.kotlinchallenge.models.Question
import com.careem.kotlinchallenge.usecase.GetQuizUseCase

class ChallengePresenter(private val getQuizUseCase: GetQuizUseCase): BasePresenter<ChallengeView>() {

    private lateinit var questions: List<Question>
    private var currentQuestion: Int = 0

    override fun onCreate(view: ChallengeView) {
        super.onCreate(view)
        CountingIdlingResourceSingleton.increment()
        getQuizResponse()
    }

    private fun getQuizResponse() = getQuizUseCase.getQuizResponse().subscribe({
        this.questions = it.questions
        view?.updateView(this.questions[currentQuestion])
        CountingIdlingResourceSingleton.decrement()
    }, { error ->
        error.message?.let {
            view?.showMessage(it)
        } ?: view?.showMessage(R.string.generic_error_msg)
        CountingIdlingResourceSingleton.decrement()
    })

    fun onAnswerSelected(answer: Answer) {
        if(answer.correct) {
            if(currentQuestion >= questions.size - 1) {
                view?.showMessage(R.string.congrats)
            } else {
                view?.showMessage(R.string.correct_answer)
                currentQuestion += 1
                view?.updateView(this.questions[currentQuestion])
            }
        } else {
            view?.showMessage(R.string.wrong_answer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}