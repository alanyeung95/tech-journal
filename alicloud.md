# Alibaba Cloud Kubernetes vs self-built  k8s

Two ways to setup k8s on alicloud, one through `Container Service` (built-in) and the other through an `Elastic Compute Service (ECS)` instance (self-built). 

## Advantage of Alibaba Cloud Kubernetes
1. Technical support	
    1. Provides the Kubernetes upgrade capabilities. Supports upgrading a Kubernetes cluster to the latest version with one click.
    2. Alibaba Cloud container team is responsible for solving problems about containers in your environment.
1. Network. High-performance Virtual Private Cloud (VPC) network plug-in.
2. Server Load Balancer. Not need to config the ingress by ourself
3. Storage. Self-built Kubernetes clusters cannot use the storage resources on the cloud.
4. Image repository. High availability. The self-built image repository may crash if you pull images from millions of clients at the same time.

how to setup k8s in container services: https://www.alibabacloud.com/help/doc-detail/161387.htm?spm=a2c63.l28256.b99.58.177d7d1bs5k5eg

## Costs and risks of self-built Kubernetes
1. Building clusters is complicated. You must manually configure the components, configuration files, certificates, keys and tools related to Kubernetes. It takes several days or weeks for professional personnel to build the cluster.
2. For public cloud, it takes you significant costs to integrate with cloud products.
3. Requires continuous upgrade

how to setup self-built k8s: https://www.alibabacloud.com/blog/self-built-kubernetes-on-alibaba-cloud_594019

# Container Service

## Clusters
For examples, displaying cluster name like `k8s-dev`, `k8s-prod` and number of nodes

## Applications
- Deployment: showing pods number, image
- Pods: show pods info like Pod IP, number of CPU, Memory
- PVC: show capacity, access mode, Bound PV

## Ingress and Load Balancer
### Ingresses
Given a domain, rout path to service
```
domain: justalittleplace 
path: `/api/()(.*)` 
name: uat-app-api
service port 3000
```

## setting up k8s cluster
1. set up RAM (resources access management) role.  (for accessing your resources in other service such as Container Service, pods etc)
2. set up k8s cluster: https://www.alibabacloud.com/help/doc-detail/85903.htm?spm=a2c63.p38356.879954.5.215954fczngJq2#task-mmv-33q-n2b
3. create deployment: https://www.alibabacloud.com/help/doc-detail/90406.htm?spm=a2c63.p38356.879954.19.3aff3233nuUZed
