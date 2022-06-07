package io.arkitik.audit.tale.core.entity

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.domain.embedded.AuditChangeType
import io.arkitik.audit.tale.core.entity.embedded.ActorTypeImpl
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:51 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
@MappedSuperclass
class AuditRecord<O>(
    @ManyToOne(optional = false)
    open override val record: O,
    @Column(nullable = false, updatable = false)
    override val keyName: String,
    @Column(updatable = false)
    override val oldValue: String?,
    @Column(updatable = false)
    override val newValue: String?,
    @Column(nullable = false, updatable = false)
    override val actorId: String,
    @Embedded
    override val actorType: ActorTypeImpl,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    override val changeType: AuditChangeType,
    @Id
    override val uuid: String = UUID.randomUUID().toString().replace("-", ""),
    @Column(nullable = false, updatable = false)
    override val creationDate: LocalDateTime = LocalDateTime.now(),
) : AuditRecordIdentity<O>
