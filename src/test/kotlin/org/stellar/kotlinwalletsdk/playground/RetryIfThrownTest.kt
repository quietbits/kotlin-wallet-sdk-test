package org.stellar.kotlinwalletsdk.playground

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RetryIfThrownTest {
  //  @Test
  //  fun `it should not retry`() {
  //    var tries = 0
  //    val block: () -> Unit = { tries++ }
  //    retryIfThrown(3, RuntimeException::class, block)
  //    kotlin.test.assertEquals(tries, 1)
  //  }

  @Test
  fun `it should retry twice`() {
    var tries = 0
    val block = {
      tries++
      if (tries < 2) {
        throw RuntimeException()
      }
    }
    retryIfThrown(3, RuntimeException::class, block)
    kotlin.test.assertEquals(tries, 2)
  }
  //
  //  @Test
  //  fun `it should retry 3 times then rethrow the exception`() {
  //    var tries = 0
  //    val block = {
  //      tries++
  //      throw RuntimeException()
  //    }
  //    try {
  //      retryIfThrown(3, RuntimeException::class, block)
  //    } catch (e: Throwable) {
  //      assertEquals(tries, 3)
  //      throw e
  //    }
  //  }
  //
  //  @Test
  //  fun `it should not retry if exception is not of expected type`() {
  //    var tries = 0
  //    val block = {
  //      tries++
  //      throw IllegalArgumentException()
  //    }
  //    try {
  //      retryIfThrown(3, RuntimeException::class, block)
  //    } catch (e: Throwable) {
  //      assertEquals(tries, 1)
  //      throw e
  //    }
  //  }
}
