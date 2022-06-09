package io.arkitik.audit.tale.core.operation.main

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.audit.tale.core.sdk.query.AuditQueryRequest
import io.arkitik.audit.tale.core.sdk.query.PagedData
import io.arkitik.audit.tale.core.store.query.AuditRecordStoreQuery
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.operation.Operation
import io.arkitik.radix.develop.operation.ext.runOperation
import io.arkitik.radix.develop.store.query.PageData
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:11 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
private typealias Predicate<ID> = (AuditQueryRequest<ID>) -> Boolean
private typealias QueryOperation<ID, I> = Operation<AuditQueryRequest<ID>, PageData<AuditRecordIdentity<ID, I>>>

class AuditQueryOperation<ID : Serializable, I : Identity<ID>>(
    auditRecordStoreQuery: AuditRecordStoreQuery<ID, I>,
) : Operation<AuditQueryRequest<ID>, PagedData<AuditRecordIdentity<ID, I>>> {
    private val auditQueryByKeysAndUuidsOrdered = AuditQueryByKeysAndUuidsOrdered(auditRecordStoreQuery)
    private val auditQueryByUuidsOrdered = AuditQueryByUuidsOrdered(auditRecordStoreQuery)
    private val auditQueryByKeysOrdered = AuditQueryByKeysOrdered(auditRecordStoreQuery)
    private val auditQueryOrdered = AuditQueryOrdered(auditRecordStoreQuery)

    private val operationMapper: Map<QueryOperation<ID, I>, Predicate<ID>> = buildMap {
        put(auditQueryOrdered) {
            it.keys.isEmpty() and it.recordUuids.isEmpty()
        }
        put(auditQueryByKeysOrdered) {
            it.keys.isNotEmpty() and it.recordUuids.isEmpty()
        }
        put(auditQueryByUuidsOrdered) {
            it.keys.isEmpty() and it.recordUuids.isNotEmpty()
        }
        put(auditQueryByKeysAndUuidsOrdered) {
            it.keys.isNotEmpty() and it.recordUuids.isNotEmpty()
        }
    }

    override fun AuditQueryRequest<ID>.operate(): PagedData<AuditRecordIdentity<ID, I>> {
        val queryOperation = operationMapper.entries
            .firstOrNull {
                it.value(this)
            }?.key ?: auditQueryOrdered
        return queryOperation.runOperation(this)
            .let {
                PagedData(
                    content = it.content,
                    numberOfElements = it.numberOfElements,
                    totalElements = it.totalElements,
                    totalPages = it.numberOfElements,
                    currentPage = it.currentPage,
                    currentPageSize = it.currentPageSize
                )
            }
    }
}
