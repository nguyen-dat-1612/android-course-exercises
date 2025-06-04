package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle2 {
  /**
   * Increment each emitted value of the given [source] by 1.
   *
   * Use case: You want to transform the data.
   */
  fun solve(source: Observable<Int>): Observable<Int> {
    return source.map {it + 1}
  }
}

fun main() {
  Riddle2.solve(Observable.just(10))
    .test()
    .assertResult(11)

  Riddle2.solve(Observable.just(10))
    .materialize()
    .subscribe(::println)

  println("NICE WORK!")
}
