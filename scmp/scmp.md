1.  code review
    1.  one MR 20 comments
2.  complex user workflow
    1.  editor published an article, archiver did some correction. and those correction won't be seen by editor. But if editor change again, the changes will override archiver's change.
3.  Need to ask more about user use case
    1.  editor published an article, v1 with photo a & b, v2 with photo a only. If user want to search article with photo b, which version of article should we return?

# Technical
## Gitlab

### How deployment work
The entry point is `.gitlab-ci.yml`

Sample file
```
...
variables:
  APP_IMAGE_TAG_COMMIT_SHA: $CI_REGISTRY_IMAGE:$CI_COMMIT_REF_SLUG-$CI_COMMIT_SHA
  APP_IMAGE_TAG_LATEST: $CI_REGISTRY_IMAGE:$CI_COMMIT_REF_SLUG-latest
  
stages:
  - test
  - build
  - deploy
  - post-deploy

build:
  <<: *build_template
  script:
    - >
      /kaniko/executor
      --cache=true
      --cache-repo=$CI_REGISTRY_IMAGE/cache
      --build-arg GITLAB_USERNAME
      --build-arg GITLAB_PASSWORD
      --context ./
      --dockerfile ./Dockerfile
      --destination $APP_IMAGE_TAG_COMMIT_SHA
      --destination $APP_IMAGE_TAG_LATEST

Deploy
.deploy_template: &deploy_template
  stage: deploy
  image: registry.scmp.tech/technology/system/kubectl:dev-2
  services: []
  before_script: [kubectl-auth]
  script:
    - /bin/sh k8s/deploy.sh

deploy uat:
  <<: *deploy_template
  only:
    - master
  variables:
    <<: *dev_2_vars
    K8S_ENV_DOMAIN: uat-api.com
  environment:
    name: uat
...
```

sample deploy-app.yaml
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: ${K8S_ENV_SLUG}-app-${K8S_PROJECT}-${K8S_APP_VERSION}
  namespace: technology-newsroom-system
  labels:
    project: ${K8S_PROJECT}
    env: ${K8S_ENV_SLUG}
    app: app
    tier: backend
    version: ${K8S_APP_VERSION}
spec:
  replicas: 2
...
...
      containers:
        - name: app
          image: ${APP_IMAGE_TAG_LATEST}
          imagePullPolicy: Always
          resources:
            limits:
              cpu: 500m
              memory: 2000Mi
            requests:
              cpu: 10m
              memory: 50Mi
...
...
```
deploy.sh
```
# Generate object configuration files for deployment.
for f in $K8S_DEPLOY_DIR/*.yaml
do
  envsubst < $f > "$K8S_DEPLOY_DIR/.generated/$(basename $f)"
done

# Apply deployment.
kubectl apply -f $K8S_DEPLOY_DIR/.generated

# Watch deployment rollout.
for w in $K8S_DEPLOY_DIR/.generated/deploy-*.yaml
do
  kubectl rollout status -f $w &
done
wait
```

Steps
1. git commit with tag
2. build image with gitlab-ci, kaniko and commit tag
3. deploy stage call `deploy.sh`
4. `deploy.sh` sub env variable into `k8s/deploy-app.yaml`
5. kubectl apply -f yaml
6. wait for container to rollout

### Others
1.  pipelink TOCHECK

## Hashicorp Vault
1.  secrets management, work with gitlab
2.  can login with OIDC (Open ID connect) by using Google account

### Reference
https://medium.com/@petertc/openid-connect-a27e0a3cc2ae
## Gsuite

## GRPC
