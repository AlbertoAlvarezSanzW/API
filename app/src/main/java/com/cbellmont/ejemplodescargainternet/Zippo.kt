package com.cbellmont.ejemplodescargainternet

import com.google.gson.annotations.SerializedName

data class Zippo (
    @SerializedName("post code")var codigo : String?,
    var country: String,
    @SerializedName("country abbreviation")var code : String?,
    var climate: String
    //@SerializedName("places") var placesCampos: List<String>?
    ){

    override fun toString(): String {
        return "\nel codigo es: $codigo, con code: $code \n"
    }
//hola
}