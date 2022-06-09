package io.arkitik.audit.tale.core.store.query

import io.arkitik.audit.tale.core.domain.AuditRecordIdentity
import io.arkitik.radix.develop.identity.Identity
import io.arkitik.radix.develop.store.PageableData
import io.arkitik.radix.develop.store.query.PageData
import io.arkitik.radix.develop.store.query.StoreQuery
import java.io.Serializable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 8:49 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditRecordStoreQuery<ID : Serializable, I : Identity<ID>> : StoreQuery<String, AuditRecordIdentity<ID, I>> {
    fun findAllOrderedBy(
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ): PageData<AuditRecordIdentity<ID, I>>

    fun findAllByRecordUuidInOrderedBy(
        recordUuids: List<ID>,
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ): PageData<AuditRecordIdentity<ID, I>>

    fun findAllByKeyNameInOrderedBy(
        keys: List<String>,
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ): PageData<AuditRecordIdentity<ID, I>>

    fun findAllByKeyNameInAndRecordUuidInOrderedBy(
        keys: List<String>,
        recordUuids: List<ID>,
        sorting: List<OrderProperty>,
        pageableData: PageableData,
    ): PageData<AuditRecordIdentity<ID, I>>
}

data class OrderProperty(
    val keyName: String,
    val ascending: Boolean,
)
