package org.example

import org.example.steps.AuthSteps
import org.example.steps.PostsSteps

open class BaseTest {

    val authSteps = AuthSteps()
    val postSteps = PostsSteps()
}