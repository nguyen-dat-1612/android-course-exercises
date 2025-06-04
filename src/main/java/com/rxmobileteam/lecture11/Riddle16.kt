package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

object Riddle16 {
  /**
   * For each emission of the [source] Observable use the [function] and return its value.
   * Dispose all previously non-terminated returned Singles from the [function] upon receiving a new emission from [source].
   *
   * Use case: The [source] Observable is a TextField and you want to issue a network request while disposing the old requests in case the user has typed something new.
   */
  fun solve(source: Observable<String>, function: (String) -> Single<Int>): Observable<Int> {
    return source
      .debounce(300, TimeUnit.MILLISECONDS)
      .distinctUntilChanged()
      .switchMapSingle { input -> function(input) }
  }
}

fun main() {
  val source = Observable.create<String> { emitter ->
    emitter.onNext("a")
    Thread.sleep(100)
    emitter.onNext("ab")
    Thread.sleep(100)
    emitter.onNext("abc")
    Thread.sleep(1000)
    emitter.onNext("abcd")
    Thread.sleep(100)
    emitter.onNext("abcde")
    emitter.onComplete()
  }

  fun fakeNetworkCall(input: String): Single<Int> {
    return Single.fromCallable {
        println("Start API call for: $input")
        input.length
      }
      .delay(500, TimeUnit.MILLISECONDS)
      .doOnSuccess {
        println("Finished API call for: $input → result: $it")
      }
  }

  val result = Riddle16.solve(source, ::fakeNetworkCall)

  result.subscribe(
    { println("Received result: $it") },
    { e -> println("Error: ${e.message}") },
    { println("Done!") }
  )

  Thread.sleep(3000)
}
