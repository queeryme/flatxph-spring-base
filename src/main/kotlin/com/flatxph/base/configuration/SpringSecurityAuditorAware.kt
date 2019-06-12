package com.flatxph.base.configuration

import com.flatxph.base.security.SecurityUtils
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
class SpringSecurityAuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor() = Optional.of(SecurityUtils.currentUserLogin.orElse("system"))
}
