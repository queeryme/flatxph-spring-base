package com.flatxph.base.rest.errors

import java.net.URI

object ErrorConstants {
    val PROBLEM_BASE_URL = "https://flatx.com.ph/problem"
    val DEFAULT_TYPE: URI = URI.create("$PROBLEM_BASE_URL/problem-with-message")
}
