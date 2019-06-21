package com.flatxph.base.configuration

import com.hazelcast.config.Config
import com.hazelcast.config.EvictionPolicy
import com.hazelcast.config.MapConfig
import com.hazelcast.config.MaxSizeConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Suppress("SpringFacetCodeInspection")
@Configuration
@EnableCaching
class HazelcastConfiguration {

    @Value("\${spring.application.name}")
    protected val applicationName: String? = null

    fun buildMapConfig() = MapConfig().apply {
        name = applicationName
        maxSizeConfig = MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        evictionPolicy = EvictionPolicy.LRU
        timeToLiveSeconds = 3600
    }

    @Bean
    fun defaultHazelcastConfiguration() = Config().apply {
        instanceName = applicationName
        groupConfig.name = applicationName
        addMapConfig(buildMapConfig())
    }
}
