package io.heapy.tgto.coroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import org.telegram.telegrambots.meta.updateshandlers.SentCallback
import java.io.Serializable

/**
 * Bridge which converts callback based method, to coroutines based.
 *
 * @author Ruslan Ibragimov
 */
suspend fun <T : Serializable, Method : BotApiMethod<T>> AbsSender.coExecute(method: Method): T {
    return suspendCancellableCoroutine { cont: CancellableContinuation<T> ->
        executeAsync(method, object : SentCallback<T> {
            override fun onResult(method: BotApiMethod<T>, response: T) {
                cont.resumeWith(Result.success(response))
            }

            override fun onException(method: BotApiMethod<T>, exception: Exception) {
                cont.resumeWith(Result.failure(exception))
            }

            override fun onError(method: BotApiMethod<T>, apiException: TelegramApiRequestException) {
                cont.resumeWith(Result.failure(apiException))
            }
        })
    }
}
