package xyz.mcnallydawes.domain.boundary

import xyz.mcnallydawes.domain.Try

interface Interactor<Request, Response> {
    fun execute(request: Request) : Try<Response>
}