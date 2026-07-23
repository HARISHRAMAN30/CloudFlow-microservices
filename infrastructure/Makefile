#####################################################
# CloudFlow Infrastructure
#####################################################

COMPOSE=docker compose -f docker/docker-compose.yml

#####################################################
# Docker
#####################################################

up:
	$(COMPOSE) up -d

down:
	$(COMPOSE) down

restart:
	$(COMPOSE) down
	$(COMPOSE) up -d

destroy:
	$(COMPOSE) down -v

ps:
	$(COMPOSE) ps

#####################################################
# Logs
#####################################################

logs:
	$(COMPOSE) logs -f

logs-postgres:
	docker logs -f cloudflow-postgres

logs-kafka:
	docker logs -f cloudflow-kafka

#####################################################
# Kafka
#####################################################

kafka-shell:
	docker exec -it cloudflow-kafka bash

kafka-topics:
	docker exec -it cloudflow-kafka \
	/opt/kafka/bin/kafka-topics.sh \
	--bootstrap-server localhost:9092 \
	--list

kafka-cluster-id:
	docker exec -it cloudflow-kafka \
	/opt/kafka/bin/kafka-cluster.sh \
	cluster-id \
	--bootstrap-server localhost:9092

#####################################################
# PostgreSQL
#####################################################

postgres-shell:
	docker exec -it cloudflow-postgres psql \
	-U postgres \
	-d cloudflow

#####################################################
# Cleanup
#####################################################

clean:
	docker system prune -f