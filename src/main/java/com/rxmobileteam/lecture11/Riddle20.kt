package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle20 {
  /**
   * Merge the [first] and [second] Observable together.
   *
   * Use case: There something you want to execute and in your UI you have multiple trigger points.
   */
  fun solve(first: Observable<Int>, second: Observable<Int>): Observable<Int> {
    return Observable.merge(first, second)
  }
}

fun main() {
  val first = Observable.just(1, 3, 5)
  val second = Observable.just(2, 4, 6)

  Riddle20.solve(first, second)
    .subscribe { value ->
      println("Received: $value")
    }

  Thread.sleep(2000)
}
