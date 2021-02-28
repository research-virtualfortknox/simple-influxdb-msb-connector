# A simple MSB to Apache Kafka Connector 
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fresearch-virtualfortknox%2Fsimple-influxdb-msb-connector.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fresearch-virtualfortknox%2Fsimple-influxdb-msb-connector?ref=badge_shield)


This application writes MSB events into InfluxDB.

## Usage

#### Prerequisites
* Java 8 or higher

#### Configuration

* Edit the application.properties-file

* influxdb.server_url = http://localhost:8089
* influxdb.user = user
* influxdb.password = password
* influxdb.batchmode = true # Enable for better performance 
* influxdb.useSourceTimestamp = true  # If false, this app will inject the current timestamp

* msb.truststore = ./cert/CERTS.trs
* msb.url = ws://localhost:8085
* app.uuid = 7ef0549b-9571-486f-9e24-75c1e9bb76df
* app.name = InfluxDB-MSB-Connector
* app.token = RandomTokenString
* app.description = "This application writes MSB events into InfluxDB"


#### How to build

* Run: `mvn clean install`


#### How to run

* Place the jar with dependencies and your edited application.properties-file in the same directory

* Open a bash or CMD in the same directory as the .jar

* Run: `java -jar simple_influxdb_msb_connector-1.0.0-SNAPSHOT.jar.jar`


## Docker

* Docker Hub https://hub.docker.com/r/arthurgrigo/simple-influxdb-msb-connector


## Usage with Docker-Compose

#### Standalone

* Place [docker-compose.yml](docker-compose/standalone/docker-compose.yml) and [env.list](docker-compose/standalone/env.list) in one directory

* Edit [env.list](docker-compose/standalone/env.list) to your needs 

* Run: `docker-compose up -d`

## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fresearch-virtualfortknox%2Fsimple-influxdb-msb-connector.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fresearch-virtualfortknox%2Fsimple-influxdb-msb-connector?ref=badge_large)