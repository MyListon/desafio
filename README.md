# sistema-para-gestao
Teste desenvolvimento java guarani

A funcionalidade de adicionar cliente no aplicativo só vai funcionar com a alteração do banco de dados.

"CLI_CODIGOCLIENTE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL;" 

precisa ser INTERGER.  um banco atualizado está na pasta utils do projeto. 


TESTE DESENVOLVEDOR JAVA
Funcionais:

O aplicativo deve ser capaz de buscar/cadastrar/atualizar/deletar um cliente;:heavy_check_mark:

O aplicactivo deve ser capaz de buscar clientes pela razão social, nome fantasia
e CNPJ; :heavy_check_mark:

A listagem deve ter uma versão simples em Smartphone e uma versão com
detalhes ao lado para Tablet;

Um cliente pode ser pessoa jurídica ou fisica. Se for pessoa Física, deve ter CPF,
nome (utilizar campo Razão Social), e-mail principal, e-mail secundário, vários
endereços (mínimo de 1). Se for pessoa Jurídica, deve trocar o CPF por CNPJ e
adicionar o campo de nome fantasia.

O aplicativo deve ser capaz de buscar todos os produtos separados pelo seu
status:
NORMAL
P. ESTOQUE
LANÇAMENTO
PROMOÇÃO
O aplicativo deve exibir na listagem de produtos: descrição. código, estoque,
preço máximo e preço mínimo).

Ao pressionar um produto, deve exibir um dialog com todos os preços dele em
ordem crescente.

O aplicativo deve utilizar o banco SQLite. O modelo será passado com as:heavy_check_mark:
O aplicativo deve ter como target SDK o SDk mais recente; :heavy_check_mark:
O aplicativo deve fazer uso das boas práticas de programação android;:heavy_check_mark:

Utilize ViewPager e TabLayout
Utilize o Material Design :heavy_check_mark:
Utilize Navigation Drawer :heavy_check_mark:
Utilize o Modelo MVP.     :heavy_check_mark:
