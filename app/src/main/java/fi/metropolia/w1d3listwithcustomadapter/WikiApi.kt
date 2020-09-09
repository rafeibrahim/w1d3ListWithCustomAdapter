package fi.metropolia.w1d3listwithcustomadapter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object WikiApi {
    const val URL =
        "https://en.wikipedia.org"

    object ModelWiki {
        data class Result(
            val query: Query
        )
        data class Query(
            val searchinfo: Searchinfo
        )
        data class Searchinfo(
            val totalhits: Int
        )
    }

    interface Service {
        @GET("/w/api.php?action=query&format=json&list=search")
    suspend fun getHitCount(@Query("srsearch") name: String): ModelWiki.Result
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(Service::class.java)!!
}

