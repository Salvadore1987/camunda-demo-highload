## Microloan Camunda 7 Demo Project

Данный проект описывает выдуманный проект по списанию средств

Для работы данного примера необходимо скачать и установить в локальный репозиторий стартер:

```
<dependency>
    <groupId>uz.keysoft.camunda</groupId>
    <artifactId>camunda-spring-boot-starter</artifactId>
    <version>${camunda-starter.version}</version>
</dependency>
```

URL на репозиторий: https://github.com/Salvadore1987/Camunda-spring-boot-starter

### Структура проекта</h6>
Проект состоит из 3х модулей:
- camunda-client Содержит логику процесса в виде External Task Workers
- camunda-server Содержит сервер приложения BPMS Camunda
- database Содержит конфигурацию развертывания БД PostgreSQL в docker контейнере

Для запуска проекта необходимо собрать проект а затем запустить в корне проекта команду:

```
docker-compose up -d --build
```

Администрирование процессов Camunda Cockpit:

```
UTL: http://localhost:8088
Login: admin
Password: ~!QAZxsw2
```

Старт процесса:

```
URL: http://localhost:8081/microloan-service/api/v1/loan/claim/start
HTTP Method: POST
JSON: 
{
    "customer": {
        "pinpp": "30712870220066"
    }
}
```
