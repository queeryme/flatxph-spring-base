package com.flatxph.base.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.flatxph.base.DataObject


abstract class BaseDTO : DataObject() {
    var id: Long? = null

    override val fieldsString: String
        @JsonIgnore
        get() = "id='$id'"
}
