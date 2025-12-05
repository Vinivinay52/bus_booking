pipeline {
    agent {
        label 'slave20'
    }

    environment {
        TOMCAT_HOST = '172.31.1.41'
        TOMCAT_USER = 'root'
        TOMCAT_DIR = '/opt/tomcat10/webapps'
        JAR_FILE = 'bus-booking-app-1.0-SNAPSHOT.war'  // Replace with the actual name of your JAR file
    }

    stages {
        stage('checkout') {
            steps {
                sh 'rm -rf bus_booking'
                sh 'git clone https://github.com/Vinivinay52/bus_booking.git'
            }
        }

        stage('build') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    def mvnCMD = "${mvnHome}/bin/mvn"
                    sh "${mvnCMD} clean install"
                }
            }
        }

        stage('Show Contents of target') {
            steps {
                script {
                    // Print the contents of the target directory
                    sh 'ls -l target'
                }
            }
        }

stage('Push the artifacts into JFrog Artifactory') {
            steps {
                script {
                    // Define WAR file path
                    def WAR_FILE = "${env.WORKSPACE}/target/news-app.war"

                    // Current timestamp
                    def currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date())

                    // Path inside Artifactory
                    def targetPath = "news_app1/${currentDate}/"

                    rtUpload(
                        serverId: "jfrog",
                        spec: """{
                            "files": [
                                {
                                    "pattern": "${WAR_FILE}",
                                    "target": "${targetPath}"
                                }
                            ]
                        }"""
                    )
                }
            }
        }

        stage('Run JAR Locally') {
            steps {
                script {
                    // Run the JAR file using java -jar
                    sh "java -jar target/${JAR_FILE}"
                }
            }
        }

        stage('Deploy JAR to Tomcat') {
            steps {
                script {
                    // Copy JAR to Tomcat server
                    sh "scp target/${JAR_FILE} ${TOMCAT_USER}@${TOMCAT_HOST}:${TOMCAT_DIR}/"

                    // SSH into Tomcat server and restart Tomcat
                    sh "ssh ${TOMCAT_USER}@${TOMCAT_HOST} 'bash -s' < restart-tomcat.sh"

                    echo "Application deployed and Tomcat restarted"
                }
            }
        }
    }

    post {
        success {
            echo "Build, Run, and Deployment to Tomcat successful!"
        }
        failure {
            echo "Build, Run, and Deployment to Tomcat failed!"
        }
    }
}
