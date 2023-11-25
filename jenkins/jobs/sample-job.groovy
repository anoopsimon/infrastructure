//Sample job 
pipeline {
    agent any
    stages {
        stage("Build") {
            steps {
                echo "Building Solution - Demo"
            }
        }
        stage("Test") {
            steps {
                echo "Testing Solution - Demo"
            }
        }
    }
}