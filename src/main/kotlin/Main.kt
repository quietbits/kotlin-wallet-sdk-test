import org.stellar.sdk.Network
import org.stellar.kotlinwalletsdk.Wallet

fun main() {
    println("MAIN")

    val wallet = Wallet("https://horizon-testnet.stellar.org", Network.TESTNET.toString())

    val newAccountKeypair = wallet.create()
    println(newAccountKeypair)
}