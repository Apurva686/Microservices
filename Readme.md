Instructions to run the application - 
Bring up all the three applications i.e 
- Api Gateway Service
- Car Service
- Eureka Discovery Service

in different terminals by doing cd in target folder and running command java -jar  <service>-0.0.1-SNAPSHOT.jar

Once all the three services are up on running . Hit the eureka url(http://localhost:8761) with default port 8761 in browser to ensure two instances are runnning one each of api gateway and car service.

Then call the url http://localhost:8080/cool-cars upon which login to Okta will be prompted . Upon successful login list of cool cars will be returned.

Also Hystrix will return an empty list of cars in case of any kind of failure

Zuul Proxy Route URLS are http://localhost:8080/cars and http://localhost:8080/home