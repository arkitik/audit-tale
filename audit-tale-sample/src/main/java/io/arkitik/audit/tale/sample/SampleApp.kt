package io.arkitik.audit.tale.sample

import io.arkitik.audit.tale.sample.entity.SampleEntityAudit
import io.arkitik.audit.tale.sample.entity.SampleEntityAuditRepository
import io.arkitik.audit.tale.sample.entity.SampleEntityStore
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
    private val sampleEntityAuditRepository: SampleEntityAuditRepository,
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
                sampleEntityAuditRepository.saveAll(
                    auditLogs.logs.map {
                        it as SampleEntityAudit
                    }
                )
            }
        }
    }

}

fun main(args: Array<String>) {
    runApplication<SampleApp>(*args)
}
