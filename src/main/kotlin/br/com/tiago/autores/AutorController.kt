package br.com.tiago.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

@Validated
@Controller("/autor")
class AutorController(
    val autorRepository: AutorRepository
) {

    @Get
    @Transactional
    fun listar(@QueryValue(defaultValue = "") email: String): HttpResponse<List<DetalhesDoAutorResponse>> {
        if(email.isBlank()) {
            val autores: List<Autor> = autorRepository.findAll()
            val resposta: List<DetalhesDoAutorResponse> = autores.map { DetalhesDoAutorResponse(it) }

            return HttpResponse.ok(resposta)
        }
        val possivelAutor = autorRepository.findByEmail(email)
        if(possivelAutor.isEmpty()){
            return HttpResponse.notFound()
        }
        val resposta = DetalhesDoAutorResponse(possivelAutor.get())
        return HttpResponse.ok(listOf(resposta))
    }

    @Post
    @Transactional
    fun cadastrar(@Body @Valid request: CadastrarAutorRequest): HttpResponse<DetalhesDoAutorResponse> {
        val autor: Autor = request.paraAutor();
        autorRepository.save(autor)

        val uri = UriBuilder.of("/autor/{id}")
            .expand(mutableMapOf("id" to autor.id))

        return HttpResponse.created(DetalhesDoAutorResponse(autor), uri)
    }

    @Put("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long, @NotBlank descricao: String): HttpResponse<DetalhesDoAutorResponse>{
        val possivelAutor = autorRepository.findById(id)

        if(possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        val autor = possivelAutor.get()
        autor.descricao = descricao

        return HttpResponse.ok(DetalhesDoAutorResponse(autor))
    }

    @Delete("/{id}")
    @Transactional
    fun deletar(@PathVariable id: Long): HttpResponse<Unit> {
        val possivelAutor = autorRepository.findById(id)

        if(possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }

        autorRepository.deleteById(id)
        return HttpResponse.ok()
    }
}