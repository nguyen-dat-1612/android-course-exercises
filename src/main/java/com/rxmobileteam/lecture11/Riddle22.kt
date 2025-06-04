package com.rxmobileteam.lecture11

import io.reactivex.rxjava3.core.Observable

object Riddle22 {
  /**
   * Group emissions of the [source] always in a list of 2 elements and skip every third element.
   *
   * Use case: Group related data while skipping over some of it.
   */
  fun solve(source: Observable<Int>): Observable<List<Int>> {
    return source
      .buffer(3)
      .filter {it.size >= 2}
      .map { it.take(2) }
  }
}

fun main() {
  val source = Observable.just(1, 3, 5, 8, 9, 10, 1, 2, 5)
  Riddle22.solve(source)
    .subscribe { value ->
      println("Value: $value")
    }
}
