# Săptămâna 3: Observabilitate + Cloud

Metrici, logging centralizat, CI/CD și deploy pe cloud.

## Structură

```
week-3-observability/
├── README.md                    (tu ești aici)
├── 01-metrics-monitoring/       (Prometheus, Grafana)
├── 02-centralized-logging/      (ELK stack complet)
├── 03-cicd-github-actions/      (pipeline automat)
├── 04-cloud-deployment/         (AWS/Azure/GCP)
└── NOTES.md
```

## Pași

### 1. Metrics & Monitoring (2-3 ore)
- Adaug Actuator și Micrometer în servicii
- Prometheus scrape-ază metrice
- Grafana vizualizare

### 2. Centralized Logging (2-3 ore)
- ELK stack: Elasticsearch, Logstash, Kibana (sau alternativă mai ușoară)
- Toate serviciile trimit loguri
- Query și alerting

### 3. CI/CD — GitHub Actions (2-3 ore)
- Workflow: test, build, push Docker image
- Deploy automat pe cloud

### 4. Cloud Deployment (2-3 ore)
- Crează cont AWS Free Tier (sau alte cloud)
- Deploy serviciile cu docker-compose sau orchestrator ușor (ECS/App Engine)
- Testing în cloud

---

**Status:** ⏳ Aștept finalizarea W2

**Dependență:** W2 trebuie complet pentru a porni W3.
