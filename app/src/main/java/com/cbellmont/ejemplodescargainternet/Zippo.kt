package com.cbellmont.ejemplodescargainternet

import com.google.gson.annotations.SerializedName

data class Zippo (
    @SerializedName("post code")var codigo : String?,
    var country: String,
    @SerializedName("country abbreviation")var code : String?,
    var climate: String){



    override fun toString(): String {
        return "\nLa ciudad es: $country, el codigo es: $codigo y con code: $code \n"
    }

}