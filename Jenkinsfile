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
			//slackSend color: 'good', message: "[Tamara Nahuelpán][pipeline-maven-gradle][${params.devtool}] Ejecución exitosa."
			slackSend channel: "#lab-pipeline-status-grupo1", color: 'good', message: "[Grupo 1][Resultado: OK] Prueba :D"           
		}
		failure {
			//slackSend color: 'danger', message: "[Tamara Nahuelpán][pipeline-maven-gradle][${params.devtool}] Ejecución fallida en stage ${STAGE_NAME}."
			slackSend channel: "#lab-pipeline-status-grupo1", color: 'danger', message: "[Grupo 1][Resultado: No OK] Prueba :D"
		}
	}
}
