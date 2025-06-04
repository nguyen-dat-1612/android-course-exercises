package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

object Riddle18 {
  /**
   * Return an Observable that mirrors either the [first] or [second] Observable depending on whoever emits or terminates first.
   *
   * Use case: You have multiple sources and want to get the data from either one and then be consistent and not switch between multiple sources.
   */
  fun solve(first: Observable<Int>, second: Observable<Int>): Observable<Int> {
    //ambArray: Cái nào hòan thành trước thì chỉ nhận giá trị của thằng đó
    return Observable.ambArray(first, second)
  }
}

fun main() {
  val first = Observable.intervalRange(1, 5, 100, 100, TimeUnit.MILLISECONDS)
    .map { it.toInt() }

  // Second phát ra giá trị trước first
  val second = Observable.intervalRange(10, 5, 50, 1000, TimeUnit.MILLISECONDS)
    .map { it.toInt() }

  Riddle18.solve(first, second).subscribe {
    println("Nhận: $it")
  }
  Thread.sleep(6000)
  println("Xong")
}
