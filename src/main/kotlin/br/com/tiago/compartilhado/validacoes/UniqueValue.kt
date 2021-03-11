package br.com.tiago.compartilhado.validacoes

import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueValueValidator::class])
annotation  class UniqueValue(
    val message: String = "must be unique ({validatedValue})",
    val field: String,
    val modelClass: KClass<out Any>,
)
