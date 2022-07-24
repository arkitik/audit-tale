package io.arkitik.audit.tale.engine.store.adapter

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.engine.store.adapter.creator.CoreAuditStoreIdentityCreator
import io.arkitik.audit.tale.engine.store.adapter.creator.DefaultIdentityStoreCreator
import io.arkitik.audit.tale.engine.store.adapter.updater.CoreAuditStoreIdentityUpdater
import io.arkitik.audit.tale.engine.store.adapter.updater.DefaultIdentityStoreUpdater
import io.arkitik.audit.tale.engine.store.core.AuditableStore
import io.arkitik.radix.adapter.shared.StoreImpl
import io.arkitik.radix.adapter.shared.repository.RadixRepository
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.creator.StoreIdentityCreator
import io.arkitik.radix.develop.store.updater.StoreIdentityUpdater
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 30, **Fri Oct, 2020**
 * Project *audit-tale* [https://arkitik.io]
 */
abstract class AuditableStoreImpl<ID : Serializable, I : Identity<ID>, E : I, A : AuditRecordIdentity<ID, I>>(
    repository: RadixRepository<ID, E>,
) : StoreImpl<ID, I, E>(
    repository
), AuditableStore<ID, I, A> {

    companion object {
        private const val radixActorId = "AUDIT_TALE"
        private const val radixActor = "AUDIT-TALE-ACTOR"
    }

    override fun identityCreator(): StoreIdentityCreator<ID, I> {
        return DefaultIdentityStoreCreator(identityCreator(radixActorId, radixActor))
    }

    override fun I.identityUpdater(): StoreIdentityUpdater<ID, I> {
        return DefaultIdentityStoreUpdater(identityUpdater(radixActorId, radixActor))
    }

    abstract override fun identityCreator(actorId: String, actorType: String): CoreAuditStoreIdentityCreator<ID, I, A>

    abstract override fun I.identityUpdater(actorId: String, actorType: String): CoreAuditStoreIdentityUpdater<ID, I, A>
}
