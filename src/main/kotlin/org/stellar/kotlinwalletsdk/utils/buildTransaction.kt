package org.stellar.kotlinwalletsdk.utils

import org.stellar.sdk.Network
import org.stellar.sdk.Operation
import org.stellar.sdk.Server
import org.stellar.sdk.Transaction

fun buildTransaction(
  sourceAddress: String,
  server: Server,
  network: Network,
  operations: List<Operation>
): Transaction {
  val sourceAccount =
    try {
      server.accounts().account(sourceAddress)
    } catch (e: Exception) {
      throw Error("Source account $sourceAddress does not exist")
    }

  // TODO: check sequence number
  // TODO: add memo
  // TODO: max fee
  // TODO: add time bounds

  return Transaction.Builder(sourceAccount, network)
    .addOperations(operations)
    .setBaseFee(500)
    .setTimeout(180)
    .build()
}
