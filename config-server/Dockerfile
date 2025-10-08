# Sıfır bir makine olduğunu varsayalım
# Yalnızca donanım.
# Bu projeyi nasıl çalıştırırsınız?

# Bulunduğu klasör ve alt klasörlere erişebilir.

FROM maven:3.9.11-eclipse-temurin-21

WORKDIR /app

COPY ../pom.xml /tmp/parent/pom.xml
RUN mvn -q -f /tmp/parent/pom.xml -N install

COPY pom.xml ./

RUN mvn -DskipTests dependency:go-offline

COPY src ./src

RUN mvn -DskipTests clean package