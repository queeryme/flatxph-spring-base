package com.flatxph.base.configuration

import com.flatxph.base.graphql.AsyncTransactionalExecutionStrategy
import org.springframework.context.annotation.Configuration

@Configuration
class GraphQLConfiguration(val asyncTransactionalExecutionStrategy: AsyncTransactionalExecutionStrategy) {
    fun buildBaseExecutionStrategies() = mutableMapOf("queryExecutionStrategy" to asyncTransactionalExecutionStrategy)
}
