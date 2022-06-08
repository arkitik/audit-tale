package io.arkitik.audit.tale.sample.entity

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.domain.embedded.AuditChangeType
import io.arkitik.audit.tale.core.entity.AuditRecord
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
@Entity
data class SampleEntity(
    @Column(nullable = false)
    val name: String,
    @Id
    override val uuid: String?,
    @Column(nullable = false)
    override val creationDate: LocalDateTime,
) : Identity<String>

@Entity
data class SampleEntityAudit(
    @ManyToOne(optional = false)
    override val record: SampleEntity,
    override val keyName: String,
    override val oldValue: String?,
    override val newValue: String?,
    override val actorId: String,
    @Embedded
    override val actorType: ActorTypeImpl,
    override val changeType: AuditChangeType,
) : AuditRecord<String, SampleEntity>(
    record,
    keyName,
    oldValue,
    newValue,
    actorId,
    actorType,
    changeType,
)

@Service
class SampleEntityStore(
    repository: SampleEntityRepository,
) : AuditableStoreImpl<String, SampleEntity, SampleEntity>(
    repository) {
    override fun SampleEntity.map() = this
    override fun identityCreator(actorId: String, actorType: String) =
        SampleEntityCreator(actorId, actorType)

    override fun SampleEntity.identityUpdater(
        actorId: String,
        actorType: String,
    ): CoreAuditStoreIdentityUpdater<String, SampleEntity> {
        TODO("Not yet implemented")
    }
}

class SampleEntityCreator(
    actorId: String,
    actorType: String,
) : CoreAuditStoreIdentityCreator<String, SampleEntity>(actorId, actorType) {
    private var uuid: String = UUID.randomUUID().toString().replace("-", "")
    private lateinit var name: String

    override fun String.uuid(): StoreIdentityCreator<String, AuditLogs<String, SampleEntity>> {
        uuid = this
        addHistoryRecord(AuditLogData(
            "uuid",
            null,
            this
        ))
        return this@SampleEntityCreator
    }

    fun String.name(): SampleEntityCreator {
        name = this

        addHistoryRecord(AuditLogData(
            "name",
            null,
            this
        ))
        return this@SampleEntityCreator
    }

    override fun SampleEntity.createAudit(
        log: AuditLogData<String>,
        actorId: String,
        actorType: String,
    ): AuditRecordIdentity<String, SampleEntity> {
        return SampleEntityAudit(
            this,
            log.keyName,
            log.oldValue,
            log.newValue,
            actorId,
            ActorTypeImpl(actorType),
            AuditChangeType.CREATE
        )
    }

    override fun createIdentity(): SampleEntity {
        return SampleEntity(
            name,
            uuid,
            LocalDateTime.now()
        )
    }
}
