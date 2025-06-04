package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

object Riddle9 {
  /**
   * As long as the [trigger] Observable does not emit an item, keep the [main] Observable alive.
   *
   * Use case: Cancel an Observable when something has happened. For instance, stop polling when the user has been logged out.
   */
  fun solve(main: Observable<Unit>, trigger: Observable<Unit>): Observable<Unit> {
    return trigger.switchMap {
        Observable.empty<Unit>()
      }
      .startWithItem(Unit)
      .switchMap {
        main
      }
  }
}

fun main() {
  val main = Observable.intervalRange(0, 10, 0, 200, TimeUnit.MILLISECONDS).map { Unit }
  val trigger = Observable.timer(700, TimeUnit.MILLISECONDS).map { Unit }

  Riddle9.solve(main, trigger).subscribe {
    println("Main emits at ${System.currentTimeMillis()}")
  }

  Thread.sleep(5000)
}
