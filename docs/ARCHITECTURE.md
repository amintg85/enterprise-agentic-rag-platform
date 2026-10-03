# Architecture Decisions

## Bounded AI integration
AI-provider calls are isolated behind the RAG/agent service boundary so OpenAI and Bedrock adapters can be swapped without coupling controllers to a vendor.

## Retrieval
PostgreSQL + pgvector is the primary vector persistence target. PostgreSQL full-text/trigram capabilities can supply lexical signals for hybrid retrieval.

## Agent safety
Models propose tool calls; application code owns execution. Every tool should declare required authority, validate arguments, and emit an audit event.

## Caching
Redis is intended for safe retrieval/result caching. Never cache authorization decisions or sensitive responses without an explicit data policy.

## Observability
Expose health and operational metrics through Spring Boot Actuator/Micrometer. Production telemetry must avoid raw prompts and sensitive document content by default.
