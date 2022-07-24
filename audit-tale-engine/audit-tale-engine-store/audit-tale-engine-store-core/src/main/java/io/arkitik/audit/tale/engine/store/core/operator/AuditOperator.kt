package io.arkitik.audit.tale.engine.store.core.operator

import io.arkitik.audit.tale.engine.store.core.log.AuditLogData

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:25 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface AuditOperator<I> {
    fun <T> addHistoryRecord(
        keyName: String,
        oldValue: T?,
        newValue: T?,
        mapper: (T) -> String?,
    ) = addHistoryRecord(
        AuditLogData(
            keyName,
            oldValue,
            newValue,
        ),
        mapper
    )

    fun <T> addHistoryRecord(
        log: AuditLogData<T>,
        mapper: (T) -> String? = { it?.toString() },
    ): AuditOperator<I>
}
