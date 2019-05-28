package com.flatxph.base.configuration

import org.hibernate.search.jpa.Search
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
abstract class BaseHibernateSearchConfiguration : ApplicationListener<ContextRefreshedEvent> {

    @PersistenceContext
    protected val entityManager: EntityManager? = null

    @Transactional
    open fun buildConfiguration(event: ContextRefreshedEvent) {
        val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)
        try {
            println("Started Creating Index for Hibernate Search")
            fullTextEntityManager.createIndexer().startAndWait()
            println("Done Creating Index for Hibernate Search")
        } catch (e: InterruptedException) {
            println("Error occurred trying to build Hibernate Search indexes $e")
        }

    }

}
