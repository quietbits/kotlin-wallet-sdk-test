import org.stellar.sdk.Server

fun sync() {
  // Kotlin by default is synchronous/blocking, no need to do anything extra.
  // Every line of code will be executed sequentially.

  val server = Server("https://horizon-testnet.stellar.org")
  val accountOne = "GAS2M4BQAZTESHBRK4REF2QVJ3UP5SM773FJLE2T3ZKICYGUSVPC2LKN"
  val accountTwo = "GD2CJQ2Y5J7AM2VCVA4XANEJKHDJQZ5JZIVNFOIVNFH7DM3XUPUELR5N"

  fun asyncOne(): String {
    println("asyncOne :: 1")
    val account = server.accounts().account(accountOne)

    Thread().run {
      Thread.sleep(3000)
      println("asyncOne :: 2")
      return account.accountId
    }
  }

  fun asyncTwo(): String {
    println("asyncTwo :: 1")
    val account = server.accounts().account(accountTwo)

    Thread().run {
      Thread.sleep(4000)
      println("asyncTwo :: 2")
      return account.accountId
    }
  }

  fun testOneTwo() {
    println("testOneTwo :: 1")
    val one = asyncOne()
    println("testOneTwo :: 2")
    val two = asyncTwo()
    println("testOneTwo :: 3")

    val sum = "$one :: $two"

    println("testOneTwo :: 4")
    println("result: $sum")
  }

  testOneTwo()
}
