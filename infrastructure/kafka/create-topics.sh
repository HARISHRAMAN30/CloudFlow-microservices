#!/bin/bash

set -e

echo "Waiting for Kafka broker..."

until /opt/kafka/bin/kafka-topics.sh \
  --bootstrap-server kafka:9092 \
  --list >/dev/null 2>&1
do
  sleep 2
done

echo "Kafka is ready."

TOPICS=(
  "file-uploaded"
  "job-created"
  "job-queued"
  "job-processing"
  "progress-updated"
  "job-completed"
  "job-failed"
  "retry-requested"
  "notification-requested"
  "audit-event"
)

for topic in "${TOPICS[@]}"
do
  echo "Creating topic: $topic"

  /opt/kafka/bin/kafka-topics.sh \
    --bootstrap-server kafka:9092 \
    --create \
    --if-not-exists \
    --topic "$topic" \
    --partitions 3 \
    --replication-factor 1
done

echo "All CloudFlow topics created successfully."