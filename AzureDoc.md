## TODO
1) Deploy spring apps application <br />
2) How do we pass env variables while deploying spring apps <br />
3) Deploy on staging and production env (Blue green deployment)<br /> 
4) Deploy using devops pipeline <br />
5) Deploy multi-instances to check if internal load balancer works fine <br />
6) How to check logs <br />
7) How do we auto-scale applications <br />
8) How do we connect to Azure SQL <br />
9) How do we use Azure Vault <br />
10) How do we use Azure redis <br />



## Azure automated deployment via Azure Devops

I couldnt run the pipeline from azure.devops site directly as it requires permission to run,
so I had to download Azure pipeline agent and configure it on personal laptop. <br /> 
Download agent software and unzip and run config.cmd from C:/agents folder. <br />
Refer link -- https://learn.microsoft.com/en-us/azure/devops/pipelines/agents/windows-agent?view=azure-devops <br />
<br />
To ensure your pipeline runs via Windows agent on local machine and not on azure.devops site,
you need to make below change in pipeline's (azure-pipelines)yaml file - <br />

pool: <br />
 name: Default <br />

Refer ( How to run azure pipeline from local machine ) --> https://www.pluralsight.com/cloud-guru/labs/azure/building-apps-using-self-hosted-build-agents-in-azure-pipelines <br /> 


## Azure key vault 
az login <br />
az keyvault create  --name java-keyvault-demo-kv -g FreeTrialSubscriptionResourceGroup <br />
az keyvault secret set  --vault-name java-keyvault-demo-kv --name "keyName" --value "secretValue" <br />

Now, create **service principal**  <br />
A **security principal** is an object that represents a user, group, service, or application that's requesting access to Azure resources. Azure assigns a unique object ID to every security principal.
<br />
A **service principal** is a type of security principal that identifies an application or service, which is to say, a piece of code rather than a user or group. A service principal's object ID acts like its username; the service principal's client secret acts like its password. <br />
<br /> 
For creating service principal you need , <br />
**role**, use contributor as role --> https://learn.microsoft.com/en-us/azure/role-based-access-control/built-in-roles <br />
**scope**, go to azure vault , properties , copy resourceId and set it as scope --> https://learn.microsoft.com/en-us/azure/role-based-access-control/scope-overview <br />

az ad sp create-for-rbac -n java-keyvault-demo-sp --role=Contributor --scopes=/subscriptions/60407016-7bab-42f7-83c7-96d608df8a14/resourceGroups/FreeTrialSubscriptionResourceGroup/providers/Microsoft.KeyVault/vaults/java-keyvault-demo-am 
<br />
output --> <br />
{ <br />                                                          
  "appId": "73459b1c-c6dd-4e79-b48b-9d9c6fc11f12", <br />          
  "displayName": "java-keyvault-demo-sp", <br />                  
  "password": "52h8Q~cjNVtgjiO4Vw5FOO4EKrjuI7JNUWdT0cXo", <br />   
  "tenant": "49a278ba-a806-415d-b311-0f0cc30b11de" <br />         
}<br />                                                           

Add below env variables -->
spring.profiles.active=AzureDB <br />
AZURE_CLIENT_ID=73459b1c-c6dd-4e79-b48b-9d9c6fc11f12 <br />
AZURE_CLIENT_SECRET=52h8Q~cjNVtgjiO4Vw5FOO4EKrjuI7JNUWdT0cXo <br />
AZURE_TENANT_ID=49a278ba-a806-415d-b311-0f0cc30b11de<br />
KEY_VAULT_NAME=java-keyvault-demo-am <br />

Need to provide service principal access to get the secret   <br />
Get the object-id of the service principal (java-keyvault-demo-sp) from UI --> Enterprise application --> java-keyvault-demo-sp --> properties <br />
  
az keyvault set-policy --name java-keyvault-demo-am --object-id d67462f0-d1ea-475a-a14e-4320cc3b43b4 --secret-permissions get


Add certificate of https://java-keyvault-demo-am.vault.azure.net/ in your java truststore using below command <br />
keytool -importcert -trustcacerts -alias AzureKeyVault -file "C:\AzureCert\vault.azure.net.crt" -keystore "C:\Program Files\Java\jdk1.8.0_73\jre\lib\security\cacerts"

## Azure SQL DB
how to connect to Azure SQL <br />
open azure cloudshell and enter below command --> <br />
mysql -h studentmysqldb.mysql.database.azure.com -u mydemouser -p <br />
connect studentdb <br />
select * from student ; <br />


## References 
CICD --> https://learn.microsoft.com/en-us/azure/spring-apps/how-to-cicd?pivots=programming-language-java <br />

Azure devops --> https://learn.microsoft.com/en-us/azure/devops/pipelines/tasks/reference/azure-spring-cloud-v0?view=azure-pipelines <br />

Azure autoscaling --> https://learn.microsoft.com/en-us/azure/spring-apps/how-to-setup-autoscale#navigate-to-the-autoscale-page-in-the-azure-portal <br />

Azure baseline architecture --> https://learn.microsoft.com/en-us/azure/architecture/web-apps/spring-apps/architectures/spring-apps-multi-region <br />

Azure key vault --> https://azure.github.io/cloud-scale-data-for-devs-guide/get-started-with-java-and-key-vault.html <br />

How to assign Azure spring apps access to vault --> https://learn.microsoft.com/en-us/azure/spring-apps/tutorial-managed-identities-key-vault?tabs=user-assigned-managed-identity <br />

