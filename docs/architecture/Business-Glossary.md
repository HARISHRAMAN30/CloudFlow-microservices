## Purpose

This document defines the common business terminology used throughout CloudFlow.

Every service, API, database schema, event, and ADR must use these terms consistently.

---

# Tenant

An organization that uses CloudFlow.

Examples:

* Hospital
* HR Company
* Insurance Company
* AI Startup
* Manufacturing Company

A tenant owns all resources created within its workspace.

---

# User

A person belonging to a tenant.

A user authenticates into CloudFlow and performs actions based on assigned roles and permissions.

---

# Role

A collection of permissions assigned to users.

Examples:

* ADMIN
* MANAGER
* OPERATOR
* VIEWER

---

# Permission

A specific action that a user is allowed to perform.

Examples:

* Upload Files
* Retry Jobs
* Delete Jobs
* Manage Users
* View Reports

---

# API Key

A credential used by external systems to access CloudFlow APIs without interactive user authentication.

---

# File

Metadata representing an uploaded object stored in Amazon S3.

The platform never stores the actual binary data inside PostgreSQL.

---

# Upload

The process of securely transferring a file to Amazon S3 using a pre-signed URL.

---

# Job

A unit of work representing the processing of one uploaded file.

Every upload creates exactly one processing job.

---

# Worker

A background service responsible for processing jobs asynchronously.

Workers consume Kafka events and publish processing updates.

---

# Processing Result

The output generated after a worker successfully processes a job.

---

# Job Status

The current lifecycle stage of a processing job.

Allowed states include:

* CREATED
* UPLOADING
* UPLOADED
* QUEUED
* PROCESSING
* PARTIAL_SUCCESS
* COMPLETED
* FAILED
* RETRYING
* CANCELLED

---

# Event

A domain occurrence published through Apache Kafka.

Examples:

* FileUploaded
* JobCreated
* ProgressUpdated
* JobCompleted

---

# Notification

A message informing users about job progress or system events.

Supported channels:

* WebSocket
* Email

---

# Audit Log

An immutable record of significant system activities.

Examples:

* User Login
* File Upload
* Job Retry
* Permission Changes

---

# Correlation ID

A unique identifier propagated across services to trace a single request through the distributed system.

---

# Retry

The process of reprocessing a failed job based on predefined retry policies.

---

# Dead Letter Queue (DLQ)

A Kafka topic used to store events that cannot be processed successfully after the maximum retry attempts.

---

# Multi-Tenancy

An architectural model where multiple organizations share the same platform while ensuring complete logical isolation of their data and operations.

---

# Bounded Context

A logical boundary within which a domain model is defined and consistently applied.

Each CloudFlow microservice represents a bounded context.
