## This repositor conains multiple application
### Run
* Requirement
    * docker
    * jre1.8
    * yarn
* Steps
    1. Enter ./keycloak folder, and exec docker-compose up
    2. Enter ./keycloak-react, and exec yarn server
    3. exec ./gradlew bootRun --parallel
    4. open browser and visit [http://localhost:7000](http://localhost:7000)
### Describe
1. keycloak-saml
    Support SAML protocol to security web application. The adapter use spring boot security.
2. keycloak-springbootsecurity
    Support OpenIdConnect protocol to security application, which use spring boot and spring security.
3. keycloak-react
    Support OpenIdConnect protocol to security application, which use keycloak javascript adapter and send token to service.
4. keycloak-backend-springbootsecurity
    Support OpenIdConnect protocol to security application, which use use spring boot and spring security.