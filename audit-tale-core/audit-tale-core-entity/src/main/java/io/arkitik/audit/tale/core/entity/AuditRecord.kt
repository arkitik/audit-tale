package io.arkitik.audit.tale.core.entity

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.domain.embedded.AuditChangeType
import io.arkitik.audit.tale.core.entity.embedded.ActorTypeImpl
import io.arkitik.radix.develop.identity.Identity
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:51 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
@MappedSuperclass
@Deprecated("Will be removed on future releases, use your own audit-tale entity implementation instead")
class AuditRecord<ID : Serializable, I : Identity<ID>>(
    @ManyToOne(optional = false)
    open override val record: I,
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
) : AuditRecordIdentity<ID, I>
