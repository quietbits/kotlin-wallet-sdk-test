package org.stellar.kotlinwalletsdk.utils

import org.stellar.sdk.*

fun sponsorOperation(
  sponsorAddress: String,
  accountAddress: String,
  operation: Operation
): List<Operation> {
  return listOfNotNull(
    // Start reserve sponsoring
    BeginSponsoringFutureReservesOperation.Builder(accountAddress)
      .setSourceAccount(sponsorAddress)
      .build(),
    // Operation to sponsor
    operation,
    // End reserve sponsoring
    EndSponsoringFutureReservesOperation(accountAddress)
  )
}
