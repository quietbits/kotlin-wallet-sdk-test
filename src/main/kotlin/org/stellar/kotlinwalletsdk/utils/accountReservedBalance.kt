package org.stellar.kotlinwalletsdk.utils

import java.math.BigDecimal
import org.stellar.kotlinwalletsdk.BASE_RESERVE
import org.stellar.kotlinwalletsdk.BASE_RESERVE_MIN_COUNT
import org.stellar.sdk.responses.AccountResponse

fun accountReservedBalance(account: AccountResponse): String {
  val subEntryCount = account.subentryCount.toBigDecimal()
  val numSponsoring = account.numSponsoring.toBigDecimal()
  val numSponsored = account.numSponsored.toBigDecimal()

  val buyingLiabilities =
    account.balances.find { it.assetType == "native" }?.buyingLiabilities?.get() ?: "0"

  return BigDecimal(BASE_RESERVE_MIN_COUNT)
    .plus(subEntryCount)
    .plus(numSponsoring)
    .minus(numSponsored)
    .times(BASE_RESERVE.toBigDecimal())
    .plus(buyingLiabilities.toBigDecimal())
    .toPlainString()
}
