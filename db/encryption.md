
# Mongodb
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

# MySQL

## At Rest
ref: https://dev.mysql.com/doc/refman/8.4/en/innodb-data-encryption.html

MySQL supports data-at-rest encryption, which ensures that your data is encrypted when stored on disk. This feature is crucial for complying with security standards and regulations that require sensitive data protection. MySQL uses a combination of tablespace encryption and column encryption to secure data stored within the database. Here’s an overview and example of setting up data-at-rest encryption in MySQL:

### 1. **Enable Encryption at the Server Level**

To use MySQL's data-at-rest encryption, you need to ensure your MySQL installation has been compiled with the InnoDB storage engine and the file-per-table option enabled. You also need to have the keyring plugin loaded to manage encryption keys.

#### Installing the Keyring Plugin

First, you need to install and configure a keyring plugin. MySQL offers several keyring plugins, but a common choice is `keyring_file`, which stores the encryption keys in a file on the server.

```sql
-- Install the keyring plugin
INSTALL PLUGIN keyring_file SONAME 'keyring_file.so';

-- Configure the keyring plugin
SET GLOBAL keyring_file_data = '/var/lib/mysql-keyring/keyring';
```

**Note:** Store the keyring file in a secure location and manage access permissions carefully.

### 2. **Enable Tablespace Encryption**

Once the keyring plugin is ready, you can enable encryption for new InnoDB tablespaces.

```sql
-- Enable InnoDB tablespace encryption
SET GLOBAL innodb_encrypt_tables = 'ON';
```

This setting will ensure that all new InnoDB tablespaces created are encrypted.

### 3. **Encrypt Existing Tables**

For existing tables, you can turn on encryption by altering each table. Here’s how you can encrypt an existing table:

```sql
-- Encrypt an existing table
ALTER TABLE mytable ENCRYPTION='Y';
```

### 4. **Encrypting the Binary and Redo Logs**

To ensure comprehensive data protection, you should also encrypt binary and redo logs:

```sql
-- Enable encryption for binary logs
SET GLOBAL binlog_encryption = 'ON';

-- Enable encryption for redo logs
SET GLOBAL innodb_redo_log_encrypt = 'ON';
```

### 5. **Column-Level Encryption**

For specific sensitive data, you might choose to use column-level encryption, which can be implemented using MySQL functions `AES_ENCRYPT()` and `AES_DECRYPT()`. Here’s a simple example:

```sql
-- Encrypting a column value
UPDATE mytable
SET sensitive_column = AES_ENCRYPT('sensitive data', 'encryption_key');

-- Decrypting a column value
SELECT AES_DECRYPT(sensitive_column, 'encryption_key') FROM mytable;
```
