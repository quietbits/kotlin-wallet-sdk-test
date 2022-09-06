import org.stellar.kotlinwalletsdk.Auth
import org.stellar.sdk.KeyPair
import org.stellar.sdk.Transaction

fun main() {
  // "https://testanchor.stellar.org/auth?account=GAMQTINWD3YPP3GLTQZ4M6FKCCSRGROQLIIRVECIFC6VEGL5F64CND22&home_domain=testanchor.stellar.org&client_domain=demo-wallet-server.stellar.org"

  // GAMQTINWD3YPP3GLTQZ4M6FKCCSRGROQLIIRVECIFC6VEGL5F64CND22
  // SAICIEF6C7ENEPUCFYU5YE7CF6Z2BXUCYQ4MNB3RK3MMZ4GECYFJBWZ3

  fun signTxn(txn: Transaction): Transaction {
    txn.sign(KeyPair.fromSecretSeed("SAICIEF6C7ENEPUCFYU5YE7CF6Z2BXUCYQ4MNB3RK3MMZ4GECYFJBWZ3"))
    return txn
  }

  val auth =
    Auth(
        "GAMQTINWD3YPP3GLTQZ4M6FKCCSRGROQLIIRVECIFC6VEGL5F64CND22",
        "https://testanchor.stellar.org/auth",
        "testanchor.stellar.org",
        signTransaction = ::signTxn,
        clientDomain = "demo-wallet-server.stellar.org"
      )
      .authenticate()

  val testUri1 = "https://testanchor.stellar.org"
  val testUri2 = "http://testanchor.stellar.org:456"

  //  val endpoint = "https://testanchor.stellar.org/auth"
  //  // signing key
  //  val serverAccount = "GCUZ6YLL5RQBTYLTTQLPCM73C5XAIUGK2TIMWQH7HPSGWVS2KJ2F3CHS"
  //  val walletAccount = "GAMQTINWD3YPP3GLTQZ4M6FKCCSRGROQLIIRVECIFC6VEGL5F64CND22"
  //  val signingKeys = "SAICIEF6C7ENEPUCFYU5YE7CF6Z2BXUCYQ4MNB3RK3MMZ4GECYFJBWZ3"
  //
  //  val sep10 = Sep10Client(endpoint, serverAccount, walletAccount, signingKeys)
  //  val test = sep10.auth()
  //
  //  println(test)

}
