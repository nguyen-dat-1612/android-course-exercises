package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

object Riddle10 {
  /**
   * Use the [first] Observable and flatten it with the results of the [function] that returns an Observable.
   *
   * Use case: Get some user data and perform a network request with the user data and have both data accessible afterwards.
   */
  fun solve(first: Observable<Int>, function: (Int) -> Observable<String>): Observable<Pair<Int, String>> {
    return first.flatMap { it ->
      function(it).map { str ->
        Pair(it, str)
      }
    }
  }
}

fun main() {
  val first = Observable.intervalRange(1, 10, 0, 200, TimeUnit.MILLISECONDS).map { it.toInt() }
  val function: (Int) -> Observable<String> = { i ->
    Observable.just("Value $i")
  }

  Riddle10.solve(first, function).subscribe {
    println(it)
  }

  Thread.sleep(5000)
}
