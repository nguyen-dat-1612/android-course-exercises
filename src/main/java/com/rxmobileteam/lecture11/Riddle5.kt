package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

object Riddle5 {
  /**
   * Sum up the latest values of [first] and [second] whenever one of the Observables emits a new value.
   *
   * Use case: Two input fields in a calculator that need to be summed up and the result should be updated every time one of the inputs change.
   */
  fun solve(first: Observable<Int>, second: Observable<Int>): Observable<Int> {
    return Observable.combineLatest(first, second) { t1, t2 -> t1 + t2 }
  }
}
fun main() {
  val first = Observable.intervalRange(1, 7, 0, 1, TimeUnit.SECONDS)
    .map { it.toInt() }
  val second = Observable.intervalRange(10, 3, 500, 1000, TimeUnit.MILLISECONDS)
    .map { it.toInt() }
  Riddle5.solve(first, second).subscribe { println(it) }

  Thread.sleep(10000)
}
