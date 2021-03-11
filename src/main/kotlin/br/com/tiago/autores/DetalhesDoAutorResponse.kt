package br.com.tiago.autores

data class DetalhesDoAutorResponse(
    val nome: String,
    val email: String,
    val descricao: String,
) {
    constructor(autor: Autor) : this(autor.nome, autor.email, autor.descricao)
}
