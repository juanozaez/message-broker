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
      +---------+------------+       +---------+------------+
      |                      |       |                      |
      |     User-service     |       | Notification-service |
      |                      |       |                      |
      +----------------------+       +----------------------+

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

- Post to localhost:8080/users , body: username
- Get to localhost:8081/notifications, response will be the username