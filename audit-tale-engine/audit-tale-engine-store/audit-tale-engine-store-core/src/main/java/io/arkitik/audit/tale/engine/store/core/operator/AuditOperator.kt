package io.arkitik.audit.tale.engine.store.core.operator

import io.arkitik.audit.tale.engine.store.core.log.AuditLogData

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:25 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
abstract class AuditOperator<I> {
    protected abstract fun <T> addHistoryRecord(
        log: AuditLogData<T>,
        mapper: (T) -> String? = { it?.toString() },
    ): AuditOperator<I>
}
