# US1001
=======================================


# 1. Requisitos

**As Sales Clerk, I want to specify a new product for sale.**

# 2. Análise

Produto é um elemento fulcral de todo o sistema e tem relações com armazéns, categorias e cátalogo.

É necessário implementar potenciais características que definem um produto (p.e. barcode, reference).

**Dependência(s)**

* Cada produto pertence obrigatoriamente a uma categoria, logo é necessário estas existirem à priori [US1005].

**Fluxo Básico**

- 1. Sales Clerk inicia a especificação de um novo produto.

- 2. Insere ou seleciona as suas características.

- 3. Sistema valida os dados necessários e regista-os, informando o sucesso da operação.

**Esclarecimento(s) do Cliente**

**1. ** Product Registration 

Some information regarding each of those (product) attributes:

- photo: it might be of any common format (e.g. png, jpeg, svg);

- short description: not empty having 30 chars maximum;

- extended description: not empty with a minimum of 20 chars and 100 chars maximum;

- technical description: multiple lines of text, preferably with no limit or the biggest possible.

- brand: not empty having 50 chars maximum;

- reference: not empty alphanumeric code with at 23 chars maximum;

- production code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.

- internal code: not empty alphanumeric code with at 23 chars maximum however it might meet a given regular expression defined/configured at deployment time.

Mandatory attributes: internal code, short and extended description.

In accordance with the specification document, other atributes might also be mandatory.

# 3. Design

## 3.1. Realização da Funcionalidade

![SD_1001](SD_US1001.svg)

## 3.2. Diagrama de Classes

*Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade.*

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas.*

## 3.4. Testes 
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar que não é possível criar uma instância da classe Exemplo com valores nulos.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*



