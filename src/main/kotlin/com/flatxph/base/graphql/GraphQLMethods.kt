package com.flatxph.base.graphql

import graphql.language.Field
import graphql.language.SelectionSet
import graphql.schema.DataFetchingEnvironment


private fun recurseSelection(selectionSet: SelectionSet): String {
    var selectionText = ""
    selectionSet.selections.forEach {
        if (it is Field) {
            var fieldText = it.name
            if (it.selectionSet != null) fieldText += "{${recurseSelection(it.selectionSet)}}"
            selectionText += "$fieldText,"
        }
    }
    return selectionText
}

/**
 * This rebuilds the subfields selected on the current field based on the environment.
 */
fun buildFieldQueryBody(environment: DataFetchingEnvironment): String{
    val field = environment.fields[0]
    val recursiveResult = recurseSelection(field.selectionSet)
    return "{$recursiveResult}"
}
