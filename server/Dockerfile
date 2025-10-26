FROM amazoncorretto:25.0.1-alpine3.22

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
