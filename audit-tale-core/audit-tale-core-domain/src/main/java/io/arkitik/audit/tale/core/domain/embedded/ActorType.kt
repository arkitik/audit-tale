package io.arkitik.audit.tale.core.domain.embedded

import io.arkitik.radix.develop.identity.EmbeddedData

/**
 * Created By [*Ibrahim Al-Tamimi *](https://www.linkedin.com/in/iloom/)
 * Created At 9:23 PM, 07 , **Tue, June 2022**
 * Project *audit-tale* [arkitik.io](https://arkitik.io)
 */
interface ActorType : EmbeddedData {
    val actorType: String
}
