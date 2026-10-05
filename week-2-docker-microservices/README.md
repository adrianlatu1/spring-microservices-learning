# Săptămâna 2: Docker + Microservicii

Containerizare și comunicare între servicii.

## Structură

```
week-2-docker-microservices/
├── README.md                    (tu ești aici)
├── 01-docker-basics/            (Dockerfile, docker-compose)
├── 02-service-discovery/        (comunicare HTTP între servicii)
├── 03-async-messaging/          (RabbitMQ sau Kafka lite)
├── 04-debugging-logging/        (centralizare logare)
└── NOTES.md
```

## Pași

### 1. Docker Basics (2-3 ore)
- Containerizez aplicația din W1
- docker-compose cu aplicație + bază de date
- Rulare locală

### 2. Service Discovery (2-3 ore)
- Crează 2-3 servicii mici
- Comunicare HTTP (RestTemplate sau WebClient)
- Routing și load balancing (nginx în docker-compose)

### 3. Async Messaging (2-3 ore)
- Introducere RabbitMQ sau Apache Kafka
- Producător și consumator în servicii separate
- docker-compose cu message broker

### 4. Debugging & Logging (2-3 ore)
- Centralizare logare (ELK stack minimal sau similar)
- Correlation IDs între servicii
- Debugging în Docker

---

**Status:** ⏳ Aștept finalizarea W1

**Dependență:** W1 trebuie complet pentru a porni W2.
