package com.flatxph.base.configuration

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.ResourceUtils
import java.io.ByteArrayInputStream
import java.nio.file.Files

@Configuration
class StorageConfiguration {

    @Value("\${application.google.project}")
    private lateinit var project: String

    @Value("\${application.google.storage.path}")
    private lateinit var path: String

    @Bean
    fun getStorage(): Storage {
        val fileBytes = Files.readAllBytes(ResourceUtils.getFile(path).toPath())
        val credentials = GoogleCredentials.fromStream(ByteArrayInputStream(fileBytes))
        return StorageOptions.newBuilder().setCredentials(credentials).setProjectId(project).build().service
    }

}
