package com.careem.kotlinchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.careem.kotlinchallenge.models.Answer
import com.careem.kotlinchallenge.models.Question
import com.careem.kotlinchallenge.presenter.ChallengePresenter

class ChallengeActivity : AppCompatActivity(), ChallengeView {

    private lateinit var presenter: ChallengePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)

        presenter = QuizModule.provideChallengePresenter()
        presenter.onCreate(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun updateView(question: Question) {
        findViewById<TextView>(R.id.question_title).text = question.query

        val radioGroup = findViewById<RadioGroup>(R.id.answers)
        radioGroup.removeAllViews()
        question.answers.map { answer ->
            (LayoutInflater.from(this@ChallengeActivity).inflate(R.layout.answer_radio_button, radioGroup, false) as RadioButton).apply {
                id = View.generateViewId()
                this.text = answer.title
                setTag(R.id.answers, answer)
            }
        }.forEach(radioGroup::addView)

        findViewById<TextView>(R.id.submitButton).setOnClickListener {
            val checkedRadioButtonId = findViewById<RadioGroup>(R.id.answers).checkedRadioButtonId
            val answer: Answer = findViewById<RadioButton>(checkedRadioButtonId).getTag(R.id.answers) as Answer
            presenter.onAnswerSelected(answer)
        }

    }

    override fun showMessage(resId: Int) {
        showMessage(getString(resId))
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}