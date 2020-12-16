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
							bat "${scannerHome}\\bin\\sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
						}
					}
					stage('Run') {
						sh "nohup gradle bootRun &"
						sleep 30
					}
					stage('Test') {
						sh "curl -XGET 'localhost:8082/rest/mscovid/test?msg=testing'"
					}
					stage('Nexus') {
						nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'feature-nexus-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'D:\\Estudios\\ejemplo-maven\\build\\libs\\DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
					}
				}
            }
        }
    }
}