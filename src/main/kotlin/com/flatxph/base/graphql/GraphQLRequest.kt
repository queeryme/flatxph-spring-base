package com.flatxph.base.graphql

class GraphQLRequest {
    var query: String? = null
    var mutation: String? = null
    var variables: MutableMap<String, Any> = HashMap()
}
