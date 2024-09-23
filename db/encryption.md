
# mongodb encryption
## At Rest
https://adnan-tech.com/encrypt-and-decrypt-strings-using-node-js-and-mongo-db/

## In Transit

### Step 1: Configure MongoDB to Use TLS

To configure MongoDB to use TLS, you need to modify the MongoDB configuration file (`mongod.conf`) or pass the appropriate command-line options to the `mongod` and `mongos` processes.

1. **Obtain a TLS Certificate**: You can use a certificate from a trusted Certificate Authority (CA) or generate a self-signed certificate. For production environments, it’s recommended to use a certificate from a trusted CA.

   Generate a self-signed certificate using OpenSSL (for testing purposes):
   ```bash
   openssl req -newkey rsa:2048 -new -x509 -days 365 -nodes -out mongodb-cert.crt -keyout mongodb-cert.key
   cat mongodb-cert.key mongodb-cert.crt > mongodb.pem
   ```

2. **Configure MongoDB to Use the TLS Certificate**:
   Edit the MongoDB configuration file (`mongod.conf`) to enable TLS and specify the certificate file:

   ```yaml
   net:
     port: 27017
     bindIp: 127.0.0.1
     tls:
       mode: requireTLS
       certificateKeyFile: /path/to/mongodb.pem
   ```

   In this configuration:
   - `mode: requireTLS` forces MongoDB to use TLS for all connections (alternatively, `preferTLS` allows both TLS and non-TLS connections).
   - `certificateKeyFile` points to the combined certificate and key file.

3. **Restart MongoDB**:
   After updating the configuration, restart your MongoDB server to apply the changes:
   ```bash
   sudo systemctl restart mongod
   ```

### Step 2: Connect to MongoDB with TLS Enabled

When connecting from a client (e.g., application, MongoDB shell), you need to specify that the connection should use TLS.

- **Connecting with the MongoDB Shell**:
  Use the `--tls` option, and if you’re using a self-signed certificate, you might need to use `--tlsAllowInvalidCertificates` (not recommended for production):

  ```bash
  mongo --tls --host hostname.example.com --port 27017 --tlsAllowInvalidCertificates
  ```

- **Connecting from Application Code** (example in Python using PyMongo):
  ```python
  from pymongo import MongoClient

  uri = "mongodb://hostname.example.com:27017/?tls=true&tlsCAFile=/path/to/ca.pem"
  client = MongoClient(uri, tlsAllowInvalidCertificates=True)  # only for self-signed certificates
  db = client.test
  ```

### Step 3: Validate and Monitor

- **Validate the Setup**: Confirm that clients can only connect using TLS and that non-TLS connections are refused.
- **Monitor the Logs**: Check MongoDB logs for any errors related to TLS or unauthorized access attempts.

### Additional Security Measures

- **Certificate Validation**: In production, ensure that clients validate the server's certificate correctly to protect against man-in-the-middle attacks.
- **Firewall Configuration**: Configure firewalls to allow only secure connections on the designated MongoDB port.
