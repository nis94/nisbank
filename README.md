**Docker setup for local testing:**
- docker run -d --hostname my-rabbit --name nir-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
- docker run -p 6379:6379 --name nir-redis -d redis

**Run load testing using apache benchmark tool:**
- ab -n 10 -c 2 -v 3 http://localhost:8072/nisbank/cards/api/contact-info

**Create WebHook for Grafana alerting notifications**
- https://hookdeck.com/