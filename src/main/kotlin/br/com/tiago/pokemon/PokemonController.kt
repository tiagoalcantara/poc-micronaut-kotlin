package br.com.tiago.pokemon

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Produces

@Controller("/pokemon")
class PokemonController(
    private val client: PokemonClient
) {

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    fun buscar(@PathVariable id: Long): MutableHttpResponse<PokemonResponse>? {
        val resposta = client.buscar(id).body()?.name?.let { pokemonNome ->
            PokemonResponse(pokemonNome)
                .also { println("Pokémon encontrado: $it") }
                .let { HttpResponse.ok(it) }
        }

        return resposta ?: HttpResponse.notFound()
    }

}