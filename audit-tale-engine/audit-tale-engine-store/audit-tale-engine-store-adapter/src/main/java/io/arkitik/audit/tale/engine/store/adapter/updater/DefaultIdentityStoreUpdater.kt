package io.arkitik.audit.tale.engine.store.adapter.updater

import io.arkitik.audit.tale.engine.store.core.updater.AuditStoreIdentityUpdater
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.updater.StoreIdentityUpdater
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:45 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
class DefaultIdentityStoreUpdater<ID : Serializable, I : Identity<ID>>(
    private val auditStoreIdentityCreator: AuditStoreIdentityUpdater<ID, I>,
) : StoreIdentityUpdater<ID, I> {
    override fun update(): I {
        return auditStoreIdentityCreator.update().identity
    }
}
