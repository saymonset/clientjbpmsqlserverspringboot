
#Se crea un proyecto en spring
spring init -dweb,data-jpa,h2,thymeleaf --build maven mysaymon2app --force


 //Colocms el nombfre del bean
 @Service (“employeeManager”)
 @Repository ("employeeDao")

Se compila el proyecto
gradle clean build

Se corre el proyecto
 java -jar build/libs/gs-rest-saymon-0.1.0.jar

#Se crea la bd mysql en docker
#En src/main/reource/db esta los archvos para crear la bd y llenarla
 docker run  --name mysql_bd --net bridge -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=musicdb -e MYSQL_USER=spring -e ALLOW_EMPTY_PASSWORD=yes -e MYSQL_PASSWORD=123456 -p 3312:3306 -d mariadb:5.5
