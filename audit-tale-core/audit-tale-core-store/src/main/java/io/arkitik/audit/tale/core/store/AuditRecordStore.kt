package io.arkitik.audit.tale.core.store

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.store.creator.AuditRecordCreator
import io.arkitik.audit.tale.core.store.query.AuditRecordStoreQuery
import io.arkitik.audit.tale.core.store.updater.AuditRecordUpdater
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.Store
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:09 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditRecordStore<ID : Serializable, I : Identity<ID>> : Store<String, AuditRecordIdentity<ID, I>> {
    override val storeQuery: AuditRecordStoreQuery<ID, I>

    override fun identityCreator(): AuditRecordCreator<ID, I>

    override fun AuditRecordIdentity<ID, I>.identityUpdater(): AuditRecordUpdater<ID, I>
}
