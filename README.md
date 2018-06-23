# SimpleShop
Projeto de uma simples loja virtual. Criado utilizando Netbeans 8.2


## Requerimentos:
Banco de dados MYSQL 8.0.11

JDK 8

Glassfish 4.1.1

Java Connector/J 5.1.6


## Instalação:
Crie a conexão com o banco de dados.

Obtenha um certificado SSL (ou crie um assinado, seguindo este tutorial https://helpdesk.ssls.com/hc/en-us/articles/115001604071-How-to-install-a-SSL-certificate-on-GlassFish) e o instale no servidor.

Faça a conexão com o banco de dados.

Entre no arquivo main.js e modifique a URL das chamadas ajax para a URL correta.

No arquivo ShopServlet.java:
  - va em SendMail()
  - modifique as variaveis:
            d_email = "remetente@gmail.com" , 
            d_uname = "login-gmail", 
            d_password = "senha-gmail", 
            d_targetemail = "receptor@gmail.com";


## Banco de dados:

Use os arquivos na pasta: BD para criar e adicionar os dados no banco.


Fazendo isso o projeto deve funcionar.
