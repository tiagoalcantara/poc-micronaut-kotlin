package br.com.tiago.pokemon

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client(value = "https://pokeapi.co/api/v2/")
interface PokemonClient {

    @Get(value = "/pokemon/{id}")
    fun buscar(@PathVariable id: Long): HttpResponse<PokemonClientResponse>
}