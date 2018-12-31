# stock-market-microservices
Stock market microservices

#Build
mvn clean package

#Run
docker-compose up

#Services links

| Service                | Link                                        | Comments         |
|------------------------|---------------------------------------------|------------------|
| ActiveMQ               | http://localhost:8161/admin                 | Console          |
| MongoDB                | http://localhost:27017                      | Driver port      |
| Prometheus             | http://localhost:9090                       |                  |
| Grafana                | http://localhost:3000                       | Administration   |
| Elastic search         | http://localhost:9200                       | JSON             |
|                        | http://localhost:9200/_cat/indices?v&pretty | Elastic indices  |
| Logstash               | http://localhost:9600                       | JSON             |
| Kibana                 | http://localhost:5601/app/kibana            |                  |
| Config service         | http://localhost:8888                       |                  |
| Discovery service      | http://localhost:8761                       | Eureka           |
| Stock prices producer  | http://localhost:9081/actuator              | Metrics          |
| Stock prices consumer  | http://localhost:9082/actuator              | Metrics          |
|                        | http://localhost:9082/swagger-ui.html       | API doc UI       |
|                        | http://localhost:9082/v2/api-docs           | API doc json     |
