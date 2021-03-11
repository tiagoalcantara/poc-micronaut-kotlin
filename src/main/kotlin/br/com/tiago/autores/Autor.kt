package br.com.tiago.autores

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Autor(
    val nome: String,
    val email: String,
    var descricao: String,
) {
    @Id @GeneratedValue
    var id: Long? = null
        private set

    var criadoEm: LocalDateTime = LocalDateTime.now()
        private set
}