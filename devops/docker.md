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

# Technical notes
```
docker-compose start
```
Starts existing containers for a service. docker start with flag '-a' can let you attach into container and see the output

```
docker-compose up
```
Builds, (re)creates, starts, and attaches to containers for a service. Can apply updated code with mounted volumes

```
docker-compose run
```
Runs a one-time command against a service. For example, the following command starts the web service and runs bash as its command. e.g. `$ docker-compose run web bash`

```
docker service scale mywebapp=5
```
scale a service


# docker swarm

Swarm mode is an advanced feature for managing a cluster of Docker daemons.

ref: https://docs.docker.com/guides/deployment-orchestration/swarm-deploy/

# what is the meaning of '-it' flag in docker run cmd?
default will connect the stdout inside the container, if we want to input something into container, we need '-it' for stdin.
The -it instructs Docker to allocate a pseudo-TTY connected to the container’s stdin; creating an interactive bash shell in the container.
-i for stdin, stdout, stderr, -t for nice formatting
