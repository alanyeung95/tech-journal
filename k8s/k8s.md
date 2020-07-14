# Hello world
https://kubernetes.io/docs/tutorials/hello-minikube/

# Concepts
## Pods
A Kubernetes Pod is a group of one or more Containers

## Deployment
A Kubernetes Deployment checks on the health of your Pod and restarts the Pod's Container if it terminates. Deployments are the recommended way to manage the creation and scaling of Pods.

## Ingress
https://kubernetes.io/docs/concepts/services-networking/ingress/

An API object that manages external access to the services in a cluster, typically HTTP.
Ingress may provide load balancing

### Ingress tutorial
https://kubernetes.io/docs/tasks/access-application-cluster/ingress-minikube/

# Cheatsheet
```
minikube ip
kubectl create deployment web --image=gcr.io/google-samples/hello-app:1.0
kubectl apply -f xxx.yaml
