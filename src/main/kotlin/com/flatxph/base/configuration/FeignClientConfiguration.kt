package com.flatxph.base.configuration

import com.flatxph.base.security.SecurityUtils
import feign.RequestInterceptor
import org.springframework.http.HttpHeaders
import com.flatxph.base.security.JwtConstants.PREFIX
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Suppress("SpringFacetCodeInspection")
@Configuration
class FeignClientConfiguration {

    @Bean
    fun defaultUserClientInterceptor() = RequestInterceptor {
        template -> SecurityUtils.currentUserJWT
            .filter { it.isNotEmpty() }
            .ifPresent { template.header(HttpHeaders.AUTHORIZATION, "$PREFIX $it") }
    }
}
