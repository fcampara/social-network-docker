// This step should not normally be used in your script. Consult the inline help for details.
podTemplate(
    containers: [
        containerTemplate(
            args: 'cat',
            command: '/bin/sh -c',
            image: 'docker',
            livenessProbe: containerLivenessProbe(execArgs: '',
            failureThreshold: 0,
            initialDelaySeconds: 0,
            periodSeconds: 0,
            successThreshold: 0,
            timeoutSeconds: 0
        ),
        name: 'docker-container',
        resourceLimitCpu: '',
        resourceLimitMemory: '',
        resourceRequestCpu: '',
        resourceRequestMemory: '',
        ttyEnabled: true
        )
    ],
    label: 'questcode',
    name: 'devops',
    namespace: 'devops',
    volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')
    ],
) {
    node ('questcode') {
        stage('Checkout') {
            echo 'Iniciado Pipeline'
        }
        stage('Build') {
            container('docker-container') {
                sh 'docker images'
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'jenkins', url: 'https://github.com/fcampara/social-network-docker.git']]])
            }
            echo 'Build'
        }
        stage('Deploy') {
            echo 'Iniciado deploy com Helm'
        }
    }
}