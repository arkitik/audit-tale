package io.arkitik.audit.tale.engine.store.adapter.creator

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.engine.store.core.creator.AuditStoreIdentityCreator
import io.arkitik.audit.tale.engine.store.core.log.AuditLogData
import io.arkitik.audit.tale.engine.store.core.log.AuditLogs
import io.arkitik.audit.tale.engine.store.core.operator.AuditOperator
import io.arkitik.radix.develop.identity.Identity
import java.io.Serializable


/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:32 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
abstract class CoreAuditStoreIdentityCreator<ID : Serializable, I : Identity<ID>>(
    private val actorId: String,
    private val actorType: String,
) : AuditStoreIdentityCreator<ID, I>() {
    private val auditLogs: MutableList<AuditLogData<String>> = mutableListOf()

    override fun <T> addHistoryRecord(log: AuditLogData<T>, mapper: (T) -> String?): AuditOperator<I> {
        auditLogs.add(AuditLogData(
            log.keyName,
            log.oldValue?.let(mapper),
            log.newValue?.let(mapper),
        ))
        return this
    }

    final override fun create(): AuditLogs<ID, I> {
        val identity = createIdentity()
        return AuditLogs(
            auditLogs.map {
                identity.createAudit(it, actorId, actorType)
            },
            identity
        )
    }

    abstract fun I.createAudit(
        log: AuditLogData<String>,
        actorId: String,
        actorType: String,
    ): AuditRecordIdentity<ID,I>

    abstract fun createIdentity(): I
}
