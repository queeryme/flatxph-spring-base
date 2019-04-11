package com.flatxph.base.rest.errors

import org.zalando.problem.AbstractThrowableProblem
import org.zalando.problem.Exceptional
import org.zalando.problem.Status

/**
 * Simple exception with a message, that returns an Internal Server Error code.
 */
class InternalServerErrorException(message: String) : AbstractThrowableProblem(ErrorConstants.DEFAULT_TYPE, message, Status.INTERNAL_SERVER_ERROR) {

    override fun getCause(): Exceptional {
        return super.cause as Exceptional
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}
