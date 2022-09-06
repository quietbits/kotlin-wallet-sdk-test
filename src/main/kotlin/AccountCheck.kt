import java.lang.Exception
import org.stellar.kotlinwalletsdk.Wallet
import org.stellar.kotlinwalletsdk.utils.accountAvailableNativeBalance
import org.stellar.kotlinwalletsdk.utils.accountReservedBalance
import org.stellar.sdk.Server
import org.stellar.sdk.responses.AccountResponse

fun accountCheck() {
  val wallet = Wallet("https://horizon-testnet.stellar.org", "Test SDF Network ; September 2015")
  val server = Server("https://horizon-testnet.stellar.org")

  val fullAccount = "GC5UMI2LAP4XF677VIN2EG7WRLPPPUHCE24JA6GTKETFT5IRJCTXJIFS"
  // 9k+ XLM
  // SBPHTJOASIPEJYX3EC77Z2X5SWXPAYUQISDKXZHWENXIXCQGE7TRJDKK
  val activeAccount = "GAFRGFVBQHOLZBTDF75FHLECSJB547NFUGZZ4WG756FALNUYITK7R4JB"
  // 1 XLM
  // SCIX7ROKYGYZ4BJWAQ2JURCQKZDEAUFQQ4WB2Y4T6VFFIF4DIE4JYDAJ
  val inactiveAccount = "GBKJ65HYIFJCQUFGFSRECLNJSXPRIC77BZGI7RM4CXFRBRKPPRMIG7BI"
  // SBD6V4P4AOSEYGV7S6W3VORD55GSGKI2G2HNB33CEXXM6G7KT2F27YIN

  val accountFull: AccountResponse? =
    try {
      server.accounts().account(fullAccount)
    } catch (e: Error) {
      null
    }

  if (accountFull != null) {
    val accountFullReserve = accountReservedBalance(accountFull)
    val accountFullAvailable = accountAvailableNativeBalance(accountFull)

    println(">>> full account bal: $accountFullReserve | $accountFullAvailable")
  }

  println(">>> full account: $accountFull")

  val accountActive: AccountResponse? =
    try {
      server.accounts().account(activeAccount)
    } catch (e: Error) {
      null
    }

  if (accountActive != null) {
    val accountActiveReserve = accountReservedBalance(accountActive)
    val accountActiveAvailable = accountAvailableNativeBalance(accountActive)

    println(">>> active account bal: $accountActiveReserve | $accountActiveAvailable")

    val test = accountActiveAvailable.toDouble()

    if (accountActiveAvailable == "0") {
      println("NO BALANCE")
    }

    println("test: $test")
  }

  println(">>> active account: $accountActive")

  val accountInactive: AccountResponse? =
    try {
      server.accounts().account(inactiveAccount)
    } catch (e: Exception) {
      null
    }

  println(">>> inactive account: $accountInactive")
}
