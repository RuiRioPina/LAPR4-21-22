# US1003
=======================================


# 1. Requisitos

**US1003** As Sales Clerk, I want to register a new customer.

A interpretação feita deste requisito foi no sentido de registar um cliente no sistema, para este poder navegar os catálogos
e visualizar, comprar produtos. 

# 2. Análise

## 2.1 Futuras implementações para os outros atores

* Este processo será feito pelo Sales Clerk um dos atores que podem fazer esta ação, sendo os outros 
atores o próprio cliente e um sales manager. Por este motivo esta ação será partilha por todos, logo iremos fazer esforços para que os
outros atores (o cliente e o sales manager) possam reutilizar o código desenvolvido. *

## 2.2 Sequência das ações

* O sales clerk irá perguntar ao customer algumas informações mandatárias: nome, VAT, endereço de email e um número de telemóvel. Opcionalmente pode pedir a data de nascimento,
o género e o endereço de shipment e billing. *

## 2.3 Regras de negócio associadas aos atributos de um customer

* NOME:
* VAT:
* ENDEREÇO DE EMAIL:
* NÚMERO DE TELEMÓVEL:
* DATA DE NASCIMENTO:
* GÉNERO:
* ENDEREÇO SHIPMENT E BILLING:

## 2.5 Pré Condições

n/a.

## 2.5 Pós Condições

A informação dos customers é persistida.

## 2.6 SSD

![SSD-Diagram](Diagramas/SSD.svg/)

# 3. Design

*Nesta secção a equipa deve descrever o design adotado para satisfazer a funcionalidade. Entre outros, a equipa deve apresentar diagrama(s) de realização da funcionalidade, diagrama(s) de classes, identificação de padrões aplicados e quais foram os principais testes especificados para validar a funcionalidade.*

Usar padrão builder

## 3.1. Realização da Funcionalidade

![SD-Diagram](Diagramas/SD.svg/)

## 3.2. Diagrama de Classes

![CD-Diagram](Diagramas/CD.svg/)

## 3.3. Padrões Aplicados

* Foi utilizado o CRUD (Create, Read, Update, Delete) para trabalhar sobre os customers.

* Foi utilizado o GRASP

* Foi utilizado o Builder, já que há certos atributos que são opcionais e temos muita complexidade de regras de negócio.

* Foram utilizados o padrão repository, para representar as ações CRUD relativas ao customer.

## 3.4. Testes 
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar que não é possível criar uma instância da classe Customer com valores nulos.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Customer instance = new Customer(null, null, null, null, null, null, null);
	}

**Teste 2:** Verificar que é possível criar uma instância da classe Customer apenas com os atributos mandatórios.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Customer instance = new Customer(name, vat, email, phoneNumber, null, null, null);
	}

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*



