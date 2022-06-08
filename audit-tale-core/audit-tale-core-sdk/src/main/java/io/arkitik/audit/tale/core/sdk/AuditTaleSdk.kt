package io.arkitik.audit.tale.core.sdk

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.sdk.query.AuditQueryRequest
import io.arkitik.audit.tale.core.sdk.query.PagedData
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.operation.Operation
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:05 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditTaleSdk<ID : Serializable, I : Identity<ID>> {
    val insertAudits: Operation<List<AuditRecordIdentity<ID, I>>, Unit>
    val auditQuery: Operation<AuditQueryRequest<ID>, PagedData<AuditRecordIdentity<ID, I>>>
}
