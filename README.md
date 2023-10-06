Gestão de Eventos em Casa de Show
Este é um programa Java para a gestão de eventos em uma casa de show. O projeto está organizado em três pastas principais: conexão com o banco de dados MySQL, métodos de getter e setter, e construtores, e uma pasta DAO onde são implementadas as funções de salvar, remover, listar, entre outras operações relacionadas aos eventos da casa de show.

Estrutura do Projeto
O projeto está organizado da seguinte forma:

|-- src
|   |-- main
|       |-- java
|           |-- conexao
|           |   |-- ConexaoMySQL.java
|           |-- dao
|           |   |-- EventosoperacionaisDAO.java
|           |-- metodos
|               |-- Cliente.java
                |-- CriarEventos.java
                |-- Verificacoes.java
                |-- EventosLista.java
|-- GestaoEventosGUI.java
|-- pom.xml
|-- README.md

A pasta conexao contém a classe ConexaoMySQL responsável por estabelecer a conexão com o banco de dados MySQL.
A pasta metodos contém a classe Evento que define a estrutura de um evento e fornece métodos de getter e setter, bem como construtores para criar objetos de evento.
A pasta dao contém a classe EventoDAO que implementa as operações de persistência de dados relacionadas aos eventos, como salvar, remover, listar, etc.

Dependências
O projeto utiliza o Apache Maven para gerenciamento de dependências. O arquivo pom.xml contém as seguintes dependências:

<dependencies>
    <!-- Dependência para geração de PDFs com iText -->
    <dependency>
        <groupId>com.itextpdf</groupId>
        <artifactId>itextpdf</artifactId>
        <version>5.0.5</version>
    </dependency>
    <!-- Dependência para conexão com o banco de dados MySQL -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.1.0</version>
    </dependency>
</dependencies>
As dependências acima são necessárias para a geração de PDFs (com o iText) e para a conexão com o banco de dados MySQL.

Alunos
Este projeto foi desenvolvido pelos seguintes alunos:

FABIO GABRIEL DA COSTA AREAS
MARIA EDUARDA RIBEIRO RAMOS 
VITOR DA SILVA GUERREIRO
WILLIAN WALLACE RAMOS BRASIL

Como Utilizar
Para utilizar este programa, siga os passos abaixo:

Configure a conexão com o banco de dados MySQL no arquivo ConexaoMySQL.java na pasta conexao.
Utilize a classe Evento na pasta metodos para criar e manipular objetos de evento.
Utilize a classe EventoDAO na pasta dao para realizar operações de persistência de dados relacionadas aos eventos.
