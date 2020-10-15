package com.careem.kotlinchallenge

import com.careem.kotlinchallenge.models.Answer
import com.careem.kotlinchallenge.models.Question
import com.careem.kotlinchallenge.models.QuizResponse

object MockResponse {

    /*fun createMockResponse() = Question(
        query = "Which file extension is used to save Kotlin files?",
        answers = listOf(
            Answer(
                title = ".java",
                correct = false
            ),
            Answer(
                title = ".kt or .kts",
                correct = true
            ),
            Answer(
                title = ".andriod",
                correct = false
            ),
            Answer(
                title = ".kot",
                correct = false
            )
        )
    )*/

    fun createMockResponse() = QuizResponse(
        questions = listOf(
            Question(
                query = "Which file extension is used to save Kotlin files?",
                answers = listOf(
                    Answer(
                        title = ".java",
                        correct = false
                    ),
                    Answer(
                        title = ".kt or .kts",
                        correct = true
                    ),
                    Answer(
                        title = ".andriod",
                        correct = false
                    ),
                    Answer(
                        title = ".kot",
                        correct = false
                    )
                )
            ),
            Question(
                query = "What is the difference between val and var in Kotlin?",
                answers = listOf(
                    Answer(
                        title = "Variables declared with var are final, those with val are not.",
                        correct = false
                    ),
                    Answer(
                        title = "Variables declared with val can only access const members.",
                        correct = false
                    ),
                    Answer(
                        title = "Variables declared with val are final, those with var are not.",
                        correct = true
                    ),
                    Answer(
                        title = "var is scoped to the nearest function block and val is scoped to the nearest enclosing block.",
                        correct = false
                    )
                )
            )
        )
    )
}