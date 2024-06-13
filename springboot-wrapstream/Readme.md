# Spring Boot Wrap stream

## Build and Run

#### 1. login to wrapstream console

[Wrapstream Console login](https://console.warpstream.com/virtual_clusters) 

create new cluster name 'customer'

#### 2. Create Credentials 
````
cluster --> credentials --> create credentials --> save it
````
#### 3. Update username and password for wrapstream in application.properties

````shell
customer.sasl.username=ccun_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
customer.sasl.password=ccp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
````

## Links

1. [Swagger ui](http://localhost:8080/swagger-ui/index.html)
2. [h2 console](http://localhost:8080/h2-console)  user : sa, password: password 

### Reference Documentation
For further reference, please consider the following sections:

* [WrapStream](https://www.warpstream.com/)

