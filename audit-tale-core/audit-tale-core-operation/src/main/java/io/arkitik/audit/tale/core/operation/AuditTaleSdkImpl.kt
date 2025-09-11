package io.arkitik.audit.tale.core.operation

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.operation.main.AuditQueryOperation
import io.arkitik.audit.tale.core.operation.main.InsertAuditsOperation
import io.arkitik.audit.tale.core.sdk.AuditTaleSdk
import io.arkitik.audit.tale.core.sdk.query.AuditQueryRequest
import io.arkitik.audit.tale.core.sdk.query.PagedData
import io.arkitik.audit.tale.core.store.AuditRecordStore
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.operation.Operation
import io.arkitik.radix.develop.operation.ext.operationBuilder
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:03 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
class AuditTaleSdkImpl<ID : Serializable, I : Identity<ID>, A : AuditRecordIdentity<ID, I>>(
    auditRecordStore: AuditRecordStore<ID, I, A>,
) : AuditTaleSdk<ID, I, A> {

    override val insertAudits: Operation<List<A>, Unit> =
        operationBuilder {
            mainOperation(
                InsertAuditsOperation(
                    auditRecordStore = auditRecordStore
                )
            )
        }
    override val auditQuery: Operation<AuditQueryRequest<ID>, PagedData<A>> =
        operationBuilder {
            mainOperation(
                AuditQueryOperation(
                    auditRecordStoreQuery = auditRecordStore.storeQuery
                )
            )
        }
}
