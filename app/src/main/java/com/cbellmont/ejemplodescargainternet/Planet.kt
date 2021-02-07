package com.cbellmont.ejemplodescargainternet

import com.google.gson.annotations.SerializedName

data class Planet(
    var name: String,
    @SerializedName("rotation_period")var episodeId : Int,
    @SerializedName("orbital_period")var openingCrawl: String,
    var diameter: Int,
    var climate: String,
    var gravity: String,
    var terrain: String,
    @SerializedName("surface_water") var surfaceWater: String?,
    var population: String,
    @SerializedName("residents")  var residentsUrls: List<String>?,
    @SerializedName("films") var filmsUrls: List<String>?,
    var created: String,
    var edited: String,
    var url: String){

    override fun toString(): String {
        return "$name, population $population and climate $climate\n"
    }
}

