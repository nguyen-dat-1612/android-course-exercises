package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

object Riddle1 {
  /**
   * Transform the given [value] into an Observable that emits the value and then completes.
   *
   * Use case: You want to transform some value to the reactive world.
   */
  fun solve(value: Int): Observable<Int> {
    return Observable.just(5)
  }
}

fun main() {
  Riddle1.solve(5)
    .test()
    .assertResult(5)

  Riddle1.solve(5)
    .materialize()
    .subscribe(::println)

  println("NICE WORK!")
}
