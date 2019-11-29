package com.art.gallery.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ImageModel {

    @JsonProperty("urls")
    var urls: ImageUrls? = null

}