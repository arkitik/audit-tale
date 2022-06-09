package io.arkitik.audit.tale.sample.store

import io.arkitik.audit.tale.core.adapter.repository.AuditRecordRepository
import io.arkitik.audit.tale.sample.entity.SampleEntity
import io.arkitik.audit.tale.sample.entity.SampleEntityAudit

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:31 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface SampleAuditRepository : AuditRecordRepository<String, SampleEntity, SampleEntityAudit>
