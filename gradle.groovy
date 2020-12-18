def call(){
  
	stage('Build & test') {
		STAGE_NAME = 'Build & test'
		sh "gradle clean build"
	}
	stage('Sonar') {
		STAGE_NAME = 'sonar'
		//Generar instancia de tipo tool del scanner
		//Va el nombre de la instancia en Jenkins>Global tool config.
		def scannerHome = tool 'sonar-scanner';
		//Corresponde a lo  configurado en Jenkins>Configurar el sistema
		withSonarQubeEnv('sonar') {
			bat "${scannerHome}\\bin\\sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
		}
	}
	stage('Run') {
		STAGE_NAME = 'Run'
		sh "nohup gradle bootRun &"
		sleep 30
	}
	stage('Test') {
		STAGE_NAME = 'Test'
		sh "curl -XGET 'localhost:8082/rest/mscovid/test?msg=testing'"
	}
	stage('Nexus') {
		STAGE_NAME = 'Nexus'
		nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'D:\\Estudios\\ejemplo-gradle\\build\\libs\\DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.3']]]
	}
}

return this;
