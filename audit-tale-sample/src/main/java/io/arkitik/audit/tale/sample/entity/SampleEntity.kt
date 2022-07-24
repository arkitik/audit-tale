package io.arkitik.audit.tale.sample.entity

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.domain.embedded.AuditChangeType
import io.arkitik.audit.tale.core.entity.embedded.ActorTypeImpl
import io.arkitik.audit.tale.engine.store.adapter.AuditableStoreImpl
import io.arkitik.audit.tale.engine.store.adapter.creator.CoreAuditStoreIdentityCreator
import io.arkitik.audit.tale.engine.store.adapter.updater.CoreAuditStoreIdentityUpdater
import io.arkitik.audit.tale.engine.store.core.log.AuditLogData
import io.arkitik.audit.tale.engine.store.core.log.AuditLogs
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.creator.StoreIdentityCreator
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 12:05 AM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface SampleIdentity : Identity<String> {
    val name: String
}

interface SampleEntityAuditIdentity : AuditRecordIdentity<String, SampleIdentity>

@Entity
data class SampleEntity(
    @Column(nullable = false)
    override val name: String,
    @Id
    override val uuid: String?,
    @Column(nullable = false)
    override val creationDate: LocalDateTime,
) : SampleIdentity

@Entity
data class SampleEntityAudit(
    @ManyToOne(optional = false)
    override val record: SampleEntity,
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
) : SampleEntityAuditIdentity

@Service
class SampleEntityStore(
    repository: SampleEntityRepository,
) : AuditableStoreImpl<String, SampleIdentity, SampleEntity, SampleEntityAuditIdentity>(
    repository
) {
    override fun SampleIdentity.map(): SampleEntity = this as SampleEntity
    override fun identityCreator(actorId: String, actorType: String) =
        SampleEntityCreator(actorId, actorType)

    override fun SampleIdentity.identityUpdater(
        actorId: String,
        actorType: String,
    ): CoreAuditStoreIdentityUpdater<String, SampleIdentity, SampleEntityAuditIdentity> {
        TODO("Not yet implemented")
    }
}

class SampleEntityCreator(
    actorId: String,
    actorType: String,
) : CoreAuditStoreIdentityCreator<String, SampleIdentity, SampleEntityAuditIdentity>(actorId, actorType) {
    private var uuid: String = UUID.randomUUID().toString().replace("-", "")
    private lateinit var name: String

    override fun String.uuid(): StoreIdentityCreator<String, AuditLogs<String, SampleIdentity, SampleEntityAuditIdentity>> {
        uuid = this
        return this@SampleEntityCreator
    }

    fun String.name(): SampleEntityCreator {
        name = this
        addHistoryRecord(
            AuditLogData(
                SampleIdentity::name.name,
                null,
                this
            )
        )
        return this@SampleEntityCreator
    }

    override fun SampleIdentity.createAudit(
        log: AuditLogData<String>,
        actorId: String,
        actorType: String,
    ) = SampleEntityAudit(
        this as SampleEntity,
        log.keyName,
        log.oldValue,
        log.newValue,
        actorId,
        ActorTypeImpl(actorType),
        AuditChangeType.CREATE
    )

    override fun createIdentity(): SampleEntity {
        return SampleEntity(
            name,
            uuid,
            LocalDateTime.now()
        )
    }
}
