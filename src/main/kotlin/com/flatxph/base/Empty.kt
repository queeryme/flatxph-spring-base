package com.flatxph.base

import io.github.jhipster.service.filter.Filter
import kotlin.reflect.KMutableProperty0


fun <T : Filter<*>> empty(param: KMutableProperty0<T?>): String {
    return if (param.get() != null) "${param.name}='${param.get()}' " else ""
}
