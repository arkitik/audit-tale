package io.arkitik.audit.tale.core.sdk.query

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 10:01 PM, 08 , **Wed, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
data class PagedData<T>(
    val content: Iterable<T>,
    val numberOfElements: Int,
    val totalElements: Long,
    val totalPages: Int,
    val currentPage: Int,
    val currentPageSize: Int,
)
