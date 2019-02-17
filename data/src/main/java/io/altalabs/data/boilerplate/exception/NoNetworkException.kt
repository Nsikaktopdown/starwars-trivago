package io.altalabs.data.boilerplate.exception

class NoNetworkException: Exception {
    constructor():  super("No internet connection")

    constructor(cause: Throwable): super(cause)
}