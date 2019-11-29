package com.art.gallery.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ImageUrls {

    @JsonProperty("thumb")
    var url: String? = ""
}