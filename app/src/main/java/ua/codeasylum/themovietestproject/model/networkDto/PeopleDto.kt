package ua.codeasylum.themovietestproject.model.networkDto

import com.google.gson.annotations.SerializedName


data class PeopleDto(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<Person> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)

data class Person(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("profile_path")
    val profilePath: String = "",
    var page: Int = 0,
    var totalPages: Int = 0

)