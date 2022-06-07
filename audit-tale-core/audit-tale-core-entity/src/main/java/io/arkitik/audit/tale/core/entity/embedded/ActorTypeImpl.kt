package io.arkitik.audit.tale.core.entity.embedded

import io.arkitik.audit.tale.core.domain.embedded.ActorType
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 11:54 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
@Embeddable
data class ActorTypeImpl(
    @Column(nullable = false)
    override val name: String,
) : ActorType
