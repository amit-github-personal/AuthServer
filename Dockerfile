FROM ubuntu:14.04

RUN sed 's/main$/main universe/' -i /etc/apt/sources.list
RUN apt-get update
RUN apt-get upgrade -y

# Download and install wkhtmltopdf
RUN apt-get install -y build-essential wget gdebi
#RUN wget https://github.com/wkhtmltopdf/wkhtmltopdf/releases/download/0.12.4/wkhtmltox-0.12.4_linux-generic-amd64.tar.xz
#RUN tar -xf wkhtmltox-0.12.4_linux-generic-amd64.tar.xz
#RUN mv /wkhtmltox/bin/* /usr/bin/
RUN wget https://download.java.net/java/ga/jdk11/openjdk-11_linux-x64_bin.tar.gz
RUN tar xzvf openjdk-11_linux-x64_bin.tar.gz
RUN mkdir /usr/lib/jvm
RUN mv jdk-11 /usr/lib/jvm/openjdk-11-manual-installation/
RUN update-alternatives --install /usr/bin/java java /usr/lib/jvm/openjdk-11-manual-installation/bin/java 1
RUN update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/openjdk-11-manual-installation/bin/javac 1
COPY target/*.jar /app/app.jar
EXPOSE 8080

CMD [ "java", "-jar", "/app/app.jar" ]