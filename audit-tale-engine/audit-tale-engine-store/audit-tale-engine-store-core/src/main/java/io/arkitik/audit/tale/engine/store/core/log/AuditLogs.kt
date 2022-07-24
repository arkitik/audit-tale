package io.arkitik.audit.tale.engine.store.core.log

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.radix.develop.identity.Identity
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:33 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
open class AuditLogs<ID : Serializable, I : Identity<ID>, A : AuditRecordIdentity<ID, I>>(
    open val logs: List<A>,
    val identity: I,
) : Identity<ID> {
    override val uuid = identity.uuid
    override val creationDate = identity.creationDate
}

data class AuditLogData<T>(
    val keyName: String,
    val oldValue: T?,
    val newValue: T?,
)
