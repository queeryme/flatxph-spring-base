package com.flatxph.base.rest.util

import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders

object HeaderUtil {

    private val log = LoggerFactory.getLogger(HeaderUtil::class.java)

    fun createAlert(message: String, param: String): HttpHeaders {
        val headers = HttpHeaders()
        headers.add("X-alert", message)
        headers.add("X-params", param)
        return headers
    }

    fun createEntityCreationAlert(entityName: String, param: String): HttpHeaders {
        return createAlert("A new $entityName is created with identifier $param", param)
    }

    fun createEntityUpdateAlert(entityName: String, param: String): HttpHeaders {
        return createAlert("A $entityName is updated with identifier $param", param)
    }

    fun createEntityDeletionAlert(entityName: String, param: String): HttpHeaders {
        return createAlert("A $entityName is deleted with identifier $param", param)
    }

    fun createFailureAlert(entityName: String, @Suppress("UNUSED_PARAMETER") errorKey: String, defaultMessage: String): HttpHeaders {
        log.error("A $entityName processing failed, $defaultMessage")
        val headers = HttpHeaders()
        headers.add("X-error", defaultMessage)
        headers.add("X-params", entityName)
        return headers
    }
}
