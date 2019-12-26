# Flipi.DataFetcher
Background process to fetch data from flights API and publish results to a streaming message system.

Status of fetcher:
[![Build Status](https://jcrecio.visualstudio.com/StudyOfBestFlights_NO_NAME/_apis/build/status/jcrecio.StudyOfBestFlights_NO_NAME?branchName=master)](https://jcrecio.visualstudio.com/StudyOfBestFlights_NO_NAME/_build/latest?definitionId=7&branchName=master)

Run the fetcher (run_fetcher.sh)
0. Move to kafka folder
$ cd {your kafka installation}
1. Start zookeeper (you need to use linux or command line linux based like cygwin or gitbash)
$ bin\zookeeper-server-start.sh config/zookeeper.properties
2. Start kafka server
$ bin/kafka-server-start.sh config/server.properties
3. Create the topic of messages if it does not exist
> bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test --if-not-exists 

