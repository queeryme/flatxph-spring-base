package com.flatxph.base.graphql

import graphql.ExecutionResult
import graphql.execution.AsyncExecutionStrategy
import graphql.execution.ExecutionContext
import graphql.execution.ExecutionStrategyParameters
import graphql.execution.NonNullableFieldWasNullException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CompletableFuture

@Service
class AsyncTransactionalExecutionStrategy : AsyncExecutionStrategy() {

    @Transactional
    @Throws(NonNullableFieldWasNullException::class)
    override fun execute(executionContext: ExecutionContext, parameters: ExecutionStrategyParameters): CompletableFuture<ExecutionResult> {
        return super.execute(executionContext, parameters)
    }
}
