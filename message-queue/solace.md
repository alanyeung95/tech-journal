## Exclusive Queue (Fault Tolerance Pattern)
Using an exclusive queue, a developer could connect multiple instances of an application to the same queue to ensure messages are always processed. The **first instance to connect, or bind, to the queue will receive all messages; should that instance of the application disconnect, the next instance that connected to the queue is prepared to take over activity and start consuming.** 

This provides a fault tolerant method to consuming data from a queue without any downtime.

## Non-Exclusive Queue (Competing Consumer Pattern)
For some use cases, the ingress rate (publish message rate) may be too fast for a single endpoint consumer to keep up. It may be required to have multiple instances of an application share an endpoint and consume data in a **load-balancing manner**.

Based on the acknowledgment rate from the consumer, the broker will deliver the messages at the appropriate rate. This is often referred to as a competing consumer pattern.

## kerberos connecton
Kerberos provides a centralized authentication server whose function is to authenticate users to servers and servers to users. In Kerberos Authentication server and database is used for client authentication. Kerberos runs as a third-party trusted server known as the Key Distribution Center (KDC). Each user and service on the network is a principal. 

### entity
![image](https://user-images.githubusercontent.com/45751387/206384363-1ad832d4-0089-4c0a-b05a-b622e94247fa.png)

1. Kerberos principals. Any unique identity that Kerberos can assign a ticket to. For most users, a principal is the same as a user ID. It also includes hosts and services that can be assigned Kerberos tickets. Individual clients are one type of Kerberos principal. The service principal is an identity assigned to an application service that is accessed through Kerberos.

2. Kerberos application servers. Any system providing access to resources that need client authentication through Kerberos. For example, application servers can include file and print services, terminal emulation, remote computing and email.

3. Kerberos KDC (key distribution center). 

### .net service authorization with kerberos 
make sure the krun the windows service/ visual studio as a different domain user
![image](https://user-images.githubusercontent.com/45751387/206383407-5144deb5-9670-463c-a961-7472f07cadcb.png)

### ref
https://www.geeksforgeeks.org/kerberos/

## SSL auth
1. install cert (root cert and other certs) to windows credential manager)
2. load those cert inside .net application as truststore


### ref
https://stackoverflow.com/questions/1205295/get-list-of-certificates-from-the-certificate-store-in-c-sharp

## connectoin code
        private static  ISession SessionOpen(EventHandler<MessageEventArgs> handleMessageEvent, SolaceSession ss)
        {
```
            ISession session;
            IContext context;
            
            // Solace related member variable
            ContextProperties contextProps = new ContextProperties();
            SessionProperties sessionProps = new SessionProperties();

            // To enable session reconnect
            sessionProps.ReconnectRetries = ss.ReconnectRetries;
            sessionProps.ReconnectRetriesWaitInMsecs = ss.ReconnectRetriesWaitInMsecs;
            sessionProps.TopicDispatch = true;

            sessionProps.SendBlocking = true;
            sessionProps.ReapplySubscriptions = true;

            if (ss.StandardConnection.Enable)
            {
                sessionProps.Host = ss.StandardConnection.Host;
                sessionProps.VPNName = ss.StandardConnection.VPNName;
                sessionProps.UserName = ss.StandardConnection.UserName;
                sessionProps.Password = ss.StandardConnection.Password;
            }
            //using SSL
            else if (ss.SSLConnection.Enable)
            {
                sessionProps.Host = ss.SSLConnection.Host;
                sessionProps.VPNName = ss.SSLConnection.VPNName;
                sessionProps.AuthenticationScheme = AuthenticationSchemes.GSS_KRB;

                if (ss.SSLConnection.SSLValidateCertificate)
                {
                    X509Certificate2Collection collection = new X509Certificate2Collection();

                    foreach (string certPath in ss.SSLConnection.SSLClientCertificateFileList)
                    {
                        X509Certificate2 cert = new X509Certificate2(AppDomain.CurrentDomain.BaseDirectory + certPath);
                        collection.Add(cert);

                    }
                    sessionProps.SSLTrustStore = collection;
                    sessionProps.SSLValidateCertificate = true;
                }
                else
                {
                    sessionProps.SSLValidateCertificate = false;
                }

            }
            //using Kerberos 
            else if (ss.KerberosConnection.Enable)
            {
                sessionProps.Host = ss.KerberosConnection.Host;
                sessionProps.VPNName = ss.KerberosConnection.VPNName;
                sessionProps.UserName = ss.KerberosConnection.UserName;

                sessionProps.AuthenticationScheme = AuthenticationSchemes.GSS_KRB;
                sessionProps.Password = null;
            }


            try
            {
                context = ContextFactory.Instance.CreateContext(contextProps, null);
                session = context.CreateSession(sessionProps
                    , handleMessageEvent
                    , HandleSessionEvent);

            }
            catch (Exception ex)
            {
                throw ex;
            }


            ReturnCode rc = ReturnCode.SOLCLIENT_FAIL;//default
            try
            {
                rc = session.Connect();
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return session;

        }

```

## troubleshooting
### 1. Error loading 'libsolclient.dll'
https://stackoverflow.com/questions/44376366/error-loading-libsolclient-dll

### 2. Could not load file or assembly or one of its dependencies. An attempt was made to load a program with an incorrect format.
https://stackoverflow.com/questions/13134769/could-not-load-file-or-assembly-or-one-of-its-dependencies-an-attempt-was-made
