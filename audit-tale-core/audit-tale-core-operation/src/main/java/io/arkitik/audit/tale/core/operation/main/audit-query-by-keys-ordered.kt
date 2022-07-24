package io.arkitik.audit.tale.core.operation.main

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.sdk.query.AuditQueryRequest
import io.arkitik.audit.tale.core.store.query.AuditRecordStoreQuery
import io.arkitik.audit.tale.core.store.query.OrderProperty
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.operation.Operation
import io.arkitik.radix.develop.store.PageableData
import io.arkitik.radix.develop.store.query.PageData
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:11 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
internal class AuditQueryByKeysOrdered<ID : Serializable, I : Identity<ID>, A : AuditRecordIdentity<ID, I>>(
    private val auditRecordStoreQuery: AuditRecordStoreQuery<ID, I, A>,
) : Operation<AuditQueryRequest<ID>, PageData<A>> {
    override fun AuditQueryRequest<ID>.operate() =
        auditRecordStoreQuery.findAllByKeyNameInOrderedBy(
            keys = keys,
            sorting = sorting.map {
                OrderProperty(it.propertyName, it.ascending)
            },
            pageableData = PageableData(page.page, page.size)
        )
}
