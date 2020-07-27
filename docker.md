# Benefits

## Standardization 
by using same image and dockerfile, it can ensure the application in production is exactly the same as local development.

## Isolation
Remove and re-creation of container can ensure the environment is clean for development or deployment. And you don't need to make your host environment dirty.

## Lightweight and faster than VM
VM need a copy of OS, and slower to boot. Docker with alpine is is only 5 MB in size

## Portable
The docker image can be run in any machine like Windows, Ubuntu, desktop, labtop as long as the OS has installed docker

# Drawbacks
## Still slower than running application on host machine
1. kick start and shutdown the container
2. communication through the docker network

## Longer time to setup and debugging
1. dockerfile and yaml file
2. debugging for setup like environment variables passing
3. personally don't prefer for <b> POC </b> purpose

# Useful command
remove all unused images and mounted volumes
```
docker system prune --volumes
```

show log
```
docker logs -f  b063c4f29572
```
