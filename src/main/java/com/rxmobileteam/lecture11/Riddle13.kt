package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle13 {
  /**
   * When the [source] emits the same value as it did last time, don't allow it to travel downstream.
   *
   * Use case: You only want to observe changes of a value but don't care if the same value has been emitted consecutively.
   */
  fun solve(source: Observable<Int>): Observable<Int> {
    return source.distinctUntilChanged()
  }
}


fun main() {
  val source = Observable.just(1, 1, 2, 2, 3, 1, 1, 4)
  Riddle13.solve(source).subscribe { println(it) }
}
