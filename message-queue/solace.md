## Exclusive Queue (Fault Tolerance Pattern)
Using an exclusive queue, a developer could connect multiple instances of an application to the same queue to ensure messages are always processed. The **first instance to connect, or bind, to the queue will receive all messages; should that instance of the application disconnect, the next instance that connected to the queue is prepared to take over activity and start consuming.** 

This provides a fault tolerant method to consuming data from a queue without any downtime.

## Non-Exclusive Queue (Competing Consumer Pattern)
For some use cases, the ingress rate (publish message rate) may be too fast for a single endpoint consumer to keep up. It may be required to have multiple instances of an application share an endpoint and consume data in a **load-balancing manner**.

Based on the acknowledgment rate from the consumer, the broker will deliver the messages at the appropriate rate. This is often referred to as a competing consumer pattern.

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
