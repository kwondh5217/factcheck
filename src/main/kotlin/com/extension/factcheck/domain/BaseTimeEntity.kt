package com.extension.factcheck.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serial
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity : Serializable {
    @CreatedDate
    lateinit var dateCreated: LocalDateTime

    @LastModifiedDate
    lateinit var dateUpdated: LocalDateTime

    companion object {
        @Serial
        private const val serialVersionUID: Long = -1039316944866564968L
    }
}
