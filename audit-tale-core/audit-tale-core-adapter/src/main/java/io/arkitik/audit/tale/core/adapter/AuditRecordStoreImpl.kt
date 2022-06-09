package io.arkitik.audit.tale.core.adapter

import io.arkitik.audit.tale.core.adapter.query.AuditRecordStoreQueryImpl
import io.arkitik.audit.tale.core.adapter.repository.AuditRecordRepository
import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.entity.AuditRecord
import io.arkitik.audit.tale.core.store.AuditRecordStore
import io.arkitik.audit.tale.core.store.creator.AuditRecordCreator
import io.arkitik.audit.tale.core.store.updater.AuditRecordUpdater
import io.arkitik.radix.adapter.shared.StoreImpl
import io.arkitik.radix.develop.identity.Identity
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:40 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
class AuditRecordStoreImpl<ID : Serializable, I : Identity<ID>, A : AuditRecord<ID, I>>(
    repository: AuditRecordRepository<ID, I, A>,
) : StoreImpl<String, AuditRecordIdentity<ID, I>, A>(
    repository
), AuditRecordStore<ID, I> {
    @Suppress("UNCHECKED_CAST")
    override fun AuditRecordIdentity<ID, I>.map() = this as A

    override val storeQuery = AuditRecordStoreQueryImpl(repository)

    override fun identityCreator(): AuditRecordCreator<ID, I> {
        TODO("Not yet implemented")
    }

    override fun AuditRecordIdentity<ID, I>.identityUpdater(): AuditRecordUpdater<ID, I> {
        TODO("Not yet implemented")
    }
}
