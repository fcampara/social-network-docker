### Required
First Install Node.js
[Node.js](https://nodejs.org/en/download/) and NPM

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
  
 ### Vantagens
 - Facilidade no auto-scale
  
### Statless
  Não armazena nenhum tipo de configuração do usuário, facilitando na criação de replicas dos serviços.
