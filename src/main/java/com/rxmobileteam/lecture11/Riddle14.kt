package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

object Riddle14 {
  /**
   * Try the given [source] up to three times unless an [IllegalArgumentException] has been emitted.
   *
   * Use case: Retry an operation for a number of times or until a valid error occurred.
   */
  fun solve(source: Single<Unit>): Single<Unit> {
    return source.retryWhen { errors ->
      errors
        .zipWith(Flowable.range(1, 3)) { error, retryCount -> Pair(error, retryCount) }
        .flatMap { (error, retryCount) ->
          when {
            error is IllegalArgumentException -> {
              Flowable.error<Unit>(error)
            }
            retryCount >= 3 -> {
              Flowable.error<Unit>(error)
            }
            else -> {
              Flowable.timer(100, TimeUnit.MILLISECONDS)
            }
          }
        }
    }
    }
}

fun main() {
  val attempt = AtomicInteger(0)

  val source1 = Single.create<Unit> { emitter ->
    val count = attempt.incrementAndGet()
    println("Attempt $count")
    emitter.onError(RuntimeException("Fail attempt $count"))
  }

  Riddle14.solve(source1)
    .subscribe(
      { println("Success!") },
      { e -> println("Error: ${e.message}") }
    )

  Thread.sleep(2000)
}
