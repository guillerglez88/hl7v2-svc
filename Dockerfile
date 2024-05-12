FROM clojure:temurin-21-bookworm-slim as build
WORKDIR /app
COPY deps.edn /app/deps.edn
RUN clj -X:deps prep
COPY . .
RUN clj -T:build uber

FROM eclipse-temurin:21-jre-alpine as prod
WORKDIR /app
COPY --from=build /app/target/hl7v2-svc-standalone.jar .
ENV PORT 80
EXPOSE 80
EXPOSE 5555
ENTRYPOINT exec java \
    -XX:+UseContainerSupport \
    -XX:MaxRAMPercentage=85 \
    -Dclojure.server.repl="{:port 5555 :accept clojure.core.server/repl :address \"0.0.0.0\"}" \
    -jar ./hl7v2-svc-standalone.jar
