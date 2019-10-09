package fr.efficom.formation.ppe1719.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface BornesService {
    @GET("api/login_mobile.php")
    fun loginUser(@Query("login") login: String): Call<ResponseBody>

    @GET("api/photos_event.php")
    fun getPhotosForEventAsList(@Query("code") code: String): Call<List<Photo>>

    @FormUrlEncoded
    @POST("api/toggle_like.php")
    fun toggleLike(@Field("id_photo")idPhoto: Int): Call<ResponseBody>

}