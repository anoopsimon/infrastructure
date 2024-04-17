# Jenkins

# Prerequesites
1. Docker 
2. Docker compose


## Start Jenkins  container

```js
docker-compose up --build -d
```

## Stop Jenkins container

```js
docker-compose down -v
```

Make sure following scriptApproval  
Link : http://localhost:8080/scriptApproval/

_**Signatures to be approved:**_
  * field jenkins.model.Jenkins clouds
  * method hudson.model.Saveable save
  * method hudson.plugins.ec2.EC2Cloud getTemplates
  * method hudson.plugins.ec2.SlaveTemplate setAmi java.lang.String
  * staticMethod hudson.model.Hudson getInstance
  * staticMethod jenkins.model.Jenkins getInstance