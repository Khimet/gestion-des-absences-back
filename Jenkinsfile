pipeline {
	agent any

	tools {
		maven "Maven"
	}

	stages {
		stage('cleanpackage'){
			steps {
				sh 'mvn clean package'
			}
		}
	}
	post {

		failure {
			discordSend description: "${BUILD_URL}", footer: 'Echec - Robin', image: '', link: '', result: 'FAILURE', thumbnail: '', title: "${env.JOB_NAME}-${BUILD_NUMBER}", webhookURL: 'https://discordapp.com/api/webhooks/748830347399266344/bwY-_bEjcLBJGm-gX_IuBib5eTV8g1bwyHX3vo5MS3Sdyhw9Bd5lr-JEgn4bF8k-n1ZS'
		}
		
		success {
			script {
				if ("${env.BRANCH_NAME}" == 'master')
					discordSend description: "${BUILD_URL}", footer: 'Success - Robin', image: '', link: '', result: 'SUCCESS', thumbnail: '', title: "${env.JOB_NAME}-${BUILD_NUMBER}", webhookURL: 'https://discordapp.com/api/webhooks/748830347399266344/bwY-_bEjcLBJGm-gX_IuBib5eTV8g1bwyHX3vo5MS3Sdyhw9Bd5lr-JEgn4bF8k-n1ZS'
			}
		}
	}
}