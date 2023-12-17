pipeline {
    agent any

    parameters {
        string(name: 'CLOUD_NAME', defaultValue: 'ec2-pool', description: 'Name of the EC2 cloud to update')
        string(name: 'NEW_AMI_ID', defaultValue: 'ami-123456', description: 'New AMI ID to set')
    }

    stages {
        stage('Update EC2 AMI ID') {
            steps {
                script {
                    String cloudName = params.CLOUD_NAME
                    String newAmiId = params.NEW_AMI_ID

                    def jenkins = jenkins.model.Jenkins.instance
                    def ec2Cloud = jenkins.clouds.find { cloud -> 
                        cloud instanceof hudson.plugins.ec2.AmazonEC2Cloud && cloud.name == cloudName
                    }

                    if (ec2Cloud) {
                        ec2Cloud.getTemplates().each { template ->
                            template.ami = newAmiId
                        }

                        jenkins.save()
                        echo "AMI ID updated to ${newAmiId} for EC2 cloud: ${cloudName}"
                    } else {
                        echo "EC2 cloud named '${cloudName}' not found!"
                    }
                }
            }
        }
    }
}
