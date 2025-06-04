package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle23 {
  /**
   * Cast each emission of the [source] from [Any] to [String].
   *
   * Use case: You get some data from a bad source and know for sure it's of a certain type that you require.
   */
  fun solve(source: Observable<Any>): Observable<String> {
    return source.map {
      println("Loại: ${it::class.simpleName} và giá trị: $it")
      it.toString()
    }
  }
}

fun main() {
  val source = Observable.fromArray<Any>("Dat", "Thanh", "Nguyen", 1, 2, 31313, 121)

  Riddle23.solve(source).subscribe(
    { println("Loại: ${it::class.simpleName} và giá trị: $it") },
    { error -> println("Error: ${error.message}") }
  )
}
