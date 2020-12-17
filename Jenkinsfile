pipeline {
    agent any
	
	parameters { choice(name: 'devtool', choices: ['maven', 'gradle'], description: '') }

    stages {
        stage('Pipeline') {
            steps {
                script {
			params.devtool
				
			def ejecucion = (params.devtool == 'gradle') ? load("gradle.groovy") : load("maven.groovy")
			ejecucion.call()
		}
            }
        }
    }
}
