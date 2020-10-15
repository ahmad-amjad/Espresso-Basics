package com.careem.kotlinchallenge

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.careem.kotlinchallenge.models.QuizResponse
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.theories.suppliers.TestedOn
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChallengeActivityTest {

    private lateinit var testData : QuizResponse
    /*@Rule @JvmField
    var activityRule = ActivityScenarioRule<ChallengeActivity>(
        ChallengeActivity::class.java
    )*/
    @Before
    fun setUp() {
        testData = MockResponse.createMockResponse()
        IdlingRegistry.getInstance().register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun open_Quiz_And_Verify_Rendering_Of_First_Question(){
        val activityScenario = ActivityScenario.launch<ChallengeActivity>(
            ChallengeActivity::class.java)

        onView(withId(R.id.q_header))
            .check(matches(isDisplayed()))

        onView(withId(R.id.question_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.question_title))
            .check(matches(withText(testData.questions[0].query)))

        onView(withId(R.id.answers))
            .check(matches(isDisplayed()))

        onView(withId(R.id.answers))
            .check(matches(isEnabled()))

        onView(withText(testData.questions[0].answers[0].title))
            .check(matches(isDisplayed()))

        onView(withText(testData.questions[0].answers[1].title))
            .check(matches(isDisplayed()))

        onView(withText(testData.questions[0].answers[2].title))
            .check(matches(isDisplayed()))

        onView(withText(testData.questions[0].answers[3].title))
            .check(matches(isDisplayed()))

        onView(withText(testData.questions[0].answers[0].title))
            .check(matches(isNotChecked()))

        onView(withText(testData.questions[0].answers[1].title))
            .check(matches(isNotChecked()))

        onView(withText(testData.questions[0].answers[2].title))
            .check(matches(isNotChecked()))

        onView(withText(testData.questions[0].answers[3].title))
            .check(matches(isNotChecked()))

        onView(withId(R.id.submitButton))
            .check(matches(isDisplayed()))

        // This assertion fails due to the submit button not
        // being disabled when no answer is selected
        onView(withId(R.id.submitButton))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun select_An_Answer_And_Verify_Button_Enabled_Status(){
        val activityScenario = ActivityScenario.launch<ChallengeActivity>(
            ChallengeActivity::class.java)

        onView(withText(testData.questions[0].answers[1].title))
            .perform(click())

        onView(withId(R.id.submitButton))
            .check(matches(isEnabled()))
    }

    @Test
    fun submit_Correct_Answer_And_Verify_Toast_Message(){
        val activityScenario = ActivityScenario.launch<ChallengeActivity>(
            ChallengeActivity::class.java)

        onView(withText(testData.questions[0].answers[1].title))
            .perform(click())

        onView(withId(R.id.submitButton))
            .perform(click())

        onView(withText(R.string.correct_answer))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    @Test
    fun submit_Incorrect_Answer_And_Verify_Toast_Message(){
        val activityScenario = ActivityScenario.launch<ChallengeActivity>(
            ChallengeActivity::class.java)

        onView(withText(testData.questions[0].answers[0].title))
            .perform(click())

        onView(withId(R.id.submitButton))
            .perform(click())

        onView(withText(R.string.wrong_answer))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }

    @Test
    fun submit_Correct_Answer_And_Verify_Rendering_Of_Subsequent_Question(){
        val activityScenario = ActivityScenario.launch<ChallengeActivity>(
            ChallengeActivity::class.java)

        onView(withText(testData.questions[0].answers[1].title))
            .perform(click())

        onView(withId(R.id.submitButton))
            .perform(click())

        onView(withText(R.string.correct_answer))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))

        onView(withId(R.id.q_header))
            .check(matches(isDisplayed()))

        onView(withId(R.id.question_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.question_title))
            .check(matches(withText(testData.questions[1].query)))

        onView(withId(R.id.answers))
            .check(matches(isDisplayed()))

        onView(withId(R.id.answers))
            .check(matches(isEnabled()))

        onView(withText(testData.questions[1].answers[0].title))
            .check(matches(isDisplayed()))

        onView(withText(testData.questions[1].answers[1].title))
            .check(matches(isDisplayed()))

        onView(withText(testData.questions[1].answers[2].title))
            .check(matches(isDisplayed()))

        onView(withText(testData.questions[1].answers[3].title))
            .check(matches(isDisplayed()))

        onView(withText(testData.questions[1].answers[0].title))
            .check(matches(isNotChecked()))

        onView(withText(testData.questions[1].answers[1].title))
            .check(matches(isNotChecked()))

        onView(withText(testData.questions[1].answers[2].title))
            .check(matches(isNotChecked()))

        onView(withText(testData.questions[1].answers[3].title))
            .check(matches(isNotChecked()))

        onView(withId(R.id.submitButton))
            .check(matches(isDisplayed()))

        // This assertion fails due to the submit button not
        // being disabled when no answer is selected
        onView(withId(R.id.submitButton))
            .check(matches(not(isEnabled())))
    }

    @Test
    fun submit_Correct_Answers_For_All_Questions_And_Verify_Toast_Message(){
        val activityScenario = ActivityScenario.launch<ChallengeActivity>(
            ChallengeActivity::class.java)

        onView(withText(testData.questions[0].answers[1].title))
            .perform(click())

        onView(withId(R.id.submitButton))
            .perform(click())

        onView(withText(R.string.correct_answer))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))

        onView(withText(testData.questions[1].answers[2].title))
            .perform(click())

        onView(withId(R.id.submitButton))
            .perform(click())

        onView(withText(R.string.congrats))
            .inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}