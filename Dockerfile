FROM openjdk:14 AS build

# Possible to execute the mave bom build here? Would need to dowload maven...

WORKDIR /build

COPY . .
RUN ./gradlew build --no-daemon -p .

FROM openjdk:14
WORKDIR /app
COPY --from=build /build/build/libs/build-*.jar app.jar
COPY ./docker-entrypoint .

RUN chmod +x ./docker-entrypoint

# Running the app
ENTRYPOINT ./docker-entrypoint