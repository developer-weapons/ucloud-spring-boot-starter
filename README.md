## ucloud-spring-boot-starter
快速接入 UCloud Starter，如 usms，ufile等

## Usage
1. 在 `pom.xml` 里面添加公开仓库
```xml
<repositories>
    <repository>
        <id>developer-weapons-repository</id>
        <url>https://raw.githubusercontent.com/developer-weapons/repository/master</url>
    </repository>
</repositories>
```
2. 引入对应的包版本
```xml
<dependency>
    <groupId>com.github.developer.weapons</groupId>
    <artifactId>ucloud-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
3. 配置必备属性
```sh
spring.usms.publicKey=pk
spring.usms.privateKey=pk
spring.usms.projectId=id
spring.usms.signContent=id
```
4. 引入操作
```java
@Autowired
private USMSService usmsService;

USMSMessage message = new USMSMessage();
message.setPhoneNumbers(Lists.newArrayList("PHONE"));
message.setTemplateId("ID");
message.setTemplateParams(Lists.newArrayList("PARAM"));
usmsService.send(message);

```
