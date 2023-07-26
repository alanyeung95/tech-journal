the setup steps are referenced from: 

https://www.youtube.com/watch?v=_lpDfMkxccc&t=744s&ab_channel=ThatDevOpsGuy

## Setup steps

### 1. Step up RABBITMQ_ERLANG_COOKIE

step up RABBITMQ_ERLANG_COOKIE
```
echo -n "cookie-value" | base64
```

then replace the `${{RABBITMQ_ERLANG_COOKIE}}` inside the `rabbitmq-secret.yaml`

### 2. Step up storage

then replace the `${{RABBITMQ_STORAGE_PROVISIONER}}` inside the `rabbitmq-storageclass.yaml` with`kubernetes.io/aws-ebs`, `kubernetes.io/no-provisioner` or other type.

Please refer to https://kubernetes.io/docs/concepts/storage/storage-classes

### 3. Apply k8s yaml files

```
kubectl apply -f rabbitmq-rbac.yaml
kubectl apply -f rabbitmq-configmap.yaml
kubectl apply -f rabbitmq-secret.yaml
kubectl apply -f rabbitmq-storageclass.yaml
kubectl apply -f rabbitmq-statefulset.yaml
```

### 4. Test deployment
port forward one of the pod and then try to access the rabbitMQ management tool
```
kubectl port-forward rabbitmq-0 8080:15672
```

