package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle4 {
  /**
   * Implement a toggle mechanism. Initially we want to return false.
   * Every time [source] emits, we want to negate the previous value.
   *
   * Use case: Some button that can toggle two states. For instance a switch between White & Dark theme.
   */
  fun solve(source: Observable<Unit>): Observable<Boolean> {
    return source.scan(false) {
      prev, _ -> !prev
    }.startWithItem(false)
  }

}

fun main() {
  val source = Observable.just(Unit, Unit, Unit, Unit) // 4 event phát liên tiếp
  Riddle4.solve(source).subscribe { println(it) }
  Thread.sleep(2000)
}
