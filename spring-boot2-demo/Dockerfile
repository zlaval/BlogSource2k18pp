FROM centos

RUN yum install -y java

VOLUME /tmp
ADD /spring-boot2-demo-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]