package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Single
import kotlin.random.Random

object Riddle17 {
  /**
   * Return a Single that emits the value from the given [function] when being subscribed to.
   *
   * Use case: Reactive types are lazy by default. Hence, you might also want to get the value upon the subscription and not execution time.
   */
  fun solve(function: () -> Int): Single<Int> {
    return Single.defer {
      Single.just(function())
    }
//    return Single.just(function())
  }
}
fun main() {
  println("Gọi phương thức")

  val single = Riddle17.solve {
    println("Function được gọi")
    Random.nextInt()
  }

  println("Bắt đầu chờ đợi có subscribe gọi")

  Thread.sleep(2000)

  println("Đăng ký subscribe lần 1")
  single.subscribe { result ->
    println("Received: $result")
  }

  Thread.sleep(2000)

  println("Đăng ký subscribe lần 2")
  single.subscribe { result ->
    println("Received: $result")
  }
}
