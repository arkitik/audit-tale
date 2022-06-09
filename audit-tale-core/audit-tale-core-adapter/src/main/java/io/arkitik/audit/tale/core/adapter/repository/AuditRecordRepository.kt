package io.arkitik.audit.tale.core.adapter.repository

import io.arkitik.audit.tale.core.entity.AuditRecord
import io.arkitik.radix.adapter.shared.repository.RadixRepository
import io.arkitik.radix.develop.identity.Identity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:54 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
@NoRepositoryBean
interface AuditRecordRepository<ID : Serializable, I : Identity<ID>, A : AuditRecord<ID, I>> :
    RadixRepository<String, A> {
    fun findAllByRecordUuidIn(uuids: List<ID>, pageable: Pageable): Page<A>
    fun findAllByKeyNameIn(keyNames: List<String>, pageable: Pageable): Page<A>

    fun findAllByKeyNameInAndRecordUuidIn(
        keyNames: List<String>,
        uuids: List<ID>,
        pageable: Pageable,
    ): Page<A>
}
