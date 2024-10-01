![image](https://github.com/user-attachments/assets/3e6cbe82-64a6-4c47-a121-ec84a5f51e74)

cd /home/coke/pjava/final/gateway && mvn install && mv -f target/gateway-0.0.1-SNAPSHOT.jar docker/gateway-0.0.1-SNAPSHOT.jar && cd docker/ && sudo docker build -t gateway-img . && cd ../../ &

cd /home/coke/pjava/final/maintenance-service && mvn install && mv -f target/maintenance-service-0.0.1-SNAPSHOT.jar docker/maintenance-service-0.0.1-SNAPSHOT.jar && cd docker/ && sudo docker build -t maintenance-img . && cd ../../ &

cd /home/coke/pjava/final/mechanic-service && mvn install && mv -f target/mechanic-service-0.0.1-SNAPSHOT.jar docker/mechanic-service-0.0.1-SNAPSHOT.jar && cd docker/ && sudo docker build -t mechanic-img . && cd ../../ &

cd /home/coke/pjava/final/review-service && mvn install && mv -f target/review-service-0.0.1-SNAPSHOT.jar docker/review-service-0.0.1-SNAPSHOT.jar && cd docker/ && sudo docker build -t review-img . && cd ../../ &

cd /home/coke/pjava/final/eureka-service && mvn install && mv -f target/eureka-service-0.0.1-SNAPSHOT.jar docker/eureka-service-0.0.1-SNAPSHOT.jar && cd docker/ && sudo docker build -t eureka-img . && cd ../../ &