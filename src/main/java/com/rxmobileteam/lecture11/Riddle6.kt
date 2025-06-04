package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Single

object Riddle6 {
  /**
   * Execute both [first] and [second] Single's in parallel and provide both results as a pair.
   * Assume both [first] and [second] will execute on a different thread already.
   * This is a slightly simpler version of [Riddle102], where no schedulers are applied by default.
   *
   * Use case: Execute two network requests in parallel and wait for each other and process the combined data.
   */
  fun solve(first: Single<Int>, second: Single<Int>): Single<Pair<Int, Int>> {
    return Single.zip(first, second) { t1, t2 -> Pair(t1, t2) }
  }
}

fun main() {
  val first = Single.just(1)
  val second = Single.just(2)

  Riddle6.solve(first, second)
    .subscribe { pair -> println("First: ${pair.first}, Second: ${pair.second}") }

  Thread.sleep(2000)
}
