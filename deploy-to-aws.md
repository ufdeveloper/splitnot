### Deploying splitnot-api to AWS

For the prototype, the splitnot-api JAR is manually deployed to EC2.  

#### Create an EC2 instance

- Create a free-tier eligible Linux instance.
- Save the pem key obtained while creating the instance.
- Create the following inbound rules on the security group assigned to the instance, (Note - this is an incomplete list, needs to be updated)
```
   Type         Protocol        Port range      Source
   Custom TCP	TCP	        8080	        100.35.182.33/32 <== your IP. This rule is to allow web traffic
   All traffic	All	        All             sg-bb381b94 (default)
   SSH	        TCP	        22	        100.35.182.33/32 <== your IP. This rule is to allow SSH
``` 
- Follow this guide for ssh-ing into the instance - https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/AccessingInstancesLinux.html
- Make your pem key file read only by running `chmod 400 my-key-pair.pem`
- Finally, SSH into your instance using `ssh -i my-key-pair.pem ec2-user@<EC2-instance-public-DNS>`

#### Bootstrap the instance with jdk8

`sudo yum install java-1.8.0`

#### Package splitnot-api JAR locally

Package your jar on your local machine using, `mvn package`. The jar file will be generated in the `target` folder.

#### Copy jar to EC2 instance

`scp -i my-key-pair.pem splitnot-api-0.1.0.jar ec2-user@<EC2-instance-public-DNS>:~`

#### Setup application and access logging

We will setup logging under the /var/log/tomcat directory. Application logs will be written to /var/log/tomcat/tomcat.log and access logs will be written to /var/log/tomcat/access.log
Spring boot only logs to the console by default, so we need to update the application.yml file to be able to write to files. Check out the application.yml file in the src directory.

Create the tomcat directory under /var/log.  
`cd /var/log`
`sudo mkdir tomcat`

Change permissions to write to the directory.  
`sudo chmod -R a+rw tomcat`

That's it, the application will now be able to write to the /var/log/tomcat directory.

#### Set Plaid clientId and clientSecret

`export plaidClientId=<clientId>`  
`export plaidClientSecret=<clientSecret>`  

#### Set AWS awsAccesskey and awsSecretkey

This is needed by the service to connect to dynamoDB.  

`export awsAccesskey=<accesskey>`  
`export awsSecretkey=<awsSecretkey>`  

#### Run the jar on EC2 instance

`nohup java -jar ~/splitnot-api-0.1.0.jar com.megshan.splitnot.SplitnotApplication &` . Note that the SplitnotApplication name must include the full path name of the class.

The `&` at the end starts the process in the background. The `nohup` command keeps the process running even if the terminal is closed.
 
#### Test the API endpoints

From your local machine, you should now be able to access the API.

`curl http://<EC2-instance-public-DNS>:8080/accounts` 
