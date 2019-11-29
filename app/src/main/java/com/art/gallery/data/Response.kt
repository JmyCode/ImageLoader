package com.art.gallery.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Response<T> {

    @JsonProperty("results")
    var results: List<T>? = null

}