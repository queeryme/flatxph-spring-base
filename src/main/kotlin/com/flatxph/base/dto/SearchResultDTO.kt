package com.flatxph.base.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.flatxph.base.DataObject
import java.io.Serializable

class SearchResultDTO : DataObject(), Serializable {
    var id: Long? = null
    var entity: String? = null
    var primary: String? = null
    var secondary: String? = null
    var image: String? = null
    var url: String? = null
    var extras: MutableMap<String, Any?>? = null

    override val fieldList
        @JsonIgnore
        get() = listOf(
                this::id,
                this::entity,
                this::primary,
                this::secondary,
                this::image,
                this::url,
                this::extras
        ) + super.fieldList
}
