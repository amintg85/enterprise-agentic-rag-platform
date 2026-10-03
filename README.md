# Enterprise Agentic RAG Prototype

A Java 21, Spring Boot, and Angular prototype for exploring document APIs, prompt guardrails, and an agent-style tool response. It is not a production RAG system yet.

## Implemented now

- PDF and plain-text uploads are type/size checked and stored as database metadata; file contents are not persisted or parsed.
- The query API validates prompts and returns a static demo answer/citation; there is no embedding generation, vector retrieval, or live LLM call.
- The agent endpoint returns a read-only system-time tool result.
- Prompt checks reject a small set of known injection phrases.
- Audit events are written to application logs.
- Terraform files are deployment starters, not a verified AWS deployment.

Authentication is also a prototype: `/api/auth/login` returns a synthetic, unsigned token, while protected backend routes currently use Spring's generated HTTP Basic credentials. The frontend only wires the public demo query route. Do not use this setup for real accounts or sensitive documents.

## Run locally

Requires Java 21, Node 20.19+ or 22.12+, and Docker Compose.

1. Copy `.env.example` to `.env` and replace the JWT placeholder with a locally generated value (`openssl rand -base64 48`). The LLM key is not used by the current static query implementation.
2. Start Postgres and Redis with `docker compose up -d postgres redis`.
3. Start the API with `cd backend && mvn spring-boot:run`.
4. Start Angular with `cd frontend && npm ci && npm start`.

The UI is at `http://localhost:4200`; Swagger is at `http://localhost:8080/swagger-ui.html`. Angular proxies `/api` to the local backend.

## Verify

```bash
cd backend && mvn test
cd ../frontend && npm ci && npm test && npm run build && npm audit
```

## Next engineering milestones

Persist uploaded files, extract and chunk content, generate embeddings, implement vector retrieval and grounded answers, replace demo authentication with verified JWT/OIDC, and add integration/evaluation datasets before describing this as an end-to-end RAG platform.
