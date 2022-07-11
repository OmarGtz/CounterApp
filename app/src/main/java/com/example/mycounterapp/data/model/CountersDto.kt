package com.example.mycounterapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * CountersDto
 *
 * @author (c) 2022
 */
data class CountersDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("count") val count: Int
)
