package com.flatxph.base.graphql.page

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.PageRequest.of
import org.springframework.data.domain.Sort

data class PageInput(
        val page: Int = 0,
        val size: Int = 20,
        val sort: List<String>?
) {
    fun toPageRequest(): PageRequest {
        if (sort != null && sort.isNotEmpty()) {
            val sortArray = sort.toTypedArray()
            return of(page, size, Sort.by(*sortArray))
        }
        return of(page, size)
    }
}


