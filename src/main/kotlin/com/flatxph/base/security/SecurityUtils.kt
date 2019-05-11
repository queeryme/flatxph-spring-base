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
            return Optional.ofNullable(securityContext.authentication)
                    .map {
                        if (it.principal is UserDetails) (it.principal as UserDetails).username
                        else if (it.principal is String) it.principal
                        null
                    }
        }

    val currentUserJWT: Optional<String>
        get() {
            val securityContext = SecurityContextHolder.getContext()
            return Optional.ofNullable(securityContext.authentication)
                    .filter { it.credentials is String }
                    .map { it.credentials as String }
        }
}
