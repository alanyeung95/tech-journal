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

# Case study: insufficient space when building docker images
```
$ docker build -t myapp:latest .
[+] Building 12.4s (8/15) => [internal] load build definition from Dockerfile               0.1s
 => => transferring dockerfile: 32B                                        0.0s
[+] Building 45.7s (9/15) => [internal] load .dockerignore                                  0.1s
 => => transferring context: 2B                                            0.0s
 => [internal] load metadata for docker.io/library/python:3.11-slim        2.3s
 => [1/11] FROM docker.io/library/python:3.11-slim@sha256:abc123...        3.2s
 => => resolve docker.io/library/python:3.11-slim@sha256:abc123...         2.1s
 => => sha256:9b3977197b4f8  3.40kB / 3.40kB                               0.0s
 => [2/11] RUN apt-get update && apt-get install -y --no-install-recommends  18.4s
 => [3/11] RUN pip install --no-cache-dir -r requirements.txt               12.1s
 => [4/11] COPY . /app                                                      0.8s
 => [5/11] WORKDIR /app                                                     0.2s
 => [6/11] RUN python -m compileall .                                       3.4s
 => ERROR [7/11] RUN pip install --no-cache-dir torch==2.2.0               5.6s
------
 > [7/11] RUN pip install --no-cache-dir torch==2.2.0:
2.2.0
Collecting torch==2.2.0
  Downloading torch-2.2.0-cp311-cp311-manylinux1_x86_64.whl (755.5 MB)
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 523.4/755.5 MB 24.5 MB/s eta 0:00:10
ERROR: Could not install packages due to an OSError: [Errno 28] No space left on device
------
Dockerfile:12
--------------------
  11 |     COPY . /app
  12 | >>> RUN pip install --no-cache-dir torch==2.2.0
  13 |     WORKDIR /app
--------------------
ERROR: failed to solve: process "/bin/sh -c pip install --no-cache-dir torch==2.2.0" did not complete successfully: exit code: 1
```
```
$ df -h /var/lib/docker
Filesystem      Size  Used Avail Use% Mounted on
/dev/sda1        50G   48G   0   96% /
```

Edit (or create) /etc/docker/daemon.json:
```
{
  "data-root":"/home/docker"
}
```

Then:
```
sudo systemctl stop docker
sudo rsync -aP /var/lib/docker/ /home/docker/
sudo systemctl start docker
```

Verify:
```
docker info | grep "Docker Root Dir"
```

# Containerd
## lazy pulling

Normally, when you `docker pull` an image: Docker downloads the entire image (all compressed layers). Then it extracts all layers before you can run the container.

With lazy pulling (via snapshotters like stargz or nydus): Only the metadata and needed chunks are fetched initially. Files are streamed on‑demand when the container actually accesses them. You can start the container almost immediately, even before the full image is downloaded.

### Advantages
1. Faster startup → Containers can begin running seconds after pull starts.
2. Reduced bandwidth → Only the files actually used are downloaded.
3. Lower disk usage → Avoids storing unused files locally.
