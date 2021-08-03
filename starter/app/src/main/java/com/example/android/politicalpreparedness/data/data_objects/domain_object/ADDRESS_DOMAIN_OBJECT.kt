package com.example.android.politicalpreparedness.data.data_objects.domain_object

data class ADDRESS_DOMAIN_OBJECT (
        val line1: String,
        val line2: String? = null,
        val city: String,
        val state: String,
        val zip: String? = null
) {
    fun toFormattedString(): String {
        var output = line1.plus("\n")
        if (!line2.isNullOrEmpty()) output = output.plus(line2).plus("\n")
        output = output.plus("$city, $state $zip")
        return output
    }
}