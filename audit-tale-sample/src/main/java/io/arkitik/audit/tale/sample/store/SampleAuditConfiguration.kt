package io.arkitik.audit.tale.sample.store

import io.arkitik.audit.tale.core.adapter.AuditRecordStoreImpl
import io.arkitik.audit.tale.core.operation.AuditTaleSdkImpl
import io.arkitik.audit.tale.core.store.AuditRecordStore
import io.arkitik.audit.tale.sample.entity.SampleEntity
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:30 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
@Configuration
class SampleAuditConfiguration {
    @Bean
    fun sampleAuditStore(
        sampleAuditRepository: SampleAuditRepository,
    ) = AuditRecordStoreImpl(sampleAuditRepository)

    @Bean
    fun sampleAuditSdk(
        sampleAuditStore: AuditRecordStore<String, SampleEntity>,
    ) = AuditTaleSdkImpl(
        sampleAuditStore
    )
}
