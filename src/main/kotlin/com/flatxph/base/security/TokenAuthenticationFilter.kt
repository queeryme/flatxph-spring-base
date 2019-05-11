package com.flatxph.base.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * This is a stateless token authentication filter that doesn't require database access.
 * It just needs to validate with the Signing Key and assume everything is right.
 */
class TokenAuthenticationFilter(
        private val tokenSecret: String
) : OncePerRequestFilter() {

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    private fun getAuthorities(claims: Claims): List<SimpleGrantedAuthority> {
        val authoritiesUntyped = claims.getOrDefault("authorities", emptyList<String>())
        val authorities = if (authoritiesUntyped is List<*>) authoritiesUntyped else emptyList<Any>()
        return authorities.stream()
                .filter { it is String }
                .map { SimpleGrantedAuthority(it as String) }
                .collect(Collectors.toList())
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        val jwt = getJwtFromRequest(request)

        try {
            val claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(jwt).body
            val email = claims["email"] ?: throw BadCredentialsException("Email of Claims does not exist.")
            val authorities = getAuthorities(claims)
            val authentication = UsernamePasswordAuthenticationToken(email, jwt, authorities)

            // assign on success
            SecurityContextHolder.getContext().authentication = authentication
        } catch (e: Exception) {
            // clear on failure
            SecurityContextHolder.clearContext()
        }

        chain.doFilter(request, response)
    }

}
