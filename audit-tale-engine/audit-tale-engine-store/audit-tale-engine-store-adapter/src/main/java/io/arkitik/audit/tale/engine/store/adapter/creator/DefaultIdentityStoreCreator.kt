package io.arkitik.audit.tale.engine.store.adapter.creator

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.engine.store.core.creator.AuditStoreIdentityCreator
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.creator.StoreIdentityCreator
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:45 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
class DefaultIdentityStoreCreator<ID : Serializable, I : Identity<ID>, A : AuditRecordIdentity<ID, I>>(
    private val auditStoreIdentityCreator: AuditStoreIdentityCreator<ID, I, A>,
) : StoreIdentityCreator<ID, I> {
    override fun ID.uuid(): StoreIdentityCreator<ID, I> {
        auditStoreIdentityCreator.run {
            this@uuid.uuid()
        }
        return this@DefaultIdentityStoreCreator
    }

    override fun create(): I {
        return auditStoreIdentityCreator.create().identity
    }
}
