package io.arkitik.audit.tale.engine.store.core.updater

import io.arkitik.audit.tale.engine.store.core.log.AuditLogs
import io.arkitik.audit.tale.engine.store.core.operator.AuditOperator
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.updater.StoreIdentityUpdater
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:25 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditStoreIdentityUpdater<ID : Serializable, I : Identity<ID>> : AuditOperator<I>,
    StoreIdentityUpdater<ID, AuditLogs<ID, I>>
