FROM amazoncorretto:21.0.4-alpine3.18

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY . .

# Expose the port that the application will run on
EXPOSE 8080
# Authorization env
ENV AUTHORIZATION ""

CMD ["./mvnw", "spring-boot:run"]
