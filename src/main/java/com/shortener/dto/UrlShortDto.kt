package com.shortener.dto

import com.shortener.model.UrlShort
import javax.validation.constraints.NotNull


data class UrlShortDto @JvmOverloads constructor (
        val id: Long?,
        val url: String,
        val shortUrl: String
        ){
        companion object{
                @JvmStatic
                fun convert( from: UrlShort): UrlShortDto{
                        return UrlShortDto(
                                from.id,
                                from.url,
                                from.shortUrl
                        )
                }
        }
}
