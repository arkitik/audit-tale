package io.arkitik.audit.tale.core.store

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.store.query.AuditRecordStoreQuery
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.Store
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:09 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditRecordStore<ID : Serializable, I : Identity<ID>, A : AuditRecordIdentity<ID, I>> :
    Store<String, A> {
    override val storeQuery: AuditRecordStoreQuery<ID, I, A>

    override fun identityCreator() =
        throw IllegalStateException("Audit records can be created only by the auditable entities, see @{audit-tale-engine}")

    override fun A.identityUpdater() =
        throw IllegalStateException("Audit records are not allowed to get updated.")
}
