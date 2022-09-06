package org.stellar.kotlinwalletsdk

import java.io.IOException
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import org.stellar.kotlinwalletsdk.utils.GsonUtils
import org.stellar.kotlinwalletsdk.utils.OkHttpUtils
import org.stellar.sdk.Network
import org.stellar.sdk.Operation
import org.stellar.sdk.Transaction

class Auth(
  private val accountAddress: String,
  private val authEndpoint: String,
  private val homeDomain: String,
  private val signTransaction: (Transaction) -> Transaction,
  private val clientDomain: String? = null,
  private val networkPassPhrase: String = Network.TESTNET.toString()
) {
  private val gson = GsonUtils.instance!!
  private val okHttpClient = OkHttpClient()

  // Data classes
  data class ChallengeResponse(val transaction: String, val network_passphrase: String)
  data class AuthToken(val token: String)
  data class AuthTransaction(val transaction: String)

  fun authenticate(): String {
    val challengeTxn = challenge()
    val signedTxn = sign(challengeTxn)
    val token = getToken(signedTxn)

    // TODO: should it return token?
    println("token")
    println(token)
    println("  ")

    return token
  }

  private fun challenge(): ChallengeResponse {
    val endpoint = authEndpoint.toHttpUrl()
    val authURL = HttpUrl.Builder().scheme("https").host(endpoint.host)

    // Add path segments, if there are any
    endpoint.pathSegments.forEach { authURL.addPathSegment(it) }

    // Add required query params
    authURL
      .addQueryParameter("account", accountAddress)
      .addQueryParameter("home_domain", homeDomain)

    if (!clientDomain.isNullOrBlank()) {
      authURL.addQueryParameter("client_domain", clientDomain)
    }

    authURL.build()

    // TODO: remove print
    println("authURL")
    println(authURL)

    val request = OkHttpUtils.buildStringGetRequest(authURL.toString())

    okHttpClient.newCall(request).execute().use { response ->
      // TODO: this repeats in a few places
      if (!response.isSuccessful) throw IOException("Request failed: $response")

      val jsonResponse: ChallengeResponse =
        gson.fromJson(response.body!!.charStream(), ChallengeResponse::class.java)

      if (jsonResponse.transaction.isBlank()) {
        throw Exception("The response did not contain a transaction")
      }

      if (jsonResponse.network_passphrase != networkPassPhrase) {
        throw Exception("Networks don't match")
      }

      return jsonResponse
    }
  }

  private fun sign(challengeResponse: ChallengeResponse): Transaction {
    var challengeTxn =
      Transaction.fromEnvelopeXdr(
        challengeResponse.transaction,
        Network(challengeResponse.network_passphrase)
      ) as Transaction

    val clientDomainOperation =
      challengeTxn.operations.firstOrNull {
        it.toXdr().body?.manageDataOp?.dataName?.string64.toString() == "client_domain"
      }

    if (clientDomainOperation != null) {
      challengeTxn = signClientDomain(clientDomainOperation, challengeResponse)
    }

    signTransaction(challengeTxn)

    return challengeTxn
  }

  private fun signClientDomain(
    operation: Operation,
    challengeResponse: ChallengeResponse
  ): Transaction {
    val clientDomainFromOp =
      operation.toXdr().body.manageDataOp.dataValue.dataValue.toString(Charsets.UTF_8)

    val clientDomainUrl =
      HttpUrl.Builder().scheme("https").host(clientDomainFromOp).addPathSegment("sign").build()

    val clientDomainRequestParams =
      ChallengeResponse(challengeResponse.transaction, challengeResponse.network_passphrase)

    val clientDomainRequest =
      OkHttpUtils.buildJsonPostRequest(clientDomainUrl.toString(), clientDomainRequestParams)

    okHttpClient.newCall(clientDomainRequest).execute().use { response ->
      if (!response.isSuccessful) throw IOException("Request failed: $response")

      val jsonResponse: ChallengeResponse =
        gson.fromJson(response.body!!.charStream(), ChallengeResponse::class.java)

      if (jsonResponse.transaction.isBlank()) {
        throw Exception("The response did not contain a transaction")
      }

      return Transaction.fromEnvelopeXdr(
        jsonResponse.transaction,
        Network(jsonResponse.network_passphrase)
      ) as Transaction
    }
  }

  private fun getToken(signedTransaction: Transaction): String {
    val signedChallengeTxnXdr = signedTransaction.toEnvelopeXdrBase64()
    // TODO: better names
    val tokenRequestParams = AuthTransaction(signedChallengeTxnXdr)

    val tokenRequest = OkHttpUtils.buildJsonPostRequest(authEndpoint, tokenRequestParams)

    okHttpClient.newCall(tokenRequest).execute().use { response ->
      if (!response.isSuccessful) throw IOException("Request failed: $response")

      val jsonResponse: AuthToken =
        gson.fromJson(response.body!!.charStream(), AuthToken::class.java)

      if (jsonResponse.token.isBlank()) {
        throw Exception("Token was not returned")
      }

      return jsonResponse.token
    }
  }
}