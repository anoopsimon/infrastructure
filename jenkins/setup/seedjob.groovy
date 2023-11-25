import jenkins.model.Jenkins
import hudson.FilePath
import java.nio.file.Files
import java.nio.file.Paths

// Path to the directory containing Groovy scripts for job definitions
//String scriptsDir = Jenkins.instance.getRootDir().getAbsolutePath() + '/jobs'
String scriptsDir ='/usr/share/jenkins/ref/jobs'

// Create a File object pointing to the scripts directory
File dir = new File(scriptsDir)

// Check if the directory exists
if (!dir.exists() || !dir.isDirectory()) {
    println("The directory ${scriptsDir} does not exist or is not a directory.")
    return
}

// List all Groovy files in the directory
dir.eachFileMatch(~/.*\.groovy/) { file ->
    println "Processing script: ${file.name}"
    createJobFromScript(file)
}

// Method to create a job from a Groovy script file
def createJobFromScript(File scriptFile) {
    String scriptContent = scriptFile.text
    String jobName = scriptFile.name - '.groovy'

    pipelineJob(jobName) {
        definition {
            cps {
                sandbox(true)
                script(scriptContent)
            }
        }
    }
}
