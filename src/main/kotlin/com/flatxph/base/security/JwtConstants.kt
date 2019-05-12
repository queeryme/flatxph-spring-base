package com.flatxph.base.security

object JwtConstants {
    const val PREFIX = "Bearer"
    object ClaimConstants {
        const val EMAIL = "email"
        const val AUTHORITIES = "authorities"
    }
}
