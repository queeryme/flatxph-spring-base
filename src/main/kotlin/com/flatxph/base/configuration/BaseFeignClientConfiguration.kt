package com.flatxph.base.configuration

import com.flatxph.base.security.SecurityUtils
import feign.RequestInterceptor
import org.springframework.http.HttpHeaders

class BaseFeignClientConfiguration {

    fun buildBaseFeignClientConfiguration() = RequestInterceptor {
        template -> SecurityUtils.currentUserJWT
            .filter { it.isNotEmpty() }
            .ifPresent { template.header(HttpHeaders.AUTHORIZATION, "Bearer $it") }
    }
}

