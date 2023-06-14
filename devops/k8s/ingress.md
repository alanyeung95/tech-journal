## ingress
An API object that manages external access to the services in a cluster, typically HTTP.

Ingress controller determine the external access according to ingress roule

## config
```
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: simple-fanout-example
spec:
  rules:
  - host: foo.bar.com
    http:
      paths:
      - path: /foo
        pathType: Prefix
        backend:
          service:
            name: service1
            port:
              number: 4200
      - path: /bar
        pathType: Prefix
        backend:
          service:
            name: service2
            port:
              number: 8080
```

 
will become

[![](https://mermaid.ink/img/pako:eNqNkl1PwyAUhv8KYTeatLWlUyczu9ILEy-MXq67oOV0I6PQAPUj6n-3Feo2PxJv4OS8532AN7ziSnPAFK8Nazfo9n5eKIQqKUC5o6XfV8dxgm7U2oC1ccMUWwNHl6VZIKkZRyWTTFVgUBIvhJ9ahukIZeez5CJLMpInWU5Wn_QwFMeLt5Na6zcL5lFUkC0ffIHGBp2SNP3pKZkZPeS7h9BZOgse25X-VZXsrAOzx_F6OKaHtppnyzvNV78I5IdAvJD_JUx3AijuA2XWXkGNWsmEQrWQkk4455F1Rm-BTuq6DnX8JLjb0Gn7HFVaakMnaZrODyDbmQ2InJxVcPovSq8dUkIqgbSz0klZlocYssP4E3ekMdFoDG0sSDSEOiyfVT4s0-Hqe17_v3woB21_s7DPcYQbMA0TvP-or8Ncgd0GGigw7UvOzLbAhXrv51jn9MOLqjB1poMIdy1nDq4E639Cg2nNpP3qXnPhtAnN9w96wwqx?type=png)](https://mermaid.live/edit#pako:eNqNkl1PwyAUhv8KYTeatLWlUyczu9ILEy-MXq67oOV0I6PQAPUj6n-3Feo2PxJv4OS8532AN7ziSnPAFK8Nazfo9n5eKIQqKUC5o6XfV8dxgm7U2oC1ccMUWwNHl6VZIKkZRyWTTFVgUBIvhJ9ahukIZeez5CJLMpInWU5Wn_QwFMeLt5Na6zcL5lFUkC0ffIHGBp2SNP3pKZkZPeS7h9BZOgse25X-VZXsrAOzx_F6OKaHtppnyzvNV78I5IdAvJD_JUx3AijuA2XWXkGNWsmEQrWQkk4455F1Rm-BTuq6DnX8JLjb0Gn7HFVaakMnaZrODyDbmQ2InJxVcPovSq8dUkIqgbSz0klZlocYssP4E3ekMdFoDG0sSDSEOiyfVT4s0-Hqe17_v3woB21_s7DPcYQbMA0TvP-or8Ncgd0GGigw7UvOzLbAhXrv51jn9MOLqjB1poMIdy1nDq4E639Cg2nNpP3qXnPhtAnN9w96wwqx)

```
kubectl describe ingress simple-fanout-example
```
```
Name:             simple-fanout-example
Namespace:        default
Address:          178.91.123.132
Default backend:  default-http-backend:80 (10.8.2.3:8080)
Rules:
  Host         Path  Backends
  ----         ----  --------
  foo.bar.com
               /foo   service1:4200 (10.8.0.90:4200)
               /bar   service2:8080 (10.8.0.91:8080)
Events:
  Type     Reason  Age                From                     Message
  ----     ------  ----               ----                     -------
  Normal   ADD     22s                loadbalancer-controller  default/test
 ```
 
## set header on k8s ingress 

first need to ensure `allow-snippet-annotations` inside ingress-nginx-controller configmap is true

```
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  annotations:
    nginx.ingress.kubernetes.io/affinity: cookie
    nginx.ingress.kubernetes.io/affinity-mode: persistent
    nginx.ingress.kubernetes.io/configuration-snippet: |
      more_set_headers "X-Frame-Options: SAMEORIGIN";
    nginx.ingress.kubernetes.io/proxy-body-size: "0"
```
