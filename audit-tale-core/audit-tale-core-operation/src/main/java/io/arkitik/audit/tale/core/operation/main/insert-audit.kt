package io.arkitik.audit.tale.core.operation.main

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.store.AuditRecordStore
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.operation.Operation
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:05 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
class InsertAuditsOperation<ID : Serializable, I : Identity<ID>>(
    private val auditRecordStore: AuditRecordStore<ID, I>,
) : Operation<List<AuditRecordIdentity<ID, I>>, Unit> {
    override fun List<AuditRecordIdentity<ID, I>>.operate() {
        with(auditRecordStore) {
            save()
        }
    }
}
