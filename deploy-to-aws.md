### Deploying splitnot-api to AWS

For the prototype, the splitnot-api JAR is manually deployed to EC2.  

#### Create an EC2 instance

- Create a free-tier eligible Linux instance.
- Save the pem key obtained while creating the instance.
- Create the following inbound rules on the security group assigned to the instance,
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

#### Set Plaid clientId and clientSecret

`export plaidClientId=<clientId>`
`export plaidClientSecret=<clientSecret>`

#### Run the jar on EC2 instance

`java -jar splitnot-api-0.1.0.jar com.megshan.splitnot.SplitnotApplication` . Note that the SplitnotApplication name must include the full path name of the class.
 
#### Test the API endpoints

From your local machine, you should now be able to access the API.

`curl http://<EC2-instance-public-DNS>:8080/accounts` 