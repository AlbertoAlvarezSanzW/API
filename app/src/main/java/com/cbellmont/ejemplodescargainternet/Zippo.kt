package com.cbellmont.ejemplodescargainternet

import com.google.gson.annotations.SerializedName

data class Zippo (
    @SerializedName("post code")var codigo : Int,
    var country: String,
    @SerializedName("country abbreviation")var code : String?,
    var climate: String,
    @SerializedName("places") var placesCampos: List<String>?){

    override fun toString(): String {
        return "El pais: $country con codigo: $codigo, sus siglas: $code, y la información complementaria es: $placesCampos\n"
    }

}