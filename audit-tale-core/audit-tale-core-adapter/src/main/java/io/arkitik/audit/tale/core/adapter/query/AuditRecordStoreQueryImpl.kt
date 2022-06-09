package io.arkitik.audit.tale.core.adapter.query

import io.arkitik.audit.tale.core.adapter.repository.AuditRecordRepository
import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.entity.AuditRecord
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
class AuditRecordStoreQueryImpl<ID : Serializable, I : Identity<ID>, A : AuditRecord<ID, I>>(
    private val repository: AuditRecordRepository<ID, I, A>,
) : StoreQueryImpl<String, AuditRecordIdentity<ID, I>, A>(
    repository
), AuditRecordStoreQuery<ID, I> {
    override fun findAllOrderedBy(
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ) = paged(repository.findAll(
        PageRequest.of(
            pageableData.page,
            pageableData.size,
            Sort.by(
                sorting.map {
                    Sort.Order.asc(
                        it.keyName
                    )
                }
            )
        )))

    override fun findAllByRecordUuidInOrderedBy(
        recordUuids: List<ID>,
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ) = paged(repository.findAllByRecordUuidIn(
        uuids = recordUuids,
        pageable = PageRequest.of(
            pageableData.page,
            pageableData.size,
            Sort.by(
                sorting.map {
                    Sort.Order.asc(
                        it.keyName
                    )
                }
            )
        )))

    override fun findAllByKeyNameInOrderedBy(
        keys: List<String>,
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ) = paged(repository.findAllByKeyNameIn(
        keyNames = keys,
        pageable = PageRequest.of(
            pageableData.page,
            pageableData.size,
            Sort.by(
                sorting.map {
                    Sort.Order.asc(
                        it.keyName
                    )
                }
            )
        )))

    override fun findAllByKeyNameInAndRecordUuidInOrderedBy(
        keys: List<String>,
        recordUuids: List<ID>,
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ) = paged(repository.findAllByKeyNameInAndRecordUuidIn(
        keyNames = keys,
        uuids = recordUuids,
        pageable = PageRequest.of(
            pageableData.page,
            pageableData.size,
            Sort.by(
                sorting.map {
                    Sort.Order.asc(
                        it.keyName
                    )
                }
            )
        )))
}
