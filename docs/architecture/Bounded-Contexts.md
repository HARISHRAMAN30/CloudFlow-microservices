## Purpose

CloudFlow follows Domain-Driven Design (DDD).

Each bounded context encapsulates a single business capability, owns its own data, and exposes explicit APIs or events.

---

# Identity Context

### Responsibilities

* Authentication
* JWT
* Refresh Tokens
* Roles
* Permissions

Owns:

* Users
* Roles
* Permissions

---

# Tenant Context

### Responsibilities

* Organizations
* Subscription Plans
* API Keys
* Tenant Settings

Owns:

* Tenant
* APIKey
* Subscription

---

# Upload Context

### Responsibilities

* Generate Pre-Signed URLs
* Validate Upload Requests
* Store File Metadata

Owns:

* File Metadata
* Upload Requests

---

# Job Context

### Responsibilities

* Job Lifecycle
* Retry
* Cancellation
* History

Owns:

* Jobs
* Job State
* Retry Metadata

---

# Processing Context

### Responsibilities

* Consume Kafka Events
* Process Files
* Publish Progress
* Generate Results

Owns:

* Processing Logic
* Worker Execution

---

# Notification Context

### Responsibilities

* Email
* WebSocket Notifications

Owns:

* Notification Templates
* Notification History

---

# Audit Context

### Responsibilities

* Login History
* API Logs
* Security Events
* Upload Events

Owns:

* Audit Logs

---

# Context Communication

Contexts communicate using:

* REST (short-lived synchronous operations)
* Kafka (long-running asynchronous workflows)

Direct database access between contexts is prohibited.

Each bounded context owns its own schema and business rules.
