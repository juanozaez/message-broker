# Message brokers

Project consisting of several microservices communicating using an asynchronous event bus

## Architecture
               
               +-------------------+
               |                   |
               |  Message-broker   |
               |                   |
               +------+-----+------+
                      ^     |
          +-----------+     +------------+
          |     Publish     Consume      |
          |                              |
          |                              v
+---------+------------+      +---------+------------+
|                      |      |                      |
|     User-service     |      | Notification-service |
|                      |      |                      |
+----------------------+      +----------------------+

## Implementations
It can be run using different message brokers
- Rabbit MQ

## Set up
- Start docker
- run docker-compose start
- Start both projects:
  - Run UserApplication.kt
  - Run NotificationApplication.kt
    
## Test