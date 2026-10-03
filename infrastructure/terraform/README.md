# AWS Terraform Architecture

This directory is an infrastructure starter for the portfolio architecture.

Target production topology:
- ALB -> ECS/Fargate (Spring Boot)
- Angular static assets -> S3/CloudFront
- RDS PostgreSQL/pgvector in private subnets
- ElastiCache Redis
- S3 document bucket
- Secrets Manager
- CloudWatch
- IAM least-privilege task roles

`main.tf` intentionally creates a small demonstrative set of resources. Review cost/security settings before applying.
