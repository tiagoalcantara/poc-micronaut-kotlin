package br.com.tiago.autores

import br.com.tiago.compartilhado.validacoes.UniqueValue
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class CadastrarAutorRequest(
    @field:NotBlank
    val nome: String,
    @field:NotBlank @field:Email @field:UniqueValue(field = "email", modelClass = Autor::class)
    val email: String,
    @field:NotBlank @field:Size(max = 400)
    val descricao: String,
) {
    fun paraAutor(): Autor {
        return Autor(nome, email, descricao)
    }
}