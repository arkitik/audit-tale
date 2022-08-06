package io.arkitik.audit.tale.core.adapter.query

import io.arkitik.audit.tale.core.adapter.repository.AuditRecordRepository
import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.store.query.AuditRecordStoreQuery
import io.arkitik.audit.tale.core.store.query.OrderProperty
import io.arkitik.radix.adapter.shared.paged
import io.arkitik.radix.adapter.shared.query.StoreQueryImpl
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.PageableData
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:55 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
class AuditRecordStoreQueryImpl<ID : Serializable, I : Identity<ID>, A : AuditRecordIdentity<ID, I>, E : A>(
    private val repository: AuditRecordRepository<ID, I, A, E>,
) : StoreQueryImpl<String, A, E>(
    repository
), AuditRecordStoreQuery<ID, I, A> {
    override fun findAllOrderedBy(
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ) = paged(
        repository.findAll(
            PageRequest.of(
                pageableData.page,
                pageableData.size,
                sorting.asSortBy()
            )
        )
    )

    override fun findAllByRecordUuidInOrderedBy(
        recordUuids: List<ID>,
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ) = paged(
        repository.findAllByRecordUuidIn(
            uuids = recordUuids,
            pageable = PageRequest.of(
                pageableData.page,
                pageableData.size,
                sorting.asSortBy()
            )
        )
    )

    override fun findAllByKeyNameInOrderedBy(
        keys: List<String>,
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ) = paged(
        repository.findAllByKeyNameIn(
            keyNames = keys,
            pageable = PageRequest.of(
                pageableData.page,
                pageableData.size,
                sorting.asSortBy()
            )
        )
    )

    override fun findAllByKeyNameInAndRecordUuidInOrderedBy(
        keys: List<String>,
        recordUuids: List<ID>,
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ) = paged(
        repository.findAllByKeyNameInAndRecordUuidIn(
            keyNames = keys,
            uuids = recordUuids,
            pageable = PageRequest.of(
                pageableData.page,
                pageableData.size,
                sorting.asSortBy()
            )
        )
    )
}

fun List<OrderProperty>.asSortBy() =
    Sort.by(
        map {
            if (it.ascending)
                Sort.Order.asc(
                    it.keyName
                )
            else
                Sort.Order.desc(
                    it.keyName
                )
        }
    )
