# Hello world
https://kubernetes.io/docs/tutorials/hello-minikube/

# Concepts

## Nodes
A node may be a virtual or physical machine, depending on the cluster. Each node contains the services necessary to run Pods, managed by the control plane.

## Pods
A Kubernetes Pod is a group of one or more Containers

## Service
An abstract way to expose an application running on a set of Pods as a network service.

Service will create a `ClusterIp` for a set of pods, so that other service within the cluster can access the target pods by using the cluster ip.

We can also use selector and define `targetPorts` so that service outside the k8s cluster within the same node can access the pods

### Example
request from client -> mapper (router to service label according to prefix) -> service (with port, send reqest to pods according to selector label and targetport) -> pods 

### Reference:
https://ithelp.ithome.com.tw/articles/10220414

https://tachingchen.com/tw/blog/kubernetes-service/

## Deployment
A Kubernetes Deployment checks on the health of your Pod and restarts the Pod's Container if it terminates. Deployments are the recommended way to manage the creation and scaling of Pods.

Deployment manages creating Pods by means of ReplicaSets that needs to keep the replicas (pods) alive (that's kind of the point of using kubernetes).

Port expose is also manage by deployment also

## yaml file
1. config map: store variable e.g. mongo table name, api port
2. deployment yaml: define image tag, deployment environment
3. auto-scaling: `kind: HorizontalPodAutoScaler`, define `minReplicas` && `maxReplicas`

## Ingress
https://kubernetes.io/docs/concepts/services-networking/ingress/

An API object that contains a set of rules and manages external access to the services in a cluster. For example, how we rout a domain to our frontend and backend respectively. Just like reverse-proxy in nginx. Normally we should have an ingress controller to process this set of rules.

Ingress may provide load balancing

sample config:
```
  rules:
  - host: kubernetes.foo.bar
    http:
      paths:
      - backend:
          serviceName: appsvc
          servicePort: 80
        path: /app
```

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

# Ambassador

Ambassador is an open source API gateway designed specifically for use with the Kubernetes container

https://www.youtube.com/watch?v=IcQr2rFWtMw

## deploy

First we should deployment ambassador into our `uat`, `stg`, `prod` environment as an API gateway   

Insdie the `deployment` yaml, we can use the image: `image: quay.io/datawire/ambassador:0.85.0`

you may also create a `service` yaml so that we can expose the ambassador as a service, for example:
```
spec:
  type: LoadBalancer
  externalTrafficPolicy: Local
  selector:
    app: ambassador
    tier: api-gateway
    env: uat
  ports:
    - name: http
      protocol: TCP
      port: 80
      targetPort: 8080
    - name: https
      protocol: TCP
      port: 443
      targetPort: 8443
```

Then it can act as a loadbalancer and api entry point at the same time

## Microservices usage
After deploying other services with `deployment` yaml, we may want to create `service` type so that other service can access the pods.

The beauty of ambassador is, we don't need to centralize the api gateway setting, each microservice can define their only routing rule. 

Sample yaml file
```
apiVersion: v1
kind: Service
metadata:
  name: ${K8S_ENV_SLUG}-app-${K8S_PROJECT}-${K8S_APP_VERSION}
  namespace: default_namespace
  labels:
    project: ${K8S_PROJECT}
    env: ${K8S_ENV_SLUG}
    app: app
    version: ${K8S_APP_VERSION}
  annotations:
    getambassador.io/config: |
      ---
      apiVersion: ambassador/v1
      kind: Mapping
      name: ${K8S_ENV_SLUG}-app-${K8S_PROJECT}-${K8S_APP_VERSION}
      ambassador_id: ambassador-${K8S_ENV_SLUG}
      service: ${K8S_ENV_SLUG}-app-${K8S_PROJECT}-${K8S_APP_VERSION}
      host: ${K8S_ENV_DOMAIN}
      method_regex: true
      method: GET|PUT|POST|DELETE|PATCH|OPTIONS
      prefix: /api/${K8S_APP_VERSION}/assets/
      timeout_ms: 120000
      cors:
        origins: http://local.development,https://swagger.default.domain
        methods: POST, GET, OPTIONS, PUT, DELETE, PATCH
        headers: Accept, Accept-Language, Content-Language, Content-Type
        credentials: true
      retry_policy:
        retry_on: "gateway-error"
        num_retries: 5

spec:
  selector:
    project: ${K8S_PROJECT}
    env: ${K8S_ENV_SLUG}
    app: app
    version: ${K8S_APP_VERSION}
  ports:
    - protocol: TCP
      port: 80
      name: http-app
```

# Secret

Reference: https://kubernetes.io/docs/concepts/configuration/secret/

list k8s secret
```
kubectl get secret
```

read a specific secret content
```
kubectl get secret demo-app -o yaml
```

return 
```
apiVersion: v1
data:
  PASSWORD: XXXXXXXXXXXXXXXXX=
kind: Secret
metadata:
  creationTimestamp: "2019-06-11T08:28:08Z"
  name: demo-app
  namespace: default
  resourceVersion: "96574473"
  selfLink: /api/v1/namespaces/defult/secrets/demo-app
  uid: cc6a20d6-8c22-11e9-aa53-00163e045bab
type: Opaque
```

Apply secret (normally we need to creat the yaml and apply it manually, as we cannot commit it to repo)
```
kubectl apply -f mysecret.yaml
```
return 
```
secret/demo-app created
```
