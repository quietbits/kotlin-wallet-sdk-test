import org.stellar.kotlinwalletsdk.Wallet
import org.stellar.sdk.Network
import org.stellar.sdk.Server

fun main() {
  println("MAIN")

  val wallet = Wallet("https://horizon-testnet.stellar.org", "Test SDF Network ; September 2015")
  val server = Server("https://horizon-testnet.stellar.org")
  val network = Network("Test SDF Network ; September 2015")

  val sourceAddress = "GBRXVHI5UITGMA5TKJIRZ7BOYRHHBHVJOCCKXXU6UVZ6EFBIHJKUEIHV"
  val sourceAddressSecret = "SCKEVIHDB5XOAOGIFC5YEC222X64LBP3VDL6BXCOUWOQB5PJAGYC6WHP"
  val feeBumpAddress = "GBIUOXTLEK7DNGB47MYLAB6GH4XTEL3MNRF4M5SMAQDN5R6FY6BVLCQN"
  val feeBumpAddressSecret = "SBQPLUOZURGK7DZDAT34TIMERMVBU4P6FZ5PHR7CR5WZQHMRXFVIXHL4"
  val newAccount = "GAW7QECBN2OI4LS4UUA3FO65Y2QPGQS3SPMTMRYK5ZX4IRZXXJPRWOBN"

  //  val testTx =
  //    wallet.fund(
  //      sourceAddress,
  //      newAccount,
  //      sponsorAddress = "GAW7QECBN2OI4LS4UUA3FO65Y2QPGQS3SPMTMRYK5ZX4IRZXXJPRWOBN"
  //    )
  //  testTx.sign(KeyPair.fromSecretSeed(sourceAddressSecret))

  //  val feeBumpTest = buildFeeBumpTransaction(feeBumpAddress, testTx, 600)
  //  println("txn xdr")
  //  println(feeBumpTest.toEnvelopeXdrBase64())
  //  feeBumpTest.sign(KeyPair.fromSecretSeed(feeBumpAddressSecret))
  //  val feeBumpXdr = feeBumpTest.toEnvelopeXdrBase64()
  //  val feeBumpFees = feeBumpTest.fee

  // Sign transaction
  //  feeBumpTest.sign(KeyPair.fromSecretSeed(sourceAddressSecret))

  //  val changeTrustTxn =
  //
  // "AAAAAgAAAABjep0doiZmA7NSURz8LsROcJ6pcISr3p6lc+IUKDpVQgAAAGQAD06tAAAAAQAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAABgAAAAFVU0RDAAAAAEI+fQXy7K+/7BkrIVo/G+lq7bjY5wJUq+NBPgIH3layf/////////8AAAAAAAAAAA=="
  //  val txn = Transaction.fromEnvelopeXdr(changeTrustTxn, network) as Transaction
  //
  //  // Submit transaction
  //  try {
  //    wallet.submitTransaction(txn)
  //  } catch (e: Exception) {
  //    println("ERROR: $e")
  //  }
}
