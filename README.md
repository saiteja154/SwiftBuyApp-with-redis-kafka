
# 🚀 Spring Boot Microservice with Kafka, Redis, Eureka, and MySQL

This project is a microservice built using **Spring Boot** with integration of:

- ✅ Apache Kafka for messaging
- ⚡ Redis for caching
- 🔍 Eureka Server for service discovery
- 🗃️ MySQL as the relational database
- 🧪 Spring Data JPA for database interaction
- 📦 Maven for dependency management

---

## 📁 Tech Stack

| Tech          | Usage                         |
|---------------|-------------------------------|
| Spring Boot   | Application Framework         |
| Kafka         | Asynchronous Messaging Queue  |
| Redis         | In-Memory Data Store          |
| Eureka        | Service Discovery             |
| MySQL         | Relational Database           |
| Spring Data JPA | ORM for database operations |

---

## 📦 Getting Started

### 🔧 Prerequisites

Make sure the following services are running:

- ✅ MySQL
- ✅ Redis
- ✅ Kafka (Zookeeper + Kafka Broker)
- ✅ Eureka Server


---

Configure application.yml / application.properties Set the following values:


**#Spring-boot**
sever.port=8080

# Eureka
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
spring.cloud.compatibility-verifier.enabled=false


# Kafka
spring.kafka.bootstrap-servers=localhost:9092

create topic:
kafka-topics.sh --create --topic order-events --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

🔹 List Topics
kafka-topics.sh --list --bootstrap-server localhost:9092

🔹 Describe Topic
kafka-topics.sh --describe --topic my-topic --bootstrap-server localhost:9092

🔹 Produce Messages (Console Producer)
kafka-console-producer.sh --topic my-topic --bootstrap-server localhost:9092

🔹 Consume Messages (Console Consumer)
kafka-console-consumer.sh --topic my-topic --bootstrap-server localhost:9092 --from-beginning


🔹 Delete Topic
kafka-topics.sh --delete --topic my-topic --bootstrap-server localhost:9092



# Redis
spring.redis.host=localhost
spring.redis.port=6379


# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/your_database

spring.datasource.username=root
spring.datasource.password=your_password


ProductService url: https://github.com/saiteja154/ProductServices



## 📄 Description

Each service in this project has a specific responsibility and functionality:

- 🗂️ We used a **caching layer** in the **Product Service** to cache frequently accessed products and improve performance.
- 👤 A **User Service** is responsible for **authentication and user management**.
- 📩 **Kafka** is integrated to **send notification emails** when a user signs up.
- 💳 Integrated **multiple payment gateways** like **Razorpay** and **Stripe** to handle secure payment transactions.
- 🌐 **Eureka Server** is used for **service registration and discovery**, enabling us to manage **multiple instances of services** and achieve **load balancing**.
- 🛢️ **MySQL** is used as the **relational database** to store and manage persistent data.
 

