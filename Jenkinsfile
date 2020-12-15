pipeline {
    agent any

    stages {
        stage('Pipeline') {
            steps {
                script {
					stage('Build & test') {
						sh "gradle clean build"
					}
					stage('Sonar') {
						//Generar instancia de tipo tool del scanner
						//Va el nombre de la instancia en Jenkins>Global tool config.
						def scannerHome = tool 'sonar-scanner';
						//Corresponde a lo  configurado en Jenkins>Configurar el sistema
						withSonarQubeEnv('sonar') {
							sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
						}
					  }
					}
					stage('Run') {
						//
					}
					stage('Test') {
						//
					}
					stage('Nexus') {
						//
					}
				}
            }
        }
    }
}