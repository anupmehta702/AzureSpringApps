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

Refer --> https://www.pluralsight.com/cloud-guru/labs/azure/building-apps-using-self-hosted-build-agents-in-azure-pipelines <br /> 

## References 
https://learn.microsoft.com/en-us/azure/spring-apps/how-to-cicd?pivots=programming-language-java <br />
https://learn.microsoft.com/en-us/azure/devops/pipelines/tasks/reference/azure-spring-cloud-v0?view=azure-pipelines --> for springApps devops task <br />
