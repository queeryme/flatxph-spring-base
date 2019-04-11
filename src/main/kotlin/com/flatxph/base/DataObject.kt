package com.flatxph.base

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(value = ["fieldsString"], ignoreUnknown = true)
abstract class DataObject : Serializable {

    abstract val fieldsString: String?

    override fun toString(): String {
        return "${this::class.simpleName}{$fieldsString}"
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}
