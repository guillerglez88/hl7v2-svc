# hl7v2-svc

HL7 v2.x Service

# Docker

``` sh
docker build . --target prod --tag guillerglez88/hl7v2-svc:latest

docker run -p 8081:80 -p 5555:5555 --name hl7v2-svc guillerglez88/hl7v2-svc:latest
```

