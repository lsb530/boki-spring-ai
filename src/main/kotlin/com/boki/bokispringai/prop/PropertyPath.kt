package com.boki.bokispringai.prop

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding
import org.springframework.core.annotation.AliasFor

@Target(AnnotationTarget.CLASS, AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@ConstructorBinding
@ConfigurationProperties(prefix = "")
annotation class PropertyPath(

    @get:AliasFor(annotation = ConfigurationProperties::class, attribute = "prefix")
    val value: String

)