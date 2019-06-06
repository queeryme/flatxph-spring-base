package com.flatxph.base.configuration

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import com.fasterxml.jackson.module.afterburner.AfterburnerModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.problem.ProblemModule
import org.zalando.problem.validation.ConstraintViolationProblemModule

@Suppress("SpringFacetCodeInspection")
@Configuration
class JacksonConfiguration {

    /*
     * Support for Hibernate types in Jackson.
     */
    @Bean
    fun hibernate5Module() = Hibernate5Module()

    /*
     * Jackson Afterburner module to speed up serialization/deserialization.
     */
    @Bean
    fun afterburnerModule() = AfterburnerModule()

    /*
     * Module for serialization/deserialization of RFC7807 Problem.
     */
    @Bean
    internal fun problemModule() = ProblemModule()

    /*
     * Module for serialization/deserialization of ConstraintViolationProblem.
     */
    @Bean
    internal fun constraintViolationProblemModule() = ConstraintViolationProblemModule()

}
