package org.stellar.kotlinwalletsdk.utils

import org.stellar.sdk.FeeBumpTransaction
import org.stellar.sdk.Transaction

fun buildFeeBumpTransaction(
  feeAccount: String,
  innerTransaction: Transaction,
  maxFee: Long
): FeeBumpTransaction {
  return FeeBumpTransaction.Builder(innerTransaction)
    .setBaseFee(maxFee)
    .setFeeAccount(feeAccount)
    .build()
}
