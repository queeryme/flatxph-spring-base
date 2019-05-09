package com.flatxph.base.configuration

import com.hazelcast.config.Config
import com.hazelcast.config.EvictionPolicy
import com.hazelcast.config.MapConfig
import com.hazelcast.config.MaxSizeConfig
import org.springframework.beans.factory.annotation.Value

/**
 * Override hazelcastConfiguration for auto hazelcast configuration.
 * It should look like this:
 * ```
 * @EnableCaching
 * @Configuration
 * class HazelcastConfiguration {
 *      @Bean
 *      override fun hazelcastConfiguration(): Config = super.hazelcastConfiguration()
 * }
 * ```
 */
abstract class BaseHazelcastConfiguration {

    @Value("\${spring.application.name}")
    protected val applicationName: String? = null

    open fun buildMapConfig() = MapConfig().apply {
        name = applicationName
        maxSizeConfig = MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        evictionPolicy = EvictionPolicy.LRU
        timeToLiveSeconds = 3600
    }

    open fun buildHazelcastConfig() = Config().apply {
        instanceName = applicationName
        groupConfig.name = applicationName
        addMapConfig(buildMapConfig())
    }
}
