# TTL

# Redis
## Active cache
## Passive cache

# k8s

## Ingress
An ingress is really just a set of rules to pass to a controller that is listening for them. 
You can deploy a bunch of ingress rules, but nothing will happen unless you have a controller that can process them.

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

## Service 
By using selector, enabling external access from `targetPort` to internal port of the pod.

### Reference:
https://ithelp.ithome.com.tw/articles/10220414

https://tachingchen.com/tw/blog/kubernetes-service/


# TODO
- [ ] https://medium.com/@C.W.Hu/kubernetes-implement-ingress-deployment-tutorial-7431c5f96c3e
- [ ] https://www.smashingmagazine.com/2020/04/nodejs-internals/
