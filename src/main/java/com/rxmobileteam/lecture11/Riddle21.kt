package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle21 {
  /**
   * Return the first emission of the [source] in a blocking fashion.
   *
   * Use case: Sometimes you can't do everything reactively and need to break out of it.
   */
  fun solve(source: Observable<Int>): Int {
    return source.blockingFirst()
  }
}
fun main() {
  val source = Observable.just(5, 10, 15)

  val result = Riddle21.solve(source)
  println("Nhận: $result")

  Thread.sleep(3000)
}
