package com.flatxph.base

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable
import java.util.stream.Collectors
import kotlin.reflect.KProperty0

@JsonIgnoreProperties(value = ["fieldList"], ignoreUnknown = true)
abstract class DataObject : Serializable {

    open val fieldList = listOf<KProperty0<Any?>>()

    override fun toString(): String {
        val fieldString = fieldList.stream()
                .filter { it.get() != null }
                .map { "${it.name}='${it.get()}'" }
                .collect(Collectors.joining(", "))
        val name = this::class.simpleName
        return "$name{$fieldString}"
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}
