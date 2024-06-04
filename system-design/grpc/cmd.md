https://grpc.io/docs/languages/python/quickstart/

```
python -m grpc_tools.protoc -I../../protos --python_out=. --pyi_out=. --grpc_python_out=. ../../protos/helloworld.proto

python greeter_client.py

python greeter_server.py
```
