<h4>Microloan Camunda 7 Demo Project</h4>
<p>Данный проект описывает выдуманный проект по списанию средств</p>
<h6>Структура проекта</h6>
<p>Проект состоит из 3х модулей:</p>
<ul>
<li>camunda-client Содержит логику процесса в виде External Task Workers</li>
<li>camunda-server Содержит сервер приложения BPMS Camunda</li>
<li>database Содержит конфигурацию развертывания БД PostgreSQL в docker контейнере</li>
</ul>

<p>Для запуска проекта необходимо собрать проект а затем запустить в корне проекта команду:</p>

```
docker-compose up -d --build
```

<p>Администрирование процессов Camunda Cockpit:</p>

```
UTL: http://localhost:8088
Login: admin
Password: ~!QAZxsw2
```

<p>Старт процесса:</p>

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
