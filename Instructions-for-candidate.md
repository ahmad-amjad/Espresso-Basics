## Problem Statement
Create a quiz app with multiple choice questions. The interface should look similiar to this: 

![Alt text](Mockup.png?raw=true "Mockup")

## Material Provided
The project provided to you for this challenge already includes the following to help you get started:
1. `NetworkProvider`: A very basic implementation of Retrofit with OkHttpClient with an interceptor for logging.
2. `Question` `Answer` models: Plain Kotlin data classes that represent Question and Answer entities.
3. `ChallengeActivity`: A very basic `Activity` class that is already inflating `Ractivity_challenge.xml`. The basic layout is already designed.
4. `ExampleUnitTest`: A unit test class.
5. Resources: Some assets (radio button) have been added to the project.

### Note: The template project has been created using Gradle 6.1.1, Android Gradle plugin 4.0.0 and Kotlin 1.3.72. We highly recommend you to upgrade your environment if it is not already using these versions to ensure your interview goes as smooth as possible.

## Expected Result
Your application should perform the following operations:
- Get a list of questions from a [remote resource](https://gist.githubusercontent.com/AamirAbro/7abe426f0f01f58140e826b19f020a8b/raw/58eb42d6a2925e066805eb96612ee33718316b7d/KoltinChallenge.json) and decode it.
- Randomly choose one question from the list and render it on the screen.
- Allow users to select an answer from the choices given and submit that answer.
- Show a dialog if the answer was right or wrong.

## Evaluation Criteria
Your finished application will be evaluated for 
1. Quality and completion. Don't spend all your time implementing a perfect architecture, but also don't compromise quality to get to the finish line.
2. Maintainability. Your colleagues and your future self should be able to understand, edit and remove any part of your code without pulling their hair out.
3. Testability. Design your code so each part is testable, for example verifying business logic through unit tests.

## General Guidelines
You will be pairing on this challenge with a Careem engineer. A few final tips to help you succeed:
- Talk through the way you’re tackling the problem. Avoid silent coding.
- Try to approach the question from different angles and showcase your in-depth technical and design knowledge.
- You will need to think of an efficient and optimized solution. Don’t over-engineer either.
- Write elegant, bug-free and clean code.
- Ask the interviewer for help when you are stuck. It's okay to ask for help.
- Feel free to use external libraries.
- Feel free to use Google, Stack Overflow etc for code snippets or API docs.
- Feel free to copy code from the internet (within reason).

## Bonus
Write tests for at least one class/struct.
