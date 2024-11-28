# AWS EC2 Mocking Example

## Overview

This project demonstrates how to mock AWS EC2 instance creation using the **AWS SDK for Java** and **Mockito**. It simulates the behavior of launching an EC2 instance without making real API calls to AWS. This approach is ideal for testing and validating workflows without incurring AWS costs or requiring real cloud infrastructure.

---

## Features

- **EC2 Instance Simulation**: Simulates the creation of an AWS EC2 instance using mocked responses.
- **Delay Simulation**: Introduces delays to mimic real-world instance launch times.
- **Testing-Friendly**: Uses Mockito to mock `AmazonEC2` client calls, making it safe and cost-free for testing.
- **Realistic AWS Integration**: Demonstrates how `AmazonEC2` and `RunInstancesRequest` are typically used in production.

---

## Prerequisites

- **Java 8 or higher**
- **AWS SDK for Java** (`com.amazonaws:aws-java-sdk-ec2`)
- **Mockito** (`org.mockito:mockito-core`)

---

## How to Run

1. Clone this repository:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the main class:
   ```bash
   java -cp target/your-jar-file.jar org.example.Main
   ```

---

## Project Structure

```
src/main/java
├── org.example
│   ├── Main.java  // Entry point demonstrating EC2 instance mocking
```

### Main.java

1. **Create Mocked AmazonEC2 Client**: 
   - The `AmazonEC2` client is mocked using Mockito.

2. **Simulate Instance Creation**:
   - `runInstances()` is stubbed to return a mock `RunInstancesResult`.

3. **Delay Handling**:
   - A delay is introduced to simulate instance creation time.

4. **Test Workflow**:
   - The `mockCreateEC2Instance()` method demonstrates calling the mocked EC2 client and handling its output.

---

## Key Code Snippets

### Mocking EC2 Client

```java
AmazonEC2 ec2Client = Mockito.mock(AmazonEC2.class);
RunInstancesResult mockResult = new RunInstancesResult();
when(ec2Client.runInstances(any(RunInstancesRequest.class))).thenReturn(mockResult);
```

This mocks the `AmazonEC2` client and its `runInstances()` method.

### Simulated Instance Creation

```java
public static void mockCreateEC2Instance(AmazonEC2 ec2Client) throws InterruptedException {
    System.out.println("Creating EC2 instance... Please wait.");
    Thread.sleep(5000);  // Mock delay
    ec2Client.runInstances(new RunInstancesRequest());
    System.out.println("EC2 instance creation complete.");
}
```

Simulates a 5-second delay to mimic the real behavior of launching an EC2 instance.

---

## Real-Life AWS Code Example

To deploy real EC2 instances, you would use the AWS SDK for Java with valid credentials. Here's a simplified version:

```java
AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard()
    .withRegion("us-east-1")
    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("accessKey", "secretKey")))
    .build();

RunInstancesRequest request = new RunInstancesRequest()
    .withImageId("ami-12345678")
    .withInstanceType("t2.micro")
    .withMinCount(1)
    .withMaxCount(1);

RunInstancesResult result = ec2Client.runInstances(request);
System.out.println("Instance launched: " + result.getReservation().getInstances().get(0).getInstanceId());
```

---

## Use Cases

- **Unit Testing**:
  - Test your application’s logic that interacts with AWS EC2 without incurring costs.
  
- **Simulating Delays**:
  - Mimic real-world AWS response times during development.

- **Education**:
  - Learn how the AWS SDK for Java integrates with EC2 services.

---

## Dependencies

Add the following dependencies to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>com.amazonaws</groupId>
        <artifactId>aws-java-sdk-ec2</artifactId>
        <version>1.12.526</version>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.5.0</version>
    </dependency>
</dependencies>
```
