### Required
[Node.js and NPM](https://nodejs.org/en/download/)

[Docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/#install-using-the-repository)

È aconselhável utilizar Linux, pois no windows Docker irá instalar um SO linux em seus sistema para poder executar

# Introdução

  Como as aplicações era1m construidas nos últimos 20 anos? Boa parte da evolução se da conta pela popularização de alguns ecossistemas (Internet, Mobile e IoT).

![alt Gráfico evolução da internet](https://camo.githubusercontent.com/de7560299c05e0c395c63d2b2010a17738ec8da4/687474703a2f2f6d697373616f6465766f70732e636f6d2e62722f696d672f77617272696f722f63617030332d65636f6d6d657263652d67726f7774682d323031382e706e67)
> Crescimento da utilização WEB

Com essa grande evolução começou ocorrer uma demanda maior dos servidores, logo precisando de servidores mais potentes e para isso tendo que investir valores grandes em seus equipamentos para poder manter um serviço de qualidade online, logo gastando valores exorbitantes.

# Monolitos

  Arquitetura de desenvolvimento bastante utilizada durante os últimos 20 anos, para uma aplicação monolito a aplicação sempre será empacotada junta (Frontend e backend), toda essa aplicação é entregue dentro de um application server (Servidor) ele que irá cuidar para fazer o Host da aplicação. Existe recursos que são muito utilizados nesse modelo de arquitetura que são as [session](###-session) e [application](###-applcation)

### Session

  Muito utilizada para armazenas a sessão do usuário (escopo do usuário), ficará sempre na memória do servidor (enquanto estiver em execução) isso é uma caracteristica forte de [stateful](###-stateful)

### Application

  É uma informação que fica definida por todo application server, que será acessado pelos usuários que estão na session. Isso facilita toda a logística.

### Stateful

 Tudo que é persistido durante a execução do application server

### Vantagens

- Pool Conexão
- Cache
- Filas

### Desvantagem

- Você se torna amarrado
- Grandes dificuldades para subir um novo serviço
- Escalabilidade custosa e gera dificuldades técnicas para compartilhar seus usuários e suas sessions, pois no load balancer os usuários podem ser enviados para qualquer aplicação, logo deve se manter as informações em ambos servidores
- Gera custos adicionais para cuidar do auto-scale, pois deve ser manter máquinas sempre em funcionamento para os momentos de picos

# Microserviços

  Nessa tipo de arquitetura a aplicação fica dividida em partes (Frontend e Backend), um conceito básico do microserviço  que ele seja [statless](###-statless), assim podendo subir partes diferentes do serviço e usuário pode acessar qualquer um dos serviços sem problema nenhum e não tem réplicas de dados desnecessários, logo gerando agilidade para o desenvolvimento, e sendo capaz de corrigir partes de um módulo sem afetar nenhuma outra parte do serviço.

## Statless

  Não armazena nenhum tipo de configuração do usuário, facilitando na criação de replicas dos serviços.

## Vantagens

- Facilidade no auto-scale

### Desvantagens

 - Custo de desenvolvimento grande

# Container

  Um container possui todas as configuração para o nosso serviço, tanto quanto frotnend e backend, basicamente é uma imagem (é a base para gerar 1 ou N containers, é construida atráves de um arquivo chamado [Dockerfile](https://docs.docker.com/engine/reference/builder/)) com todas as configurações necessárias, mas subir essa imagem e utilizar em seu servidor. Um comparativo de container é as máquinas virtuais, podendo criar várias snapshots com as configurações do nosso serviço, mas o problema disso que acaba se tornando custoso pela questão que a cada snapshot devemos instalar um SO por completo e não iremos utilizar todos os recursos dele. Diferente do Container que ele é isolado mas compartilha o mesmo SO, logo poupando recurso computacional.

  ![alt Comparação entre Hypervirsos e Container](http://imesh.github.io/images/contvsvm.png)
  > Hypervisor X Container

# Docker

  Para entender o ecossistema do Docker, é interessante saber como funciona, ele é dividio em várias camadas, sendo a primeira o Client aonde será executado os comando, Host aonde fica armazenada as imagens e o containers e o Registry sendo aonde busca as imagens do Docker. Utilizaremos o [Docker Registry](https://hub.docker.com/) pois é o repositório oficial do Docker.

### Instalação

  Para fazer a instalação é simples (totalmente aconselhavel utilizar Linux para utilzação de Docker). Primeiramente devemos inserir o repositório do Docker em nossa máquina

```
  $ sudo apt-get update
  $ sudo apt-get install \ apt-transport-https \ ca-certificates \ curl \ gnupg-agent \ software-properties-common
  $ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
  $ sudo add-apt-repository \ "deb [arch=amd64] https://download.docker.com/linux/ubuntu \ $(lsb_release -cs) \ stable"

```

  Com o repositório agora iremos instalar o Docker CE.

  ```
    $ sudo apt-get install docker-ce
  ```

  E por último definir o usuário para o docker

  ```
    $ sudo usermod -aG docker your-user
  ```

# Dockerfile

  Arquivo que será inserido os comandos para pode executar o docker, cada linha que for inserida será gerada um layer para ele, isso signifca que será criado 'pausas', caso ocorrá algum erro ou atualziar um parter do arquivo docker saberá da onde começar a partir de sua layer, então é aconselhável sempre pensar na ordem de criação deixar sempre ações que irão gerar mais tempo de processamento por último

# Docker Registry

  Docker Registry ou Docker Hub nada mais que um sistema de SCM, aonde possibilita guarda as imagens de desenvolvimento, semelhante ao github, primeiro a imagem deve estar gerada

# Docker Composer

  Ajuda a compor aplicações (como o própio nome diz), de vez subir cada serviço manualmente, o composer facilita ajudando a subir todos os serviços juntos com apenas um comando, para instalar basta olha a [documentação](https://docs.docker.com/compose/install/)

![alt Ecossistema Docekr](https://docs.docker.com/engine/images/architecture.svg)

# Cloud Native

  Uma aplicação Cloud Native é uma aplicação orientada a micro-serviços, possui uma camada REST, tem empacotamento em container e pode ser dinâmicamente gerenciada por um orquestrador.

# Kubernetes

  [Kubernetes](https://kubernetes.io/) é uma ferramenta OpenSource que faz gestão de aplicações em container atráves de recuros como deployments, updates, scaling, e lifecycles, é mantido pela [Cloud Native Compunting Foundation](https://www.cncf.io/). Algo muito interessante do Kubernetes que atualmente [Azure AKS](https://azure.microsoft.com/pt-br/services/kubernetes-service/), [Digital Ocean](https://www.digitalocean.com/products/kubernetes/), [IBM Cloud Kubernetes Services](https://www.ibm.com/br-pt/cloud/container-service) e [AWS EKS](https://aws.amazon.com/pt/eks/) aceitam Kubernetes, logo é uma tecnologia interessante pela sua possibilidade de portabilidade entre provedores de cloud.

### Instalação

  Basta seguir a [documentação oficial](https://kubernetes.io/docs/setup/independent/install-kubeadm/) que está disponivel no site do kubernetes, meu ambiente de desenvovliment está sendo em um Notebook com as seguintes configurações

Para um ambiente mais real, tudo será feito em um VMs (Máquina virtual) que estará hospeda na Google Cloud. Primeiro devemos [alocar nossa máquina](https://console.cloud.google.com/compute/instances), estarei utilizando uma máquina de 8Gb com 2 núcleos de processamentos. Para conseguirmos utilizar tambêm devemos criar regras para o Fire wall, nas configurações devemos liberar as seguintes portas 30000-32767. Feito isso devemos editar nossa Instância inseriondo o nome do nosso firewall configurado.

Como irei executar o Kubernetes em meu Notebook e estou utilizando o Ubuntu necessito desabilitar o SWAP para verificar se está habilitado ou não basta utilizar o htop, caso não tenho basta instalar.

```
  $ sudo apt install htop
  $ htop
```

Para desativar o Swap basta utilizar o seguinte comando.

```
  $ sudo swapoff -a
```

Após podemos iniciar iniciar a instalação do Kubernetes. Iremos instalar 3 ferramentas Kubelet, kubeadm e kubectl
  - Kubectl: Irá ficar assistindo os Nodes (as máquinas), é um engine interna,
  - Kubeadm: Irá instalar o kubernetes (toolbox), ajuda na instalação
  - Kubectl: É um empactador, ele é nosso cliente.

 ```
   $ sudo su
   $ apt-get update && apt-get install -y apt-transport-https curl
   $ curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
   $ cat <<EOF >/etc/apt/sources.list.d/kubernetes.list
    deb https://apt.kubernetes.io/ kubernetes-xenial main
    EOF
   $ apt-get update
   $ apt-get install -y kubelet kubeadm kubectl
   $ apt-mark hold kubelet kubeadm kubectl
   $ mkdir -p $HOME/.kube
 ```

 Com esses comandos nos instalamos o cliente, demos permissões para o kubectl e já podemos visualizar o kubectl, agora podemos iniciar um cluster com o seguinte comando

 ```
  $ sudo kubeadm init --pod-network-cidr=10.244.0.0/16
 ```

Após sua criação é necessário criar a pasta do Kubernetes em sua home e copiar as configurações do mesmo.

```
  $ mkdir -p $HOME/.kube
  $ sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  $ sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

Feito isso Kube está configurado para ser acessado,caso seja necessário podemos verificar a chave do mesmo no arquivo `.kube/config`

### PODS
É a menor unidade dentro do Kubernetes, uma definicação rápida e simples de Pods é um grupo de um ou mais contâiners que compartilha a rede e o armazenamento. Acontece de ter mais containers caso há um banco que deve ser escalado junto com a aplicação

Nesta aplicação será utilizado um POD chamado [Flannel](https://kubernetes.io/docs/setup/independent/create-cluster-kubeadm/#pod-network), para fazer a instalação dele é simples basta seguir os seguintes passos.

Swap deve estar desabilitado
```
  $ kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/v0.10.0/Documentation/kube-flannel.yml
```
Por padrão o kubernetes não roda nada dentro do seu node master, mas podemos conseguir rodar tudo no mesmo node, apenas necessário rodar o seguinte comando

```
 $ kubectl taint nodes --all node-role.kubernetes.io/master-
```

Agora foi finalizado a inicialização e a configuração do Kubernetes, agora temos um node master finalizado e permitindo executar tudo nele mesmo.

Por conhecimento será subido um serviço simples e padrão para testes, pode ser encontrado na documentação do Kubernetes, para fazer isso basta executar o seguinte comando

![Kubernetes Cluster](https://d33wubrfki0l68.cloudfront.net/152c845f25df8e69dd24dd7b0836a289747e258a/4a1d2/docs/tutorials/kubernetes-basics/public/images/module_02_first_app.svg)

```
  $ kubectl run kubernetes-bootcamp --image=gcr.io/google-samples/kubernetes-bootcamp:v1 --port=8080
```

O comando a cima, criou um deployment dentro da master, para poder rodar os containers dentro dele mesmo. Agora para verificar se está disponivel o deployment basta executar

```
 $ kubectl get deployment
```
Ao executar o comando de criação de imagem ocorre os seguintes casos, ele criou no node master um deployment, o deploymnet informa que necessita criar uma instância da imagem informada e gerou um pod, o kubernetes atráves do master se comunica com os outros nodes e pede para ser criado um container. Não conseguimos comunicar diretamente com o serviço para isso poder ocorrer devemos abrir um proxy diretamente esse caso utilizamos mais para o desenvolvimento.

```
  $ kubectl proxy
```

Com o proxy liberado conseguimos acessar diretamente dentro do nodes apenas com um curl e podemos chamar um serviço a partir do proxy

```
 $ curl 127.0.0.1:8001
 $ curl http://localhost:8001/api/v1/namespaces/default/pods/$POD_NAMES/proxy/
```

## Namespaces (NS)

É a segregação lógica dos componentes, iremos aplicar em cluster diferentes componentes em estados diferentes, stagin e o production. O que possibilita ter N ambientes lógicos como Desenvolvimento, Homologação e Produção, são fatias de nosso cluster físico

## Deployment
Criamos um deploy via linha de comando e também via arquivo YAML tambêm sendo possível editar esse arquivo

## Arquivos YAML

É um descritor de Deployment, server para ser gerado as váriavéis de ambientes e configurações especificas para o deployment


## Staging

É a versão que está indo para a produção, um ambiente de homologação

## Production

É a versão estavél do software que está no ar em execução para os usuários

## Kube-System (PADRÃO)

Não é ideal mexer pois aonde está locado todoso os componentes que serão utilizados para o kubernetes para funcionar

Quando executados o comando `$ kubectl run kubernetes-bootcamp --image=gcr.io/google-samples/kubernetes-bootcamp:v1 --port=8080` e `kubectl taint nodes --all node-role.kubernetes.io/master-` criamos um ambiente de stagin que será executado um deployment, sendo que o taint é para pode ser executado dentro do node master. O deployment gera um arquivo YAML, internamente antes de subir um POD é seguindo um passo a passo.

- Será gerado um recurso chamado Replica Set
- Replica Set irá cuidar a quantidade de pods
- Dentro dos Pods será inserido nossos containers
- Dentro dos container estaram os serviços (para acessar precisamos do proxy), nesse momento não existe como ter acesso ao mundo externo.

### Replica Set
É o componente que irá manter os Pods, ele sempre irá monitorar e manter a quantidade de execuções(pods) informada na criação do deployment. Não é inserido nada e não é alterado, só alteramos o Deployment. (Replica Set é um recurso operacional do nosso Deployment)

### [Service](https://kubernetes.io/docs/concepts/services-networking/service/)
Forma de os container dentro dos pods poderem se comunicar com o mundo externo, para fazer isso podemos utilizazr o comando

```
  $ kubectl expose deployment
```

Expose cria um service por padrão o `Cluster IP` e todos os services iram criar um arquivo YAML, será gerado um service do tipo `cluster IP`, ele tem a funcionbilidade de expor os pods dentro do nosso cluster, assim outros services consegue acessar esse cluster (Isso é apenas para expor internamente). Temos o `Load Balancer` ele irá fazer um bind com o Cloud Provider e irá expor o IP externo e por último temos `Node Port` ele tambêm irá fazer um bind com uma porta do nosso Node.js, a porta definida irá ficar escutando na porta do nosso cluster assim basta fazer um CURL para o ip e porta e assim iremos conseguir consumir a nossa aplicação.

### Dashboard

Agora para conhecimento iremos utilizar a Dashboard padrão do Kubernetes, para utilizar ela basta apenas executar o seguinte comando

```
  $ kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
```

Está aplicação estará apenas rodando no `Cluster IP` será apenas acessivel internamente, para acessar externametne iremos precisar utilizar o `NodePort` com o próximo comando iremos expor nossa aplicação para o mundo externo. Sendo que `port` será a uma porta definada por nós para o acesso interna e a `target-port` será a porta que estará acessível externamente.

```
  $ kubectl expose deployment kubernetes-dashboard --name=kubernetes-dashboard-nodeport --port=443 --target-port=8443 --type=NodePort -n kube-system
```

Com a nossa Dashboard exposta ao mundo real basta identificarmos qual é a sua porta exposta, para verificar basta digitar o seguinte comando

```
  $ kubectl get all --all-namespaces
```

Será informado o nome dos nosso services, como anterimos definimos que o nome do nosso service de dashboard seria kubernetes-dashboard-nodeport, basta olharmos para a linha do mesmo.
No caso da minha execução a porta exibida foi 32210, como foi criado uma VMs na Cloud deixarei ativado para exibição do projeto, para acessar basta acessar o seguinte link [https://35.226.15.29:32110](https://35.226.15.29:32110).

Em primeiro acesso devemos configurar o token de acesso, para isso primeiros devemos verificar o arquivo de YAML, pois nele foi criado um `Service Account` devemos acessar-ló e pegar o nome, após isso devemos dar um `describe` na sua `service account`.

```
  $ kubectl describe sa kubernetes-dashboard -n kube-system
```

Descoberto o nome do token agora devemos pega o token por completo

```
  $ kubectl get secret ${TOKEN} -n kube-system -o yaml
```

Agora temos o token por completo, mas mesmo assim não podemos utilizar ele na Dashboard pois está em base64, devemos decodificar ele, para isso bastar executar o seguinte passo

```
  $ echo ${SECRET} | base64 decode
```

Agora temos o token para ser inserido na Dashboard, feito isso teremos conectado na nossa Dashboard, mas temos um problema ainda, o usuário do `service account` ele tem a permissão miníma, não pode ver nada da nossa Dashboard ( A real função dele é apenas para pode criar toda a nossa Dashboard ). Devemos criar nosso próprio usuário com o seguintes comandos.

```
  $ kubectl create serviceaccount ${USERNAME} -n kube-system
  $ kubectl create clusterrolebinding ${USERNAME}-binding --clusterrole=cluster-admin --serviceaccount=kube-system:${USERNAME}
  $ kubectl describe sa ${USERNAME} -n kube-system
  $ kubectl get secret ${TOKEN} -n kube-system -o yaml
```

Feito isso foi criado o usuário, dado permissãoes de adminstrador a ele, recuperado o token e decodificado o mesmo. Agora podemos usar o token para acessar o Kubernetes como adminstrador. Agora obtido sucesso em ter criado um primerio Kubernetes com a configuração padrão, irei criar um arquivo YAML para gerar minha própria aplicação, na [página do Kubernetes na documentação](https://kubernetes.io/docs/concepts/services-networking/service/#defining-a-service) existe expecificações de como utilizar, iremos definir da forma mais básica possível

```yaml
  apiVersion: v1
  kind: Service
  metadata:
    name: kubernetes-dashboard-nodeport-yaml
    namespace: kube-system
  spec:
    type: NodePort
    selector:
      run: kubernetes-bootcamp
    ports:
    - protocol: TCP
      port: 443
      targetPort: 8443
```

Com essas configurações pdoemos subir nosso primeiro pod descrito por um yaml, `name` é o nome que daremos ao nosos pod, `selector` deve ser verificado o nome dele no painel do Kubernetes e `ports`tem que vser verificado qual a porta que nosso container está sendo executada, como anteriormente definimos que a mesma seria na porta 8443, podemos utilizar a mesma. No servidor bastar executar os seguintes comandos

```
  $ nano ${FILE-NAME}.yaml
  $ more ${FILE-NAME}.yaml
  $ kubect apply -f ${FILE-NAME}.yaml
```

Com o comando `nano` nos criamos um arquivo com o nome definido, com esse arquivo criado basta copiar as configurações inseridas mais acima, o comando `more` só para verificar se o arquivo esta ok e por ultimo nos aplicamos nosso arquivos, devemos receber uma mensagem que o mesmo foi criado. Até agora foi seguido apenas exemplos do kubernetes para o conhecimento da ferramenta, a partir desse momento será montado uma aplicação a partir do projeto criado em Node.js e React.


### Criando nosso Namespaces

  Como dito anteriormente namespaces são ambientes de trabalhos, será feito três ambientes devops, prod e stagin. Para criar esses ambientes é simples, basta criar nosso arquivlo .yaml e passar as configurações de cada ambiente,
  podemos seguir o descritor de deployment que está disponível na [documentação do kubernetes](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/#creating-a-deployment)

```yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: frontend
    labels:
      app: frontend
  spec:
    replicas: 2
    selector:
      matchLabels:
        app: frontend
    template:
      metadata:
        labels:
          app: frontend
      spec:
        containers:
        - name: frontend
          image: fcamparasilva/frontend:alpha
          ports:
          - containerPort: 80
  ```

Neste yaml possui dois pontos de entrada, uma `metada` e o outro `spec`, sendo que `spec` vai inferir diretamente no pods enquanto o `metada` é nos service, dentro do `spec`temos três
pontos de entrada, `selector`, `template`, `replicas`, `template` é o runtime do pods, isso significa que o pod irá subir com o label marcado como `frontend` (especificado no metadata),
em passos anteriores foi criados as imagens do [Docker Hub](https://hub.docker.com/) e subido, agora iremos utilizar a mesma imagem no `spec containers`. Agora por útlimo está faltando o
`selector`, todas vez que o deployment precisa achar um pod ou um service precisa chegar em um pod, eles sempre utilzam a label (mathLabels), é importante as labels em metada sejam iguais
ao do matchLabels para eles poderem se encontrar. Feito isso poderá ser acesso a aplicação a partir do IP externo da aplicação. Criado o YAML do frontend, agora será desenvolvido para o primeiro backend criado que é o de user. Nesse backend iremos começar a criar nossos ambientes, o primerio será de staging, para definir o ambiente basta inserir um nova flag

```
  kubectl apply -f backend-user-serivce.yaml -n staging
```

```yaml
kind: Service
apiVersion: v1
metadata:
  name: backend-user
spec:
  type: NodePort
  selector:
    app: backend-user
  ports:
    - protocol: TCP
      port: 80
      targetPort: 3020
```

Com o services criado iremos fazer o deployment da nossa imagem de docker.

```
  kubectl apply -f backend-user-deployment.yaml -n staging
```

```yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: backend-user
    labels:
      app: backend-user
  spec:
    replicas: 2
    selector:
      matchLabels:
        app: backend-user
    template:
      metadata:
        labels:
          app: backend-user
      spec:
        containers:
        - name: backend-user
          image: fcamparasilva/backend-user:alpha
          ports:
          - containerPort: 3020
          env:
          - name: NODE_ENV
            value: "staging"
          - name: MONGO_URI
            value: "mongodb://felipe:abc123@ds211265.mlab.com:11265/wariorcamp"
          - name: SECRET_OR_KEY
            value: "b00tc4mp18"
```

Agora já temos nosso Frontend e backend de user sendo executado em nosso ambiente de staging, agora iremos criar nosso último microserviço que será o de scm.

```
  kubectl apply -f backend-service.yaml -n staging
```

```yaml
kind: Service
apiVersion: v1
metadata:
  name: backend-scm
spec:
  type: NodePort
  selector:
    app: backend-scm
  ports:
    - protocol: TCP
      port: 80
      targetPort: 3030
```

```
  kubectl apply -f backend-deployment.yaml -n staging
```

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: backend-scm
  labels:
    app: backend-scm
spec:
  replicas: 2
  selector:
    matchLabels:
      app: backend-scm
  template:
    metadata:
      labels:
        app: backend-scm
    spec:
      containers:
      - name: backend-scm
        image: fcamparasilva/backend-scm:alpha
        ports:
        - containerPort: 3030
        env:
        - name: NODE_ENV
          value: "staging"
        - name: GITHUB_CLIENT_ID
          value: "e50ff23474ac9ae0a87a"
        - name: GITHUB_CLIENT_SECRET
          value: "0ab3cbde9601aafd574e13a8971f934755ae1d1d"
        - name: SECRET_OR_KEY
          value: "b00tc4mp18"
```

Com as três partes do sistema criadas e no nosso ambiente de Staging, agora teremos que fazer a comunicação entre elas, para isso devemos passar para o fronend qual ambiente ele estará executado, como o projeto está feito em REACT e qualquer framework atual no momento da geração o código gerado é estático logo não temos como passar dinâmicamente as váriaveis de ambientes e alterar em execução, então temos que fazer todas essas configurações diretamente no nosso arquivo de enviroment dentro do frontend e altera algumas partes do código do Docker file para receber as váriaveis que necessitamos.

```Dockerfile
  FROM node:8 as builder
  ARG NPM_ENV=development

  WORKDIR /usr/src/app
  COPY package*.json ./
  RUN npm install
  COPY src/ ./src/
  COPY public/ ./public/
  RUN npm run build:${NPM_ENV}

  FROM nginx:1.15.5
  COPY --from=builder /usr/src/app/build/ /usr/share/nginx/html
  EXPOSE 80
```

Em nosso arquivo Dockerfile apenas foi inserido uma linha e modificada outra, foi inserido `ARG` aqui podemos definir um váriavel que iremos passar na execução do build do docker e em `RUN` definimos qual o comando será exeuctado (Os comandos definidos para a execução estão dentro do .package.json). Para passar a váriavel na execução do Docker basta inserir --build-arg NPM_ENV=`{VARIAVEL}`

```
  docker build -t ${NAME-IMAGE} --build-arg ${ENV}=${VARIABLE}
```
Com todos os services e deployment sendo executado corretamente irá ser feito uma correção para deixar no padrão WEB que temos uma regra que é: "O que é inerente ao ambiente fique no ambiente", nosso deployment temos uma env que defini em qual o ambiente o deployment será executado, isto está incorreto, para corrigir iremos usar um recurso do Kubernetes que é configMap.

### ConfigMap

  Ele ficará junto com o namespace, poderá ser criado vários configMap, tudo que for inerente ao ambiente será removido do deployment e inserido no nosso configMap

Agora será configurado o configMap para nossos serviços, será gerado configurações para o nosso ambiente de staging e production.

```yaml
---
kind: ConfigMap
apiVersion: v1
metadata:
  name: questcode
  namespace: staging
data:
  NODE_ENV: staging
  GITHUB_CLIENT_ID: e50ff23474ac9ae0a87a
---
kind: ConfigMap
apiVersion: v1
metadata:
  name: questcode
  namespace: prod
data:
  NODE_ENV: production
  GITHUB_CLIENT_ID: e50ff23474ac9ae0a87a
```

feito isso teremos que corrigi o nosso deployments e inserir para pegar os valores diretamente do nosso configMap

### SecretMap
  Semelhante ao configMap mas a inteção desse arquivo é sempre de manter as senhas, todas as senhas devem ser matindas em base64 (não por segunraça, mas sim por usabilidade do Kubernetes), no arquivo YAML será semelhante, iremos utilizar a chave secretRef.

```YAML
  secretKeyRef:
    valueFrom:
    name:
```

### HELM

Agora iremos utilizar o HELM, ele é um gerenciado do kubernetes (Package manager), ele irá gerar informações importantes para monitoramento, irá ajudar em deploy e roolback. Helm funciona da seguinte forma, ele será instalando no nosso server irá solicitar ao Kubernetes qual o kubesystem, iremos passar um SA (Service account) para o HELM poder gerenciar todas nossas aplicações. Para isso primeiro devemos fazer a instalação do pacote do HELM basta cessar a documentação e seguir os passos. Existem várias formas de instalação do mesmo, mas nesse estudo iremos seguir a instalação por script.

```
  $ curl -LO https://git.io/get_helm.sh
  $ chmod 700 get_helm.sh
  $ ./get_helm.sh
```

Feito isso nosso Helm está sendo executado no cluster, mas ele não possui nenhum permissão, terá que ser criado um [SA]([https://kubernetes.io/docs/tasks/configure-pod-container/configure-service-account/) para o mesmo.

```YAML
---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: tiller
  namespace: kube-system
---
kind: ClusterRole
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: allresources
rules:
- apiGroups: ["*"]
  resources: ["*"]
  verbs: ["*"]
---
kind: ClusterRoleBinding
apiVersion: rbac.authorization.k8s.io/v1
metadata:
  name: tiller
subjects:
- kind: ServiceAccount
  namespace: kube-system
  name: tiller
  apiGroup: ""
roleRef:
  kind: ClusterRole
  name: allresources
  apiGroup: rbac.authorization.k8s.ioP
```

Com esse "service account" criada agora temos que informar para nossa HELM utilizar ela, iremos digitar um comando que irá pegar um arquivo criado no nosso sistema que terá a confugaração do nosso "tiller-patch", para aplicar iremos executar o seguinte comando.

```
  $ kubectl patch deployment tiller-deploy -n kube-system --patch "$(cat ${FILE-NAME})
```

```YAML
spec:
  template:
    spec:
      serviceAccountName: tiller
```

Helm trabalha com repositórios de charts (mapas), o helms possui um [repositório](https://github.com/helm/charts#helm-charts) do mesmo, agora será criado nosso primeiro chart, para iniciar iremos criar uma estrutura de pastas seguindo o padrão do Helm

```folders
  .
    ├── ...
    ├── charts                  # Pasta principal
    │   ├── templates           # Onde irá ficar os deployment e services
    │   │   ├── ...             # Deployment e services
    │   ├── charts.yaml         # Tagueamento e versionamento do charts
    │   └── values.yaml         # Várias definidas dentro dos deployment e services
    └── ...
```

Após ter criado nosso primeiro chart sozinho iremos utilizar a ferramente do helm que gera automáticamente para nos para isso basta executar o seguinte comando

```
  $ helm create frontend
  $ helm create backend-user
  $ helm create backend-scm
```

Com o conhecimento da craição de Charts e finalizado a criação dos charts do Frontend agora iremos proguedir para os outros dois microserviços o de frontend e o de backend.

Com o todos os charts criados iremos armazenas nossos charts em algum lugar, existe um repositóry para os charts chamado "ChartMuseum", é um repository apenas em terminal criado em GoLang. Ele deve ser instalado e pode ser instalado pelo próprio Helm, poderemos criar um repositorio com o nome desejado e iremos publicar todos nossos charts dentro desse ChartMuseum, para fazer essa temos que criar um YAML com as configurações do ChartMuseum.

```YAML
  env:
    open:
      STORAGE: local
      DISABLE_API: false
      ALLOW_OVERWRITE: true
  service:
    type: NodePort
    noddePort: 30010
```

Com nosso YAML de configuração criada e nomeado como `chartmuseum-config.yaml` podemos executar ele e criar nosso repositório, iremos começar a trabalhar no nosso namespace devops.

```
  $ helm install --name charmuseum --namespace devops -f chartmuseum-config.yaml stable/chartmuseum
```

Agora nosso repositório estara funcionando dentro do nosso cluster, agora vamos adicionar nosso repositório na lista do helm pos nos só temos o stable, para fazer isso basta digitar o seguinte comando

```
  $ helm repo add questcode http://$(kubectl get nodes --namespace devops -o jsonpath="{.items[0].status.addresses[0].address}"):30010
```

O comando `$(kubectl get nodes --namespace devops -o jsonpath="{.items[0].status.addresses[0].address}")` serve apenas para trazer o ip que o cluster está rodando. Agora iremos instalar um plugin que irá habilitar dar push ao nosso repositório, para instalar esse plugin basta usar o [helm push](https://github.com/chartmuseum/helm-push) para instalar ele apenas executar o seguinte comando

```
  $ helm plugin install https://github.com/chartmuseum/helm-push
```

Agora com nosos plugin criado, nossos charts funcionando corretamente, agora vamos popular nosso repositório.

```
  $ helm lint backend-scm/
  $ helm push backend-scm/ questcode

  $ helm lint backend-user/
  $ helm push backend-user/ questcode

  $ helm lint frontend/
  $ helm push frontend/ questcode

  $ helm repo update
```

Feito isso temos todos nossos charts dentro do nosso repositório para ver é apenas necessário executar ` $ helm ls`, o prossimo passo sera gerar todos os nossos deploy a partir do nosso repositório, 

```
  $ helm install questcode/frontend --namespace staging --name staging-frontend
  $ helm install questcode/backend-scm --namespace staging --name staging-backend-scm
  $ helm install questcode/backend-user --namespace staging --name backend-user
```

Agora temos todos os nossos charts instalado pelo nossos helm e utilizando o repositório, agora com todo os nossos charts rodando utilizando nosso repositório iremos fazer uma simulação de como seria um update para isso temos que gerar uma nova imagem do docker alterando a tag dele e depois subir para nosso repositório de imagens, feito isso iremos sobrescrever nosso Charts com o seguinte comando

```
  $ helm upgrade staging-backend-user questcode/backend-user --set image.tag=0.1.4
```

# Jenkins

È uma ferramenta criada para fazer integração contínua (CI), iremos fazer a instalação do Jenkins por um arquivo YAML. Antes de tudo vamos criar um volume para nosso Jenkins para poder previnir qualquer crash teremos nossos dados salvos nesse volume.

```
  $ cd /mnt/
  $ sudo mkdir data-jenkins
  $ cd data-jenkins/
  $ helm install --name jenkins --set persistence.existingClaim=jenkins --set master.serviceType=NodePort --set master.nodePort=30808 --namespace devops stable/jenkins
```

Agora nosso Jenkins estará sendo executado na porta definada em nosso YAML, para conseguimos pegar a senha do mesmo devemos verifcar o status do helm.

```
  $ helm status jenkins
```

Visualizando o nosso status terá uma informação de Notes que estará informando como pegar o password do admin, basta seguir os passos descritos, agora precisamos executar um comando para liberar o permissionamento do jenkins, será o SA já criado.

```
  kubectl create rolebinding sa-devops-role-clusteradmin --clusterrole=cluster-admin --serviceaccount=devops:default --namespace=devops
```

Agora iremos criar nossas chaves de SSH para comunicação do Jenkins e do github, para fazer isso precisamos gerar a chaves privadas e públicas na nossa máquina.

```
  $ ssh-keygen -t rsa -b 4096 -C "jenkins-github"
```

Com nossa chave basta inserir no [github keyes](https://github.com/settings/keys)

Tudo feito podemos criar nosso primeiro pipeline a criação dos pipe são simples mas deve seguir um estrutura definida pelo jenkins.

## Node

É uma máquina que tem uma parte do jenkins e é capaz de executar nossos pipeline

## Stage

É estágio do processo que está sendo executado

### Comands Docker

| COMANDOS                                    | DESCRIÇÃO                                                                                                                     |
| ------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| `$ docker build .`                          | Executa o Dockerfile                                                                                                          |
| `$ docker build -t ${NAME} .`               | Executa o Dockerfile e insere um tag para essa imagem                                                                         |
| `$ docker pull`                             | Baixa uma imagem, esse é imagem baixada do Docker Host caso não exista ele procura no Registry e depois armazena no Host      |
| `$ docker run`                              | Basta passa o nome da imagem ele é procurando no Docker Host caso não exista ele procura no Registry e depois joga a imagem no repositório de imagens local e depois instância ela em um container |
| `$ docker run -d ${NAME}`                   | Executa a imagem em background, liberando o terminal                                                                          |
| `$ docker run -d -p ${PORT-EXTERNAL}:${PORT-INTERNAL} ${NAME}` | Mapeia a porta necessário para export o container, pois pode existir várias imagens na mesma instância, pois a porta port-internal é fixa mas a port-external é dinâmica |
| `$ docker images`                           | Lista as imagens                                                                                                              |
| `$ docker ps`                               | Lista os containers em execução                                                                                               |
| `$ docker rm`                               | Para remover um container basta digita os 4 primeiros digitor do COINTAINER ID (Para encontrar o ID basta executar docker ps) |
| `$ docker rm -f`                            | Remover um container com force                                                                                                |
| `$ docker rm i`                             | Para remover uma imagem basta digita os 4 primeiros digitor do IMAGE ID (Para encontrar o ID basta executar docker images )   |
| `$ docker ps -a`                            | Lista todos os containers                                                                                                     |
| `$ docker inspect ${NAME}`                  | Descreve o que está ocorrendo na execução do docker passando o name (Para encontrar o name basta digitar docker ps)           |
| `$ docker login`                            | Para acessar seu docker hub                                                                                                   |
| `$ docker push ${IMAGE-NAME}`               | Para dar push na imagem criada                                                                                                |
| `$ docker exec -t ${CONTAINER-NAME} sh`     | Executa o container no modo iterativo                                                                                         |
| `$ docker run -d --env NODE_ENV=${VARIABLE}`| Executa o container definindo uma váriavel de ambiente para o mesmo                                                           |
| `$ docker logs ${IMAGE-NAME}`               | Exibi logs do container                                                                                                       |
| `$ docker build -t ${NAME-IMAGE} --build-arg ${ENV}=${VARIABLE} .` | Gerar um build a passando um váriavel para o Dockerfile                                                |


### Comands Docker Composer

| COMANDOS                                        | DESCRIÇÃO                              |
| --------------------------------------------    | -------------------------------------- |
| `$ docker-compose up -d`                        | Executar o arquivo do docker           |
| `$ docker-compose ps`                           | Lista as images do composer executando |
| `$ docker-compose logs`                         | Exibi um log da execução               |
| `$ watch docker-compose ps`                     | Fica assistindo o container            |
| `$ docker-compose scale ${IMAGE}=${N}`          | Faz o escalonamento dos containers     |

### Comands Kubernetes

| COMANDOS                                              | DESCRIÇÃO                                                 |
| ----------------------------------------------------- | --------------------------------------------------------- |
| `$ kubectl cluster-info`                              | Info onde o kubectl está sendo executado                  |
| `$ kubect get nodes`                                  | Verifica os nodes                                         |
| `$ watch kubectl get all --all-namespaces`            | Pega todos os recursos dentro do kubernetes               |
| `$ kubectl get pods`                                  | Informa os pods que estão sendo executados                |
| `$ kubectl logs`                                      | Informa os logs que estão sendo exibidos                  |
| `$ kubectl get deploy ${PODNAMES}`                    | Acessar o recurso estruturado                             |
| `$ kubectl edit deploy ${PODNAMES}`                   | Alterar o recurso estruturado                             |
| `$ kubectl describe ${PODNAMES} -n kube-system`       | Pegar a descrição de um pod                               |
| `$ kubectl describe sa ${PODNAMES} -n kube-system`    | Pega a descrição do service account de um pod             |
| `$ kubectl get secret ${TOKEN -n kube-system -o yaml` | Descobre a senha a partir do token                        |
| `$ kubectl delete ${NAME} -n kube-system`             | Deleta um serviço do Kubect a partir do seu nome          |
| `$ kubectl get ns`                                    | Ver namespaces criados                                    |
| `$ kubect get all -n ${AMBIENTE}`                     | Verifica todos os serviços criado para o ambiente         |
| `$ kubectl apply -f ${NAME}`                          | Aplica um arquivo YAML para o cluster                     |
| `$ kubectl apply -f ${NAME} --namespace ${AMBIENTE}`  | Aplica um arquvio YYAML para o cluser e define o ambiente |
| `$ kubectl get all cm --all-namespaces`               | Exibi todos os config maps                                |
| `$ kubectl exec -t ${POD-NAME} sh -n ${NAMESPACE}`    | Acessa linha de comando do pod                            |
| `$ kubectl edit svc ${SERVICE-NAME} --all-namespaces` | Acessa no modo de edição o arquivo de service             |
| `$ kubectl patch deployment tiller-deploy -n kube-system --patch "$(cat ${FILE-NAME})` | Acessa no modo de edição o arquivo de service |

### Comands HELM

| COMANDOS                                              | DESCRIÇÃO                                                         |
| `$ helm repo update`                                  | Baixa os catálogos                                                |
| `$ helm lint ${CHART}`                                | Verificar se nosso chart tem algum erro                           |
| `$ helm repo list`                                    | List repositórios                                                 |
| `$ helm search ${WORDS-SEARCH}`                       | Faz uma pesquisa apartir do texto informado                       |
| `$ helm search`                                       | List tudo que está no repositórios                                |
| `$ helm status ${HELM-NAME}`                          | Retorna algumas informações do nosso helm                         |
| `$ helm install ${FILE}`                              | Faz uma instalação de algo listado no helm                        |
| `$ helm install . --name ${NAME} --namespace ${NAMESPACE}` | Cria a partir do diretório atual e define name e qual namespace irá pertencer |
| `$ helm upgrade ${HELM-NAME} ${HELM-REPO} --set image.tag=${TAG-VERSION}` | Faz upgrade de um helm em execução            |
| `$ helm status ${FILE}`                               | Exibir informações de algo instalado pelo helm                    |
| `$ helm history ${HELM-NAME}`                         | Exibi o histórico do helm                                         |
| `$ helm rollback ${HELM-NAME} ${REVISION-NUMBER}`     | Faz rollback para o ultimo deployment do helm                     |
| `$ helm create ${NAME}`                               | Cria automatátcimanete os arquivos necessários                    |
| `$ helm delete ${NAME}`                               | Delete algo instalado pelo instalador do helm                     |
| `$ helm delete --purge ${NAME}`                       | Delete algo instalado pelo instalador do helm e limpa o histórico |
