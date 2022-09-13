## Exclusive Queue (Fault Tolerance Pattern)
Using an exclusive queue, a developer could connect multiple instances of an application to the same queue to ensure messages are always processed. The **first instance to connect, or bind, to the queue will receive all messages; should that instance of the application disconnect, the next instance that connected to the queue is prepared to take over activity and start consuming.** 

This provides a fault tolerant method to consuming data from a queue without any downtime.

## Non-Exclusive Queue (Competing Consumer Pattern)
For some use cases, the ingress rate (publish message rate) may be too fast for a single endpoint consumer to keep up. It may be required to have multiple instances of an application share an endpoint and consume data in a **load-balancing manner**.

Based on the acknowledgment rate from the consumer, the broker will deliver the messages at the appropriate rate. This is often referred to as a competing consumer pattern.
