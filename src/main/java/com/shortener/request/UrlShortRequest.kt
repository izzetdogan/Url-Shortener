package com.shortener.request

import com.shortener.model.UrlShort
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty


data class UrlShortRequest @JvmOverloads constructor(
        @field:NotBlank(message = "must not be Blank")
        val url: String?,
        val shortUrl: String?
){

    companion object{
        @JvmStatic
        fun convert(from: UrlShort): UrlShortRequest{
            return UrlShortRequest(from.url,from.shortUrl)
        }
    }
}
