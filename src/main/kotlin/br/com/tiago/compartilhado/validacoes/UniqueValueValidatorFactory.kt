package br.com.tiago.compartilhado.validacoes
//
//import io.micronaut.context.annotation.Factory
//import io.micronaut.validation.validator.constraints.ConstraintValidator
//import javax.inject.Singleton
//import javax.persistence.EntityManager
//import javax.transaction.Transactional
//
//@Factory
//class UniqueValueValidatorFactory(
//    private val manager: EntityManager
//) {
//
//    @Singleton
//    @Transactional
//    fun uniqueValueValidator(): ConstraintValidator<UniqueValue, Any> {
//        return ConstraintValidator lambda@{ value, annotation, _ ->
//            if(value == null) {
//                return@lambda true
//            }
//
//            val parameters = annotation.convertibleValues.asMap()
//            val field = parameters["field"]
//            val entity = parameters["entity"]
//
//            val query = manager.createQuery("SELECT 1 FROM $entity WHERE $field = :value")
//            query.setParameter("value", value)
//            val result = query.resultList
//
//            result.isEmpty()
//        }
//    }
//}