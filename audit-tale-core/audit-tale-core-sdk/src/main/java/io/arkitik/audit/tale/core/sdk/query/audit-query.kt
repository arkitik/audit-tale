package io.arkitik.audit.tale.core.sdk.query

import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:30 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
data class AuditQueryRequest<ID : Serializable>(
    val page: PageableQuery,
    val recordUuids: List<ID>,
    val keys: List<String>,
    val sorting: List<AuditPropertyOrder>,
)

data class PageableQuery(
    val page: Int,
    val size: Int,
)

data class AuditPropertyOrder(
    val propertyName: String,
    val ascending: Boolean,
)

fun <ID : Serializable> auditQueryRequest(builder: AuditQueryRequestBuilder<ID>.() -> Unit): AuditQueryRequest<ID> {
    return AuditQueryRequestBuilder<ID>()
        .apply(builder)
        .build()
}

class AuditQueryRequestBuilder<ID : Serializable> {
    private val recordUuids: MutableList<ID> = mutableListOf()
    private val keys: MutableList<String> = mutableListOf()
    private val sorting: MutableList<AuditPropertyOrder> = mutableListOf()
    private var page: Int = 0
    private var size: Int = 20

    infix fun byRecordUuid(recordUuid: ID) {
        recordUuids.add(recordUuid)
    }

    infix fun byKey(key: String) {
        keys.add(key)
    }

    fun orderedBy(key: String, ascending: Boolean) {
        sorting.add(AuditPropertyOrder(key, ascending))
    }

    infix fun orderedByAscending(key: String) {
        orderedBy(key, true)
    }

    infix fun orderedByDescending(key: String) {
        orderedBy(key, true)
    }

    infix fun orderedByCreationDate(ascending: Boolean) {
        orderedBy("creationDate", ascending)
    }

    infix fun page(page: Int) {
        this.page = page
    }

    infix fun size(size: Int) {
        this.size = size
    }

    fun build() =
        AuditQueryRequest(
            page = PageableQuery(page, size),
            recordUuids = recordUuids,
            keys = keys,
            sorting = sorting,
        )

}
