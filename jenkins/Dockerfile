FROM jenkins/jenkins

ENV JENKINS_REF /usr/share/jenkins/ref
ENV JENKINS_HOME /var/jenkins_home/
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false
ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc.yml

COPY jobs $JENKINS_REF/jobs/
COPY casc.yml $JENKINS_HOME/casc.yml
COPY setup/seedjob.groovy $JENKINS_REF/seedjob.groovy
COPY setup/plugins.txt $JENKINS_REF/plugins.txt


RUN jenkins-plugin-cli --plugin-file $JENKINS_REF/plugins.txt
