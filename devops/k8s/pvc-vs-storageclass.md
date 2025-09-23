# Examples

Here's a sample Kubernetes configuration to deploy MongoDB with Alibaba Cloud (Aliyun) disk-based storage using a PersistentVolumeClaim (PVC).

storageclass:
```
apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: aliyun-disk
provisioner: diskplugin.csi.alibabacloud.com
parameters:
  type: cloud_efficiency  # or cloud_ssd / cloud_essd
  zoneId: cn-hangzhou-e   # match your cluster's zone
volumeBindingMode: WaitForFirstConsumer
```

pvc:
```
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mongodb-data-claim
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 10Gi
  storageClassName: aliyun-disk

```

deployment
```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mongodb
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mongodb
  template:
    metadata:
      labels:
        app: mongodb
    spec:
      containers:
        - name: mongodb
          image: mongo:6.0
          ports:
            - containerPort: 27017
          env:
            - name: MONGO_INITDB_ROOT_USERNAME
              value: your_admin_username
            - name: MONGO_INITDB_ROOT_PASSWORD
              value: your_admin_password
          volumeMounts:
            - name: mongodb-data
              mountPath: /data/db
      volumes:
        - name: mongodb-data
          persistentVolumeClaim:
            claimName: mongodb-data-claim
```

# Notes
## StorageClass
1. Defines how storage is provisioned.
2. Specifies the type of storage (e.g. SSD, HDD),
3. Specifies the provisioner (e.g. Aliyun CSI, Amazon EBS)

## PVC
1. PersistentVolumeClaim (PVC): Requests what storage is needed.
2. Specifies how much storage you want and access mode (e.g. ReadWriteOnce).
3. References a StorageClass to tell Kubernetes which kind of volume to create.
4. Provide a label
