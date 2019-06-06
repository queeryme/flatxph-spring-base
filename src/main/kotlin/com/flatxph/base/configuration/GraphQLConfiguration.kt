package com.flatxph.base.configuration

import com.flatxph.base.graphql.AsyncTransactionalExecutionStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Suppress("SpringJavaInjectionPointsAutowiringInspection", "SpringFacetCodeInspection")
@Configuration
class GraphQLConfiguration(val asyncTransactionalExecutionStrategy: AsyncTransactionalExecutionStrategy) {
    @Bean
    fun buildBaseExecutionStrategies() = mutableMapOf("queryExecutionStrategy" to asyncTransactionalExecutionStrategy)
}
