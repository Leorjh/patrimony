# patrimony

Desenvolvimento de WEB API REST para gerenciamento de patrimônios de uma empresa.

Está sendo utilizado JAVA com Spring Boot.

Para o correto funcionamento da aplicação é necessário seguir os seguintes passos.

### Configurar o projeto
- Clonar o respositório em sua pasta desejada
`git clone https://github.com/Leorjh/patrimony.git`
- Criar um banco de dados chamado `patrimony` no postgres
`create database patrimony;`
- Garantir que as configurações do banco de dados estejam iguais ao arquivo `application.yml`
url: `localhost:5432/patrimony`
user: `postgres`
senha: `postgres`

- Use o comando mvn clean install para construir a aplicação.

- Para iniciar use o comando: java -jar target/patrimonies-0.0.1-SNAPSHOT.jar.

### Usuário
Primeiro se deve criar o usuário.

Comando cURL de exemplo para criação de usuário:

`` curl
curl -X POST \
  http://localhost:8080/user \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
  "name":"Nome do Usuario",
  "login":"email@gmail.com",
  "password":"senha123"
}' 
``


JSON de exemplo para post de usuário:

Endereço: http://localhost:8080/user

Regra: não é possível gerar 2 usuários com o mesmo login, nesse caso deve ser o e-mail.

    {
      "name":"Nome do Usuario",
      "login":"email@gmail.com",
      "password":"senha123"
    }

Esse é o único que não é necessário efetuar o login, para os demais será necessário gerar o token.

Para gerar a autenticação e o token, exemplo com cURL:

Informe os dados de login e senha para o usuário criado.
``` curl
curl -v --location --request POST 'http://localhost:8080/login' --header 'Content-Type: application/json' --header 'Cookie: JSESSIONID=C5CECBF4B5758FC21378FB741B17775C' --data-raw '{

"login":"email@gmail.com",
"password":"senha123"

}'
```

Lembre-se que o usuário e senha devem estar corretos.

Se feito o login com sucesso será gerado o token como X-Authorization, nesse exemplo foi:

Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ

Esse token será usado para as próximas requisições.

###POST
- Brand

http://localhost:8080/brands

Regra: não pode ser incluído marcas com mesmo nome.

cURL:

``` curl
curl -X POST \
  http://localhost:8080/brands \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ' \
  -d '    {
        "name": "Marca"
    }'
````    
JSON:

    {
      "name":"Marca"
    }

- Patrimony

http://localhost:8080/patrimony

Regra: o número de tombo será gerado automaticamente, foi usado como id da tabela.

O brand_id deve ser um brand existente na tabela brands.

cURL:

``` curl
curl -X POST \
  http://localhost:8080/patrimony \
  -H 'authorization: Basic YWRtaW46' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 1964b229-d55f-e884-4f9e-2154f0a85393' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ' \
  -d '{
  "name":"Nome", 
  "description":"Descricao", 
  "brand":{"brand_id":26}
}'
````
JSON:

    {
      "name":"Nome", 
      "description":"Descricao", 
      "brand":{"brand_id":15}
    }

Necessário informar o X-Authorization com o token gerado para efetuar os gets.

###GET
- User

http://localhost:8080/user

cURL:

``` curl
curl -X GET \
  http://localhost:8080/user \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ'
````

- GET de user com id

Exemplo buscando usuário com o id 3

http://localhost:8080/user/3

cURL:

``` curl
curl -X GET \
  http://localhost:8080/user/3 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ'
````

- Patrimony

http://localhost:8080/patrimony

cURL:

``` curl
curl -X GET \
  http://localhost:8080/patrimony \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ'
````

- GET de patrimony com id

Exemplo buscando patrimony com o id 27

http://localhost:8080/patrimony/27

cURL:

``` curl
curl -X GET \
  http://localhost:8080/patrimony/27 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ'
````

- Brand

http://localhost:8080/brands

cURL:

``` curl
curl -X GET \
  http://localhost:8080/brands \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \  
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ'
````

- GET de brand com id

Exemplo buscando brand com o id 26

http://localhost:8080/brands/26

cURL:

``` curl
curl -X GET \
  http://localhost:8080/brands/26 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ'
```

###PUT
- Patrimony

http://localhost:8080/patrimony/38

Atualizando patrimony 38, é necessário passar qual é  o id. 

cURL: 

``` curl
curl -X PUT \
  http://localhost:8080/patrimony/38 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ' \
  -d '    {
        "name": "Nome",
        "brand": {
            "name": "Marca",
            "brand_id": 26
        },
        "description": "Nova Descricao",
        "tombo": 38
    }'
``` 

JSON:

    {
      "name": "Nome",
      "brand": {
          "name": "Marca",
          "brand_id": 26
      },
      "description": "Nova Descricao",
      "tombo": 38
    }
 
 
- Brand

Atualizando a brand 26, colocando um novo nome:

cURL:

``` curl
curl -X PUT \
  http://localhost:8080/brands/26 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ' \
  -d '{
    "name": "Nova Marca",
    "brand_id": 26
}'
```

JSON:

    {   
      "name": "Nova Marca",
      "brand_id": 26
    }

Necessário informar o X-Authorization com o token gerado para efetuar os deletes.

###DELETE
- User

Exemplo deletando usuário de id 2:

http://localhost:8080/user/2

cURL:

``` curl
curl -X DELETE \
  http://localhost:8080/user/2 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ'
```

- Patrimony

Exemplo deletando patrimony de id 27:

http://localhost:8080/patrimony/27

cURL:

``` curl
curl -X DELETE \
  http://localhost:8080/patrimony/27 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ'
```

- Brand

Exemplo deletando brand de id 28:

http://localhost:8080/brands/28

cURL:

``` curl
curl -X DELETE \
  http://localhost:8080/brands/28 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'x-authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJOb21lIGRvIFVzdWFyaW8iLCJ1c2VyTmFtZSI6Ik5vbWUgZG8gVXN1YXJpbyIsInVzZXJMb2dpbiI6ImVtYWlsQGdtYWlsLmNvbSIsImV4cCI6MTYwMjA5ODA5MH0.cutRr4beoFdxDjAuDAZv8f2cJeF44099wsmxT_NgYaqfg415WiZUaPVOfCn1Nwfx2NImT65Lj5NlJ7l05nKbIQ'
```
Regra: não permite deletar caso a brand esteja relecionada a tabela patrimony.











