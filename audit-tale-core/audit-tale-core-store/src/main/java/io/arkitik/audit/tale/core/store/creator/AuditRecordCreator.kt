package io.arkitik.audit.tale.core.store.creator

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.creator.StoreIdentityCreator
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 8:46 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditRecordCreator<ID : Serializable, I : Identity<ID>> :
    StoreIdentityCreator<String, AuditRecordIdentity<ID, I>>
