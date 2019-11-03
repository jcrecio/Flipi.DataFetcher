for linux create the entire thing for kafka and zookeper
https://www.digitalocean.com/community/tutorials/how-to-install-apache-kafka-on-ubuntu-18-04
kafka 11...Ydnx
zookeper hadoop hadoop

for windows or linux, the built in servers for development:
cd ./cygdrive/c/kafka_2.12-2.3.0/kafka_2.12-2.3.0
./bin/zookeeper-server-start.sh config/zookeeper.properties
./bin/kafka-server-start.sh config/server.properties
./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test --if-not-exists 