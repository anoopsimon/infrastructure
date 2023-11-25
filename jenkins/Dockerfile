FROM jenkins/jenkins

ENV JENKINS_REF /usr/share/jenkins/ref
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yaml

COPY jobs $JENKINS_REF/jobs/
COPY casc.yaml /var/jenkins_home/casc.yaml
COPY setup/seedjob.groovy $JENKINS_REF/seedjob.groovy
COPY setup/plugins.txt $JENKINS_REF/plugins.txt


RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt
