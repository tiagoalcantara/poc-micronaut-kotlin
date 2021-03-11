package br.com.tiago.compartilhado.validacoes

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Singleton
open class UniqueValueValidator(
    private val manager: EntityManager
) : ConstraintValidator<UniqueValue, Any> {
    @Transactional
    override fun isValid(value: Any?,annotationMetadata: AnnotationValue<UniqueValue>, context: ConstraintValidatorContext): Boolean {
       if(value == null) return true

        return annotationMetadata.convertibleValues.asMap().let { annotationValues ->
            val modelClass = annotationValues["modelClass"]
            val field = annotationValues["field"]

            manager
                .createQuery("SELECT 1 FROM $modelClass WHERE $field = :value")
                .setParameter("value", value)
                .resultList.isEmpty()
        }
    }

}