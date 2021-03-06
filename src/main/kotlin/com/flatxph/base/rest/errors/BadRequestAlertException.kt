package com.flatxph.base.rest.errors

import org.zalando.problem.AbstractThrowableProblem
import org.zalando.problem.Exceptional
import org.zalando.problem.Status
import java.net.URI
import java.util.*

open class BadRequestAlertException(
        type: URI,
        defaultMessage: String,
        val entityName: String,
        val errorKey: String
) : AbstractThrowableProblem(
        type,
        defaultMessage,
        Status.BAD_REQUEST,
        null,
        null,
        null,
        getAlertParameters(entityName, errorKey)
) {
    override fun getCause(): Exceptional {
        return super.cause as Exceptional
    }

    constructor(defaultMessage: String, entityName: String, errorKey: String) :
            this(ErrorConstants.DEFAULT_TYPE, defaultMessage, entityName, errorKey)

    companion object {

        private const val serialVersionUID = 1L

        private fun getAlertParameters(entityName: String, errorKey: String): Map<String, Any> {
            val parameters = HashMap<String, Any>()
            parameters["message"] = "error.$errorKey"
            parameters["params"] = entityName
            return parameters
        }
    }
}
