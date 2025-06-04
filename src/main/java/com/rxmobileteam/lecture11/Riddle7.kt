package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle7 {
  /**
   * When the [source] emits the same value multiple times, only allow the first value to travel downstream.
   *
   * Use case: You never want to show the same value twice.
   */
  fun solve(source: Observable<Int>): Observable<Int> {
    return source.distinctUntilChanged()
  }
}

fun main() {
  val source = Observable.just(1, 2, 2, 4, 5, 5, 6, 6, 7, 1)
  Riddle7.solve(source).subscribe { item -> println("item: $item") }
  Thread.sleep(3000)
}
