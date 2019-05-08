package com.flatxph.base.configuration

import com.hazelcast.config.Config
import com.hazelcast.config.EvictionPolicy
import com.hazelcast.config.MapConfig
import com.hazelcast.config.MaxSizeConfig

fun buildHazelcastMapConfig(): MapConfig {
    return MapConfig().apply {
        name = "configuration"
        maxSizeConfig = MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE)
        evictionPolicy = EvictionPolicy.LRU
        timeToLiveSeconds = 3600
    }
}

fun buildHazelcastConfiguration(applicationName: String): Config {
    return Config().apply {
        instanceName = applicationName
        addMapConfig(buildHazelcastMapConfig())
    }
}
