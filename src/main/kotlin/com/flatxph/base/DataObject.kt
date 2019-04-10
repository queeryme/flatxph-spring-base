package com.flatxph.base

import java.io.Serializable

abstract class DataObject: Serializable {

    abstract val fieldsString: String?

    override fun toString(): String {
        return "${this::class.simpleName}{$fieldsString}"
    }

    companion object {

        private const val serialVersionUID = 1L
    }
}
