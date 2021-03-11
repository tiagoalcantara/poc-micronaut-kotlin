package br.com.tiago.autores

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository: JpaRepository<Autor, Long> {
    fun findByEmail(email: String): Optional<Autor>

    fun existsByEmail(email: String): Boolean

    @Query("SELECT a FROM autor a WHERE a.email = :email")
    fun buscaPorEmail(email: String): Optional<Autor>
}