package io.arkitik.audit.tale.core.store

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.radix.develop.store.Store
import io.arkitik.radix.develop.store.creator.StoreIdentityCreator
import io.arkitik.radix.develop.store.query.StoreQuery
import io.arkitik.radix.develop.store.updater.StoreIdentityUpdater

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:09 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditRecordStore<I, A : AuditRecordIdentity<I>> : Store<String, A> {
    override val storeQuery: StoreQuery<String, A>

    override fun identityCreator(): StoreIdentityCreator<String, A>

    override fun A.identityUpdater(): StoreIdentityUpdater<String, A>
}
