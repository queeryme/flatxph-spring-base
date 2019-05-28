package com.flatxph.base.configuration

import org.hibernate.search.jpa.Search
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
abstract class BaseHibernateSearchConfiguration : ApplicationListener<ContextRefreshedEvent> {

    @Value("\${spring.jpa.properties.hibernate.search.async:false}")
    protected val async: Boolean = false

    @PersistenceContext
    protected val entityManager: EntityManager? = null

    @Transactional
    open fun buildConfiguration(event: ContextRefreshedEvent) {
        val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)
        try {
            println("Started Creating Index for Hibernate Search")
            val indexer = fullTextEntityManager.createIndexer()
            indexer.apply { if (async) start() else startAndWait() }
            println("Done Creating Index for Hibernate Search")
        } catch (e: InterruptedException) {
            println("Error occurred trying to build Hibernate Search indexes $e")
        }

    }

}