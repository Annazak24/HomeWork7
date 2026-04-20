FROM maven:3.9.4-eclipse-temurin-21

ENV THREAD_COUNT=5

WORKDIR /app

COPY . /app

RUN mkdir -p /app/allure-results

CMD mvn test \
      -DthreadCount=${THREAD_COUNT} \
      -Dmaven.test.failure.ignore=true \
      -Dallure.results.directory=allure-results