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
        def repos
        stage('Checkout') {
            echo 'Iniciado Clone do Repositório'
            repos = git credentialsId: 'jenkins', url: 'https://github.com/fcampara/social-network-docker.git'
            echo repos.toString()
            sh 'ls -ltra'
        }
        stage('Package') {
            container('docker-container') {
                echo 'Iniciado o empacotamento com Docker'
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USER')]) {
                    sh "docker login -u ${DOCKER_HUB_USER} -p ${DOCKER_HUB_PASSWORD}"
                }
                sh 'docker images'
                sh 'docker build -t fcamparasilva/frontend:alpha . --build-arg NPM_ENV="staging"'
                sh 'docker push fcamparasilva/frontend:alpha'
            }
        }
        stage('Deploy') {
            echo 'Iniciado deploy com Helm'
        }
    }
}