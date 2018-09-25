# price

This project contains the code artifacts needed for Price micro services

----

## Steps to build and deploy


##### Build the jar file

```
$ cd price
$ mvn clean install
```

##### Build the container image
```
$ docker build -t price .
```

##### Tag and push the image to Google Container Registry
```
$ docker tag price gcr.io/servicesdemo-213209/price:v1
$ docker push gcr.io/servicesdemo-213209/price:vX
```
Here X is the version number of the image, this will be mapped to build number in CI system

##### Setup your kubectl credentials by running the below command
```
$ gcloud beta container clusters get-credentials testcluster --region us-central1 --project servicesdemo-213209
```

##### Create a secret in kubernetes using the service account credentials json file as given below
```
$ kubectl create secret generic price --from-file service-account.json
```

##### Create a configmap in kubernetes as given below 
```
$ kubectl create configmap price --from-literal "spring-profile=dev"
```
This config map relates to the environment, value will change according to environment

##### Deploy the price service in kubernetes using the below command
```
$kubectl apply -f Deployment.yml
```
Update the image version in the Deployment.yml file before running this command to reflect the latest image version

##### Expose the price service to internet through a load balancer using the below command
```
$kubectl apply -f Service.yml
```
