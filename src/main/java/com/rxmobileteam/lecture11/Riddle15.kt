package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

object Riddle15 {
  /**
   * Concatenate the [first] Observable with the [second] while subscribing to both early.
   *
   * Use case: You have two sources of your data (cache & network request). You want to subscribe to both right away and keep the emission order.
   */
  fun solve(first: Observable<Int>, second: Observable<Int>): Observable<Int> {
    return second
      .replay()
      .autoConnect()
      .let { secondReplay ->
        Observable.concat(first, secondReplay)
      }
  }
}

fun main() {
  val first = Observable.just(1, 2).delay(1, TimeUnit.SECONDS)
  val second = Observable.just(3, 4).doOnSubscribe { println("Subscribed to second") }

  Riddle15.solve(first, second).subscribe(::println)

  Thread.sleep(2000)
}
