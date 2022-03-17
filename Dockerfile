FROM openjdk:8u312-jdk-slim

ENV SERVER_INSTALL_FOLDER=/app/influxdb-msb-connector/
ENV JAR_FILE_NAME=simple_influxdb_msb_connector-1.2.0-SNAPSHOT.jar

# Application propertis
ENV INFLUXDB_SERVER_URL=http://localhost:8089
ENV INFLUXDB_USER=root
ENV INFLUXDB_PASSWORD=password
ENV INFLUXDB_BATCHMODE=true
ENV INFLUXDB_USESOURCETIMESTAMP=true

ENV MSB_TRUSTSTORE=./cert/CERTS.trs
ENV MSB_URL=ws://localhost:8085
ENV APP_UUID=7ef0549b-9571-486f-9e24-75c1e9bb76df
ENV APP_NAME=InfluxDB-MSB-Connector
ENV APP_TOKEN=RandomToken
ENV APP_DESCRIPTION="This application writes MSB events into InfluxDB"


RUN apt-get update
RUN apt-get install maven -y

RUN mkdir -p "${SERVER_INSTALL_FOLDER}log"
WORKDIR ${SERVER_INSTALL_FOLDER}

COPY . .
RUN mvn clean install
RUN cp ./target/${JAR_FILE_NAME} ./${JAR_FILE_NAME}

CMD java -jar -Xmx1024m -Xms512m ${JAR_FILE_NAME}

