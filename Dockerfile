FROM bitnami/wildfly:33.0.2
COPY target/Lab2_REST-1.0-SNAPSHOT.war /opt/bitnami/wildfly/standalone/deployments/

ENV WILDFLY_USERNAME=admin \
    WILDFLY_PASSWORD=password