resource "aws_s3_bucket" "documents" {
 bucket_prefix = "${var.project}-documents-"
 tags = { Project = var.project, DataClassification = "Enterprise" }
}
resource "aws_s3_bucket_public_access_block" "documents" {
 bucket = aws_s3_bucket.documents.id
 block_public_acls=true
 block_public_policy=true
 ignore_public_acls=true
 restrict_public_buckets=true
}
resource "aws_s3_bucket_versioning" "documents" {
 bucket = aws_s3_bucket.documents.id
 versioning_configuration { status = "Enabled" }
}
resource "aws_cloudwatch_log_group" "app" {
 name = "/ecs/${var.project}"
 retention_in_days = 30
}
output "document_bucket" { value=aws_s3_bucket.documents.bucket }
