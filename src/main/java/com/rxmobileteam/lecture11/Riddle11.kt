package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

object Riddle11 {
  /**
   * Let the first emission of the [source] within a time window of 300ms travel downstream but don't emit any other events until the next time window.
   *
   * Use case: Handle the click of a button right away but prevent double clicking by not handling multiple click events within a given time window.
   */
  fun solve(source: Observable<Unit>): Observable<Unit> {
    return source.throttleFirst(300, java.util.concurrent.TimeUnit.MILLISECONDS)
  }
}

fun main() {
  val start = System.currentTimeMillis()
  val source = Observable.create<Unit> { emitter ->
    emitter.onNext(Unit)
    Thread.sleep(100)
    emitter.onNext(Unit)
    Thread.sleep(100)
    emitter.onNext(Unit)
    Thread.sleep(100)
    emitter.onNext(Unit)
    Thread.sleep(100)
    emitter.onNext(Unit)
    Thread.sleep(100)
    emitter.onNext(Unit)
    Thread.sleep(100)
    emitter.onNext(Unit)
    Thread.sleep(100)
    emitter.onNext(Unit)
    emitter.onComplete()
  }
  Riddle11.solve(source).subscribe {
    println("Emitted $it at ${System.currentTimeMillis() - start} ms")
  }
  Thread.sleep(2000)
}
