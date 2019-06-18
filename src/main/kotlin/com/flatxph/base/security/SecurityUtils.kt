package com.flatxph.base.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

/**
 * Utility class for Spring Security.
 */
object SecurityUtils {

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    val currentUserLogin: Optional<String>
        get() {
            val securityContext = SecurityContextHolder.getContext()
            val authentication = securityContext.authentication ?: return Optional.empty()

            val principal: Any? = authentication.principal ?: return Optional.empty()
            if (principal is UserDetails) return Optional.of(principal.username)
            if (principal is String) return Optional.of(principal)
            return Optional.empty()
        }

    val currentUserJWT: Optional<String>
        get() {
            val securityContext = SecurityContextHolder.getContext()
            return Optional.ofNullable(securityContext.authentication)
                    .filter { it.credentials is String }
                    .map { it.credentials as String }
        }
}
