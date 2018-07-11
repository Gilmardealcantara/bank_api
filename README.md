# [BANK API]
#### API para simulações de transações bancárias.

## Spring Rest API
##### Especificações de dados JSON
Endereço (ADDR Type)

Chave   |Tipo           |Permissão              |
--------|---------      |---------------------  |
street  | String        |       user            |       
number  | Integer       |       user            |        
street  | String        |       user            |       
city    | String        |       user            |       
state   | String        |       user            |       
country | String        |       user            |       
zipcode | String        |       user            |       

Conta (ACCOUNT Type)

Chave   |Tipo           |Permissão              |Descrição
--------|---------      |---------------------  |----------
id      | Long          |       system          |       Numero da conta
balance | Double        |       user            |       Saldo da Conta 
createAt| String        |       system           |       Data de criação
updateAt| String        |       system          |       Data a última atualização

O número da conta é gerado automaticamente pelo sistema de maneira que será único.

Cliente (CLIENT Type)

Chave   |Tipo           |Permissão              |Descrição
--------|---------      |---------------------  |----------
name    | String        |       user            |       Nome completo do Cliente
age     | Integer       |       user            |       Idade do cliente 
addr    | ADDR          |       user            |       Endereço do cliente 
account | ACCOUNT       |       user            |       Conta corrente 
createAt| String        |       system          |       Data de criação
updateAt| String        |       system          |       Data a última atualização

Transação

Chave   |Tipo           |Permissão              |Descrição
--------|---------      |---------------------  |----------
value   | Double        |       user            |       Valor da transferência
send    | ACCOUNT       |       user            |       Conta que envia o valor
rcv     | ACCOUNT       |       user            |       Conta que recebe o valor
createAt| String        |       system           |       Data de criação
updateAt| String        |       system          |       Data a última atualização


#### Exemplo de dado retornado pela API
```json
{
    "content": "Descrição T1...",
    "status": 0,
    "createdAt": "2018-05-20T23:32:40.000+0000",
    "updatedAt": "2018-05-20T23:32:40.000+0000"
}
```

### Métodos REST HTTP
<model>: transactions, clients ou accounts

Method          |URI
----------------|----------------
GET(all)        | "/api/<model>" 
POST            | "/api/<model>"
GET(one)        | "/api/<model>/{id}"
PUT             | "/api/<model>/{id}"
DELETE          | "/api/<model>/{id}"

#####Exemplo: Criar cliente: 
Envio o post com o body:
```json
{
	"name":"Gilmar", 
	"age": 25,
	"addr": {
		"street": "",
		"number": 0,
		"city": "",
		"state": "",
		"country": "",
		"zipcode": ""
	},
	"account": {
		"number":1, 
		"balance": 50.00
	}
}
```
##### Exemplo: Executar transação: 
Envie o post com o body:
```json
{
	"value": 100.00,
	"send": {"id": 1}, 
	"rcv": {"id": 2}
}
```

## Database schema
![alt text](https://raw.githubusercontent.com/Gilmardealcantara/bank_api/master/databasescheme.png)

## Tecnologias

Back-end Java

* spring framework
* hibernate
* REST
* maven


## Licence

Desenvolvido por [Gilmar.Alcantara](https://github.com/Gilmardealcantara)

