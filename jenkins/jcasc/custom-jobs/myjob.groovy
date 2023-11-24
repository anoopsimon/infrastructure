// this is an example declarative pipeline that says hello and goodbye
pipeline {
    agent any
    stages {
        stage("Hello") {
            steps {
                echo "Hello from pipeline"
            }
        }
        stage("Goodbye") {
            steps {
                echo "Goodbye from pipeline"
            }
        }
    }
}