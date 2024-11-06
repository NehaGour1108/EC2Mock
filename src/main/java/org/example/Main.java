package org.example;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Create a mock EC2 client
        AmazonEC2 ec2Client;
        ec2Client = Mockito.mock(AmazonEC2.class);

        // Create a mock result
        RunInstancesResult mockResult = new RunInstancesResult();

        // Simulate EC2 instance creation
        when(ec2Client.runInstances(any(RunInstancesRequest.class))).thenReturn(mockResult);

        // Simulating EC2 instance creation with a delay
        try {
            System.out.println("Starting EC2 instance creation...");
            mockCreateEC2Instance(ec2Client);
            System.out.println("EC2 instance created successfully.");
        } catch (InterruptedException e) {
            System.err.println("EC2 creation was interrupted.");
            Thread.currentThread().interrupt(); // Restore interrupt status
        }
    }

    public static void mockCreateEC2Instance(AmazonEC2 ec2Client) throws InterruptedException {
        // Simulate EC2 instance creation delay
        System.out.println("Creating EC2 instance... Please wait.");
        Thread.sleep(5000);  // Mock delay (5 seconds)

        // Calling the mocked EC2 client (it won’t actually hit AWS)
        RunInstancesRequest request = new RunInstancesRequest();
        ec2Client.runInstances(request);

        System.out.println("EC2 instance creation complete.");
    }
}
