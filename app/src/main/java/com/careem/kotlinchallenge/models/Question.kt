package com.careem.kotlinchallenge.models

class Question(
    val query: String, // The prompt/title/question we want to ask
    val answers: List<Answer> // List of answers (always 4)
)


class Answer(
    val title: String, // The prompt/title/answer choice to the question
    val correct: Boolean // Is this the correct answer or not
)