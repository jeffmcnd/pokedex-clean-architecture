package xyz.mcnallydawes.pokedex.boundary

import xyz.mcnallydawes.pokedex.Try

interface Interactor<Request, Response> {
    fun execute(request: Request) : Try<Response>
}