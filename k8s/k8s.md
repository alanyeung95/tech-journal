# Hello world
https://kubernetes.io/docs/tutorials/hello-minikube/

# Concepts
## Pods
A Kubernetes Pod is a group of one or more Containers

## Service
An abstract way to expose an application running on a set of Pods as a network service.

## Deployment
A Kubernetes Deployment checks on the health of your Pod and restarts the Pod's Container if it terminates. Deployments are the recommended way to manage the creation and scaling of Pods.

Deployment manages creating Pods by means of ReplicaSets that needs to keep the replicas (pods) alive (that's kind of the point of using kubernetes).

Port expose is also manage by deployment also

## Ingress
https://kubernetes.io/docs/concepts/services-networking/ingress/

An API object that manages external access to the services in a cluster, typically HTTP.
Ingress may provide load balancing

### Ingress tutorial
https://kubernetes.io/docs/tasks/access-application-cluster/ingress-minikube/

## Memory
tutorial: https://kubernetes.io/docs/tasks/configure-pod-container/assign-memory-resource/
```
kubectl create namespace mem-example
kubectl config set-context --current --namespace=mem-example
kubectl apply -f  memory-request-limit-2.yaml --namespace=mem-example
```

# Production
## Deployment
```
# Watch deployment rollout.
for w in $K8S_DEPLOY_DIR/.generated/deploy-*.yaml
do
  kubectl rollout status -f $w &
done
wait
```
output
```
alanyeung@takeaguess:~/Git/Notes/k8s$ kubectl set image deployment.v1.apps/nginx-deployment nginx=nginx:1.14.2 --record=true
deployment.apps/nginx-deployment image updated
alanyeung@takeaguess:~/Git/Notes/k8s$ kubectl rollout status deployment.v1.apps/nginx-deployment
Waiting for deployment "nginx-deployment" rollout to finish: 1 out of 3 new replicas have been updated...
Waiting for deployment "nginx-deployment" rollout to finish: 1 out of 3 new replicas have been updated...
Waiting for deployment "nginx-deployment" rollout to finish: 1 out of 3 new replicas have been updated...
Waiting for deployment "nginx-deployment" rollout to finish: 2 out of 3 new replicas have been updated...
Waiting for deployment "nginx-deployment" rollout to finish: 2 out of 3 new replicas have been updated...
Waiting for deployment "nginx-deployment" rollout to finish: 1 old replicas are pending termination...
Waiting for deployment "nginx-deployment" rollout to finish: 1 old replicas are pending termination...
deployment "nginx-deployment" successfully rolled out
```
# Cheatsheet
```
minikube ip
kubectl create deployment web --image=gcr.io/google-samples/hello-app:1.0
kubectl apply -f xxx.yaml

# change image and see the rollout status
kubectl set image deployment/nginx-deployment nginx=nginx:1.16.1 --record
kubectl rollout status deployment.v1.apps/nginx-deployment

# check cpu/memory usage
kubectl describe nodes minikube
```
