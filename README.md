# IoT Air Quality Monitoring

## 📌 Описание проекта
Этот проект представляет собой IoT-систему мониторинга качества воздуха. Данные с сенсоров генерируются случайным образом каждые **5 секунд**(можно настроить) для имитации работы реального сенсора. Затем данные проходят через **RabbitMQ**, сохраняются в **PostgreSQL** и обновляются в реальном времени через **WebSocket** на сайте.

Также есть возможность просматривать **исторические данные** для анализа изменений качества воздуха.

⚠️ **Проект находится на стадии разработки.** В будущем все сервисы будут упакованы в Docker-образы, и будет добавлен `docker-compose` для удобного развертывания.

![AirSensor](https://postimg.cc/JDRfwhhm)

## 🖥️ 0. Версия Java: 21

## 🐘 1. Запуск PostgreSQL
В проекте используется **PostgreSQL** с портом **2345:5432**.
Запустите контейнер PostgreSQL:
```bash
docker run --name air-quality-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=air_quality -p 2345:5432 -d postgres
```

## 🐇 2. Запуск RabbitMQ
Запустите контейнер RabbitMQ с панелью управления:
```bash
docker run --name air-quality-rabbit -p 5672:5672 -p 15672:15672 -d rabbitmq:management
```
После запуска панель управления RabbitMQ будет доступна по адресу: [http://localhost:15672](http://localhost:15672) (логин/пароль: `guest/guest`).

## ⚙️ Переменные окружения
Создайте `.env` файл или настройте переменные в `application.yml`:
```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:2345/air_quality
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres

SPRING_RABBITMQ_HOST=localhost
SPRING_RABBITMQ_PORT=5672
SPRING_RABBITMQ_USERNAME=guest
SPRING_RABBITMQ_PASSWORD=guest
```

