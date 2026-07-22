# End-to-End Upload & Processing Workflow

## Overview

This document describes the complete lifecycle of a file from upload to processing and result delivery.

---

# Step 1 — Authentication

The user authenticates using JWT.

The API Gateway validates the token before forwarding the request.

---

# Step 2 — Request Upload

The client requests a pre-signed upload URL.

The Upload Service:

* validates the request
* verifies tenant permissions
* creates file metadata
* generates an Amazon S3 pre-signed URL

---

# Step 3 — Upload File

The client uploads the file directly to Amazon S3.

CloudFlow never proxies the binary file through Spring Boot.

---

# Step 4 — Upload Confirmation

After a successful upload:

* metadata is updated
* a processing job is created
* the `FileUploaded` event is published

---

# Step 5 — Job Creation

The Job Service:

* creates a processing job
* assigns the initial status
* publishes a `JobCreated` event

---

# Step 6 — Queue Processing

A worker subscribes to the appropriate Kafka topic and receives the event.

The worker:

* downloads the file from S3
* validates it
* begins processing

---

# Step 7 — Progress Updates

During execution, the worker periodically publishes progress events.

Examples:

* 10%
* 35%
* 60%
* 85%
* 100%

The Notification Service stores temporary progress in Redis and broadcasts updates over WebSockets.

---

# Step 8 — Completion

If processing succeeds:

* results are stored in Amazon S3
* job status becomes COMPLETED
* completion event is published
* user receives a notification

---

# Step 9 — Failure

If processing fails:

* job status becomes FAILED
* retry policy is evaluated

If retries remain:

FAILED

↓

RETRYING

↓

PROCESSING

Otherwise:

FAILED

↓

Dead Letter Queue

---

# Step 10 — Download

The user requests the processed file.

A secure download URL is generated through CloudFront.

---

# Summary Workflow

```text
Authenticate
      │
      ▼
Request Upload URL
      │
      ▼
Upload File to S3
      │
      ▼
FileUploaded Event
      │
      ▼
Create Job
      │
      ▼
Kafka Queue
      │
      ▼
Worker Processing
      │
      ▼
Progress Updates
      │
      ▼
Redis
      │
      ▼
WebSocket Notification
      │
      ▼
Job Completed
      │
      ▼
Store Result in S3
      │
      ▼
Download via CloudFront
```
