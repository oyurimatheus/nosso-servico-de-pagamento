# Nosso serviço de pagamento

## Setup

O projeto usa o redis como banco para cache. Para subir o projeto, rode esse comando do Docker para ter o redis disponível:
```shell script
docker container run -d --rm -p 6379:6379 --name redis redis
```  