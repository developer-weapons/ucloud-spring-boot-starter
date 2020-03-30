## ucloud-spring-boot-starter
快速接入 UCloud Starter，如 usms，ufile等

## Usage
1.
```sh
git clone https://github.com/developer-weapons/ucloud-spring-boot-starter.git
```
2.
```sh
cd ucloud-spring-boot-starter
mvn install
```
3.
```xml
<dependency>
    <groupId>com.github.developer.weapons</groupId>
    <artifactId>ucloud-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
4.
```sh
spring.usms.publicKey=pk
spring.usms.privateKey=pk
spring.usms.projectId=id
spring.usms.signContent=id
```
5.
```java
@Autowired
private USMSService usmsService;

USMSMessage message = new USMSMessage();
message.setPhoneNumbers(Lists.newArrayList("PHONE"));
message.setTemplateId("ID");
message.setTemplateParams(Lists.newArrayList("PARAM"));
usmsService.send(message);

```
