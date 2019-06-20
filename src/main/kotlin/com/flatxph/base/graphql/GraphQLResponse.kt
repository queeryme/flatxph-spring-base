package com.flatxph.base.graphql

import graphql.GraphQLError
import java.io.Serializable

class GraphQLResponse : Serializable {
    var data: HashMap<String, Any>? = null
    var errors: List<GraphQLError>? = null
}

