# <a target="_new" href="https://gabrielffguimaraes.github.io/servico-tenax-web-app/"> 🚀 servico-web-api</a>

Serviço Web Api cadastro de servidores e setores servindo uma api na nuvem .

<h5>METRICAS</h5>
<small>Boas praticas de programação utilizadas</small>
<ul>
  <li>Reaproveitamento de código</li>
  <li>Herança</li>
  <li>Polimosfismo</li>
  <li>Validacoes personalizadas </li>
  <li>Testes de integração , TestContainers etc .</li>
  <li>Profiles</li>
  <li>CI/CD (heroku)</li>
</ul>

<h5>INSTRUÇÕES LOCALHOST</h5>
<small>Para rodar o mini projeto no localhost é necessário criar o database postgres </small>
<li>
  1 - alterar o profile para dev .
  
  ``` spring.profiles.active=dev ```
</li>
<li>
  1 - sugestão docker : 
  
  ``` docker run --name dbpostgres -e POSTGRES_PASSWORD=123456 -d -p 127.0.0.1:5432:5432 postgres ```
</li>
<li>
  2 - criar o banco de dados : 
  
  ``` craete database servico_web_api  ```
</li>

