package io.arkitik.audit.tale.sample.store

import io.arkitik.audit.tale.core.adapter.repository.AuditRecordRepository
import io.arkitik.audit.tale.sample.entity.SampleEntityAudit
import io.arkitik.audit.tale.sample.entity.SampleEntityAuditIdentity
import io.arkitik.audit.tale.sample.entity.SampleIdentity

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:31 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface SampleAuditRepository :
    AuditRecordRepository<String, SampleIdentity, SampleEntityAuditIdentity, SampleEntityAudit>
