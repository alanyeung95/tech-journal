## Istio
### Why use Istio?

**Istio bring advanced network features in k8s**

Istio makes it easy to create a network of deployed services with load balancing, service-to-service authentication, monitoring, and more, with few or no code changes in service code. You add Istio support to services by deploying a special sidecar proxy throughout your environment that intercepts all network communication between microservices, then configure and manage Istio using its control plane functionality, which includes:

Automatic load balancing for HTTP, gRPC, WebSocket, and TCP traffic.

Fine-grained control of traffic behavior with rich routing rules, retries, failovers, and fault injection.

A pluggable policy layer and configuration API supporting access controls, rate limits and quotas.

Automatic metrics, logs, and traces for all traffic within a cluster, including cluster ingress and egress.

Secure service-to-service communication in a cluster with strong identity-based authentication and authorization.

reference: https://istio.io/latest/docs/concepts/what-is-istio/

### kiali (istio visual tool)

Kiali answers the questions:
```
Which microservices are part of my service mesh?
How are they connected?
How are they performing?
How can I operate on them?
```

show using and non-using service as a graph

https://kiali.io/documentation/latest/features/#_graph

### Istio and grpc
https://medium.com/getamis/istio-%E5%9F%BA%E7%A4%8E-grpc-%E8%B2%A0%E8%BC%89%E5%9D%87%E8%A1%A1-d4be0d49ee07

### Additional reading
1. https://ithelp.ithome.com.tw/articles/10216453

## sidecar
inject containers into a pod

example yaml
```
apiVersion: apps/v1
kind: Deployment
metadata:
...
spec:
  replicas: 2
  revisionHistoryLimit: 0
  strategy:
    type: RollingUpdate
...
  template:
    metadata:
      annotations:
        # it will show the request log inside the 'istio-proxy' containers
        sidecar.istio.io/inject: 'true'
```
