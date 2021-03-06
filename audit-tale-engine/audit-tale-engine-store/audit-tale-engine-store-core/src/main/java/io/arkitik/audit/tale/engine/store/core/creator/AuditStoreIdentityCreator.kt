package io.arkitik.audit.tale.engine.store.core.creator

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.engine.store.core.log.AuditLogs
import io.arkitik.audit.tale.engine.store.core.operator.AuditOperator
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.creator.StoreIdentityCreator
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:25 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditStoreIdentityCreator<ID : Serializable, I : Identity<ID>, A : AuditRecordIdentity<ID, I>> :
    AuditOperator<I>, StoreIdentityCreator<ID, AuditLogs<ID, I, A>>
