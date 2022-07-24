package io.arkitik.audit.tale.engine.store.core

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.engine.store.core.creator.AuditStoreIdentityCreator
import io.arkitik.audit.tale.engine.store.core.updater.AuditStoreIdentityUpdater
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.Store
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:25 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditableStore<ID : Serializable, I : Identity<ID>, A : AuditRecordIdentity<ID, I>> : Store<ID, I> {

    fun identityCreator(
        actorId: String,
        actorType: String,
    ): AuditStoreIdentityCreator<ID, I, A>

    fun I.identityUpdater(
        actorId: String,
        actorType: String,
    ): AuditStoreIdentityUpdater<ID, I, A>
}
