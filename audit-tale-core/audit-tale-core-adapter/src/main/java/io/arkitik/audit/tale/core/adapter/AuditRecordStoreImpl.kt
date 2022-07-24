package io.arkitik.audit.tale.core.adapter

import io.arkitik.audit.tale.core.adapter.query.AuditRecordStoreQueryImpl
import io.arkitik.audit.tale.core.adapter.repository.AuditRecordRepository
import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.store.AuditRecordStore
import io.arkitik.radix.adapter.shared.StoreImpl
import io.arkitik.radix.develop.identity.Identity
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:40 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
class AuditRecordStoreImpl<ID : Serializable, I : Identity<ID>, A : AuditRecordIdentity<ID, I>, E : A>(
    repository: AuditRecordRepository<ID, I, A, E>,
) : StoreImpl<String, A, E>(
    repository
), AuditRecordStore<ID, I, A> {
    @Suppress("UNCHECKED_CAST")
    override fun A.map() = this as E

    override val storeQuery = AuditRecordStoreQueryImpl(repository)
}
