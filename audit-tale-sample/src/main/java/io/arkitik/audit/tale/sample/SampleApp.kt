package io.arkitik.audit.tale.sample

import io.arkitik.audit.tale.core.sdk.AuditTaleSdk
import io.arkitik.audit.tale.core.sdk.query.auditQueryRequest
import io.arkitik.audit.tale.sample.entity.SampleEntityAuditIdentity
import io.arkitik.audit.tale.sample.entity.SampleEntityStore
import io.arkitik.audit.tale.sample.entity.SampleIdentity
import io.arkitik.radix.develop.operation.ext.runOperation
import io.arkitik.radix.develop.store.storeCreator
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 12:03 AM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
@EnableJpaRepositories
@SpringBootApplication
class SampleApp(
    private val sampleEntityStore: SampleEntityStore,
    private val sampleAuditSdk: AuditTaleSdk<String, SampleIdentity, SampleEntityAuditIdentity>,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        with(sampleEntityStore) {
            repeat(100) {
                val auditLogs = storeCreator(identityCreator("Sample", "Test")) {
                    UUID.randomUUID().toString().replace("-", "_").uuid()
                    "s${it}".name()
                    create()
                }
                auditLogs.identity.save()
                sampleAuditSdk.insertAudits.runOperation(auditLogs.logs)
            }
        }
        with(sampleAuditSdk.auditQuery) {
            auditQueryRequest<String> {
                byKey("name")
                size(1000)
                orderedByCreationDate(true)
            }.operate()
                .let {
                    println(it.content.map(SampleEntityAuditIdentity::newValue))
                }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SampleApp>(*args)
}
