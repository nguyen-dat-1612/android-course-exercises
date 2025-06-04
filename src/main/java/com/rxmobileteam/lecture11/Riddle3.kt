package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle3 {
  /**
   * Don't emit odd numbers from the [source] Observable.
   *
   * Use case: You want to filter certain items out.
   */
  fun solve(source: Observable<Int>): Observable<Int> {
     return source.filter{it % 2 == 0}
  }
}
fun main() {
  val source = Observable.just(1, 2, 3, 4, 5, 6)
  Riddle3.solve(source).subscribe { println(it) }
}


