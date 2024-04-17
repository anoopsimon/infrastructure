pipeline {
    agent any

    environment {
        EMAIL_RECIPIENT = "s451421990@gmail.com"
    }

    stages {
        stage('Update AMI IDs') {
            steps {
                script {
                    // Define cloud names and corresponding AMI URLs
                    def cloudMap = [
                            "ec2-pool": "https://gist.githubusercontent.com/anoopsimon/dde8569889ab5db3722b4364ed4eb257/raw/268a7816ffd0031c680408f42fd2a82acd90009a/rhel-latest",
                           // "ec2-pool-node": "https://gist.githubusercontent.com/anoopsimon/dde8569889ab5db3722b4364ed4eb257/raw/268a7816ffd0031c680408f42fd2a82acd90009a/rhel-latest"
                    ]
                    def results = []

                    // Get Jenkins instance
                    def jenkins = jenkins.model.Jenkins.instance

                    cloudMap.each { cloudName, url ->
                        // Use curl to fetch the AMI ID
                        String newAmiId = sh(script: "curl -s ${url}", returnStdout: true).trim()

                        // Find the specific EC2 cloud by name
                        def ec2Cloud = jenkins.clouds.find {
                            it instanceof hudson.plugins.ec2.AmazonEC2Cloud && it.name == cloudName
                        }

                        if (ec2Cloud && newAmiId ==~ /ami-[0-9a-fA-F]+/) {
                            // Update AMI ID for the specified cloud
                            ec2Cloud.getTemplates().each { template ->
                                template.ami = newAmiId
                            }

                            // Save the updated configuration
                            jenkins.save()
                            results << "| ${cloudName} | ${new Date().format('yyyy-MM-dd')} | ${newAmiId} |"
                        } else {
                            results << "| ${cloudName} | ${new Date().format('yyyy-MM-dd')} | Error: Cloud not found or invalid AMI ID |"
                        }
                    }

                    // Print results
                    echo "Update Results:"
                    echo "| Cloud Name | Updated Date | Updated AMI ID |"
                    results.each { echo it }

                    // Send email with the results
                    emailext (
                            subject: "AMI Update Report",
                            body: """<p>AMI Update Results:</p>
                                <table>
                                <tr><th>Cloud Name</th><th>Updated Date</th><th>Updated AMI ID</th></tr>
                                ${results.join("\n").replaceAll("\\|", "<td>").replaceAll(" ", "</td><td>")}
                                </table>""",
                            to: EMAIL_RECIPIENT
                    )
                }
            }
        }
    }
}
