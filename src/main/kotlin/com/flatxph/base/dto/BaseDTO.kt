package com.flatxph.address.dto

import com.flatxph.base.DataObject


abstract class BaseDTO : DataObject() {
    var id: Long? = null
    override val fieldsString: String
        get() = "id='$id'"
}
