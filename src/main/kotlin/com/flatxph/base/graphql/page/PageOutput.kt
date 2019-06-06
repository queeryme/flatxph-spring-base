package com.flatxph.base.graphql.page

import org.springframework.data.domain.Page
import kotlin.streams.toList

abstract class PageOutput<T>(results: Page<T>) {
    val totalElements = results.totalElements
    val totalPages = results.totalPages
    val results: List<T> = results.get().toList()
}
