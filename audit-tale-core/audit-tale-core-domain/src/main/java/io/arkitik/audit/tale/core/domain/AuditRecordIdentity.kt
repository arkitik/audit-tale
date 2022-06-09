package io.arkitik.audit.tale.core.domain

import io.arkitik.audit.tale.core.domain.embedded.ActorType
import io.arkitik.audit.tale.core.domain.embedded.AuditChangeType
import io.arkitik.radix.develop.identity.Identity
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:23 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditRecordIdentity<ID : Serializable, I : Identity<ID>> : Identity<String> {
    override val uuid: String
    val record: I
    val keyName: String
    val oldValue: String?
    val newValue: String?
    val actorId: String
    val actorType: ActorType
    val changeType: AuditChangeType
}
