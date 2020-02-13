package ua.codeasylum.themovietestproject.model.networkDto
import com.google.gson.annotations.SerializedName


data class GenreDto(
    @SerializedName("genres")
    val genres: List<Genre> = listOf()
)

data class Genre(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)