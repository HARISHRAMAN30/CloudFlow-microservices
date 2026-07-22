## Overview

CloudFlow is a cloud-native, event-driven microservices platform.

The architecture separates user-facing APIs from long-running background processing, enabling independent scaling and improved fault tolerance.

---

# Architecture Diagram

```text
                        Client
                           │
                           ▼
                    API Gateway
                           │
      ┌────────────────────┼────────────────────┐
      ▼                    ▼                    ▼
 Authentication      Tenant Service      Upload Service
                                              │
                                              ▼
                                        PostgreSQL
                                              │
                                              ▼
                                         Kafka Topics
                                              │
         ┌────────────────────────────────────────────────┐
         ▼                ▼                ▼              ▼
   CSV Worker       PDF Worker      Image Worker    ZIP Worker
         │
         ▼
       Redis
         │
         ▼
 WebSocket Notification Service
         │
         ▼
    Frontend Client

-----------------------------------------------------------

Amazon S3
      │
      ▼
CloudFront

-----------------------------------------------------------

Prometheus
      │
Grafana

-----------------------------------------------------------

Logstash
      │
Elasticsearch
      │
Kibana
```

---

# Core Components

## API Gateway

Single entry point responsible for routing, authentication, and rate limiting.

## Microservices

Each service owns one business capability.

## Kafka

Coordinates asynchronous workflows.

## PostgreSQL

Persistent storage.

## Redis

Low-latency temporary data.

## Amazon S3

Durable object storage.

## CloudFront

Global content delivery.

## Observability

Prometheus collects metrics.

Grafana visualizes metrics.

ELK centralizes logs.

---

# Architectural Characteristics

* Microservices
* Event-Driven
* Stateless Services
* Eventual Consistency
* Horizontal Scalability
* Fault Tolerance
* Multi-Tenant SaaS
* Cloud-Native Deployment
