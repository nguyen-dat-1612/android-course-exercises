package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle12 {
  /**
   * In case the [source] Observable emits an error, don't emit the error and instead complete the Observable with a value of 5.
   *
   * Use case: Getting a network error and you want to recover and show some default state.
   */
  fun solve(source: Observable<Int>): Observable<Int> {
    return source.onErrorReturnItem(5)
  }
}

fun main() {
  val source = Observable.just(1, 2)
    .concatWith(Observable.error(Exception("Error")))

  Riddle12.solve(source).subscribe(
    { next -> println("Next: $next") },
    { error -> println("Error: ${error.message}") },
    { println("Completed") } // OnComplete
  )
}
