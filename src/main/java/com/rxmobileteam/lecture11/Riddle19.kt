package com.rxmobileteam.lecture11

import com.rxmobileteam.utils.ExerciseNotCompletedException
import io.reactivex.rxjava3.core.Observable

object Riddle19 {
  /**
   * Use the given [Interaction] interface and use its listener to transform the [Int] callback to an Observable that emits every time the listener is called.
   * When the Observable is being disposed the listener should be set to null.
   *
   * Use case: Transform any listener into an Observable.
   */
  fun solve(interaction: Interaction): Observable<Int> {
    return Observable.create { emitter ->
      // Gán listener mới
      val callback: (Int) -> Unit = { value ->
        if (!emitter.isDisposed) {
          emitter.onNext(value)
        }
      }

      interaction.listener = callback

      // Khi Observable bị dispose, gỡ listener
      emitter.setCancellable {
        interaction.listener = null
      }
    }
  }

  interface Interaction {
    var listener: ((Int) -> Unit)?
  }
}

fun main() {
  val interaction = object : Riddle19.Interaction {
    override var listener: ((Int) -> Unit)? = null
  }

  val disposable = Riddle19.solve(interaction)
    .subscribe { value ->
      println("Nhận: $value")
    }

  interaction.listener?.invoke(1)
  interaction.listener?.invoke(2)

  println("Gọi dispose")
  disposable.dispose()

  println("Gọi listener khi đã dispose")
  interaction.listener?.invoke(3)

  println("Kết thúc");
}
