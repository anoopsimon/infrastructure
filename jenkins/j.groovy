pipeline {
    agent any

    parameters {
        string(name: 'CLOUD_NAME', defaultValue: 'ec2-pool', description: 'Name of the EC2 cloud to update')
    }

    stages {
        stage('Fetch AMI ID') {
            steps {
                script {
                    // URL to fetch the AMI ID
                    String url = 'https://gist.githubusercontent.com/anoopsimon/dde8569889ab5db3722b4364ed4eb257/raw/268a7816ffd0031c680408f42fd2a82acd90009a/rhel-latest'

                    // Use curl to fetch the AMI ID
                    String newAmiId = sh(script: "curl -s ${url}", returnStdout: true).trim()

                    // Check if the fetched AMI ID is valid
                    if (newAmiId ==~ /ami-[0-9a-fA-F]+/) {
                        echo "Fetched AMI ID: ${newAmiId}"
                    } else {
                        error "Fetched invalid AMI ID: '${newAmiId}'"
                    }

                    // Get Jenkins instance
                    def jenkins = jenkins.model.Jenkins.instance

                    // Parameters
                    String cloudName = params.CLOUD_NAME

                    // Find the specific EC2 cloud by name
                    def ec2Cloud = jenkins.clouds.find { cloud ->
                        cloud instanceof hudson.plugins.ec2.AmazonEC2Cloud && cloud.name == cloudName
                    }

                    if (ec2Cloud) {
                        // Update AMI ID for the specified cloud
                        ec2Cloud.getTemplates().each { template ->
                            template.ami = newAmiId
                        }

                        // Save the updated configuration
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
