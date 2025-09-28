# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.6/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.6/gradle-plugin/packaging-oci-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.6/reference/using/devtools.html)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/3.5.6/reference/web/reactive.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


# Generating PKCS12 certificates for SSL connections

Commands: 
* keytool -genkeypair -alias myapp  -keyalg RSA  -keysize 2048  -validity 365 -storetype PKCS12  -keystore keystore.p12  -storepass skhskh  -keypass skhskh  -dname "CN=localhost, OU=Dev, O=CTS, L=HYD, S=TG, C=IN"

* keytool -exportcert -alias myapp -file myapp.crt -keystore keystore.p12 -storepass skhskh -rfc

* keytool -importcert -alias myapp -file myapp.crt -keystore truststore.p12 -storepass skh123 -storetype PKCS12 -noprompt

Below command run on gitbash: 
* openssl x509 -in myapp.crt -out myapp.pem



