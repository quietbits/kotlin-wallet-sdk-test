package org.stellar.kotlinwalletsdk

import java.io.IOException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

private val client = OkHttpClient()

class TestHttp {
  val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
  val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()

  fun runMd() {
    val postBody =
      """
        |Releases
        |--------
        |
        | * _1.0_ May 6, 2013
        | * _1.1_ June 15, 2013
        | * _1.2_ August 11, 2013
        |""".trimMargin()

    val request =
      Request.Builder()
        .url("https://api.github.com/markdown/raw")
        .post(postBody.toRequestBody(MEDIA_TYPE_MARKDOWN))
        .build()

    client.newCall(request).execute().use { response ->
      if (!response.isSuccessful) throw IOException("Unexpected code $response")

      println(response.body!!.string())
    }
  }

  fun runJson() {
    val postBody =
      """
          {"transaction":"AAAAAgAAAACpn2Fr7GAZ4XOcFvEz+xduBFDK1NDLQP875GtWWlJ0XQAAASwAAAAAAAAAAAAAAAEAAAAAYw/SZQAAAABjD9XpAAAAAAAAAAMAAAABAAAAABkJobYe8Pfsy5wzxniqEKUTRdBaERqQSCi9Uhl9L7gmAAAACgAAABt0ZXN0YW5jaG9yLnN0ZWxsYXIub3JnIGF1dGgAAAAAAQAAAEByUzVNTnJ0TUtIVjhmTGtqR2hBNVNLQ0ZJOHRocDBENTA2SENNcCtHQXEraFpZV2tXY2gxR3hRcFlEVVJvVEtOAAAAAQAAAACpn2Fr7GAZ4XOcFvEz+xduBFDK1NDLQP875GtWWlJ0XQAAAAoAAAAPd2ViX2F1dGhfZG9tYWluAAAAAAEAAAAWdGVzdGFuY2hvci5zdGVsbGFyLm9yZwAAAAAAAQAAAABZ3eGdFRAiOmbG26ola88hdogAahoWrPJsyUWGGzka6gAAAAoAAAANY2xpZW50X2RvbWFpbgAAAAAAAAEAAAAeZGVtby13YWxsZXQtc2VydmVyLnN0ZWxsYXIub3JnAAAAAAAAAAAAAlpSdF0AAABAquMIQfSEgF25+hlwjfNPercuiYThTpC9PDQ3wDoKRqZm7FewFSEAn65kKiiMP7wgER+aKenN3NuUTaif7B9CBH0vuCYAAABAlmUlbIzXpAytgMhA7u+Y3S6wIEKtHvOABCAhomu/JsbuMHyUYrEJU2S2pSxK6YgJAryV5BqQsZJQdgpGUlnKDA\u003d\u003d"}
      """.trimMargin()

    val request =
      Request.Builder()
        .url("https://testanchor.stellar.org/auth")
        .header("Content-Type", "application/json; charset=utf-8")
        .post(postBody.toRequestBody(MEDIA_TYPE_JSON))
        .build()

    client.newCall(request).execute().use { response ->
      if (!response.isSuccessful) throw IOException("Unexpected code $response")

      println(response.body!!.string())
    }
  }
}
