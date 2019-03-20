### Required
[Node.js and NPM](https://nodejs.org/en/download/)
[Docker](https://docs.docker.com/install/linux/docker-ce/ubuntu/#install-using-the-repository)

È aconselhável utilizar Linux, pois no windows Docker irá instalar um SO linux em seus sistema para poder executar

# Introdução
  Como as aplicações eram construidas nos últimos 20 anos? Boa parte da evolução se da conta pela popularização de alguns ecossistemas (Internet, Mobile e IoT).

![alt Gráfico evolução da internet](http://missaodevops.com.br/img/warrior/cap03-ecommerce-growth-2018.png)
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

### Statless
  Não armazena nenhum tipo de configuração do usuário, facilitando na criação de replicas dos serviços.

 ### Vantagens
 - Facilidade no auto-scale

 ### Desvantagens
 - Custo de desenvolvimento grande

# Container
  Um container possui todas as configuração para o nosso serviço, tanto quanto frotnend e backend, basicamente é uma imagem (é a base para gerar 1 ou N containers, é construida atráves de um arquivo chamado Dockerfile) com todas as configurações necessárias, mas subir essa imagem e utilizar em seu servidor. Um comparativo de container é as máquinas virtuais, podendo criar várias snapshots com as configurações do nosso serviço, mas o problema disso que acaba se tornando custoso pela questão que a cada snapshot devemos instalar um SO por completo e não iremos utilizar todos os recursos dele. Diferente do Container que ele é isolado mas compartilha o mesmo SO, logo poupando recurso computacional.

  ![alt Comparação entre Hypervirsos e Container](http://imesh.github.io/images/contvsvm.png)
  > Hypervisor X Container

# Docker

  Para entender o ecossistema do Docker, é interessante saber como funciona, ele é dividio em várias camadas, sendo a primeira o Client aonde será executado os comando, Host aonde fica armazenada as imagens e o containers e o Registry sendo aonde busca as imagens do Docker. Utilizaremos o [Docker Registry](https://hub.docker.com/) pois é o repositório oficial do Docker.

### Comands Docker

| COMANDOS        | DESCRIÇÃO           |
| --------------- | ------------------- |
| $ docker build  | Constrói uma imagem |
| $ docker pull   | Baixa uma imagem, esse é imagem baixada do Docker Host caso não exista ele procura no Registry e depois armazena no Host |
| $ docker run    | Basta passa o nome da imagem ele é procurando no Docker Host caso não exista ele procura no Registry e depois joga a imagem no repositório de imagens local e depois instância ela em um container |
| $ docker images | Lista as imagens                |
| $ docker ps     | Lista os containers em execução |
| $ docker ps -a  | Lista todos os containers       |
| $ docker rm     | Para remover um container basta digita os 4 primeiros digitor do COINTAINER ID (Para encontrar o ID basta executar docker ps) |
| $ docker rm i   | Para remover uma imagem basta digita os 4 primeiros digitor do IMAGE ID (Para encontrar o ID basta executar docker images ) |
