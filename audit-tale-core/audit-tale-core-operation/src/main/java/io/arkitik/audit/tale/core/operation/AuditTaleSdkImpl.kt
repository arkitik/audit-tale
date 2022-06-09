package io.arkitik.audit.tale.core.operation

import io.arkitik.audit.tale.core.operation.main.AuditQueryOperation
import io.arkitik.audit.tale.core.operation.main.InsertAuditsOperation
import io.arkitik.audit.tale.core.sdk.AuditTaleSdk
import io.arkitik.audit.tale.core.store.AuditRecordStore
import io.arkitik.radix.develop.identity.Identity
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:03 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
class AuditTaleSdkImpl<ID : Serializable, I : Identity<ID>>(
    auditRecordStore: AuditRecordStore<ID, I>,
) : AuditTaleSdk<ID, I> {

    override val insertAudits = InsertAuditsOperation(auditRecordStore)

    override val auditQuery = AuditQueryOperation(auditRecordStore.storeQuery)
}
