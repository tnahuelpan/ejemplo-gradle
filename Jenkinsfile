pipeline {
    agent any
	
	parameters { choice(name: 'devtool', choices: ['maven', 'gradle'], description: '') }

    stages {
        stage('Pipeline') {
            steps {
                script {
			def STAGE_NAME = ''
			params.devtool
		
			def ejecucion = (params.devtool == 'gradle') ? load("gradle.groovy") : load("maven.groovy")
			ejecucion.call()
		}
            }
        }
    }
	
	post {
		success {
			slackSend color: 'good', message: "[Tamara Nahuelpán][pipeline-maven-gradle][${params.devtool}] Ejecución exitosa."
		}
		failure {
			slackSend color: 'danger', message: "[Tamara Nahuelpán][pipeline-maven-gradle][${params.devtool}] Ejecución fallida en stage ${STAGE_NAME}."
		}
	}
}
