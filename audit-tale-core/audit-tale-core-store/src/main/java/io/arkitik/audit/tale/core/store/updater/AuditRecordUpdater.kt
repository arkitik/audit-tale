package io.arkitik.audit.tale.core.store.updater

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.updater.StoreIdentityUpdater
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 8:46 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditRecordUpdater<ID : Serializable, I : Identity<ID>> :
    StoreIdentityUpdater<String, AuditRecordIdentity<ID, I>>
