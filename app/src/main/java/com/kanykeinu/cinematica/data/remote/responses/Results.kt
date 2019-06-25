package com.kanykeinu.cinematica.data.remote.responses

import com.google.gson.annotations.SerializedName

class Results(@SerializedName("results") val list : List<MovieChangesResponse>) {
}