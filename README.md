# Sistema de Cadastro de Produtos e Vendas

Este é um sistema desenvolvido como trabalho final para a disciplina de **Sistemas Distribuídos**. O objetivo é fornecer uma solução prática e eficiente para o cadastro de produtos e controle de vendas. O sistema permite gerenciar produtos e registrar as vendas realizadas, oferecendo um recurso essencial para o gerenciamento de inventário e análise de desempenho das vendas.

## Tecnologias Utilizadas

- **Java** - Linguagem principal para o desenvolvimento do sistema.
- **Spring Boot** - Framework para facilitar a criação de APIs REST e o gerenciamento de dependências.
- **MySQL** - Banco de dados relacional para armazenamento de informações de produtos e vendas.
- **Thymeleaf** - Motor de templates para a geração de páginas HTML dinâmicas.
- **HTML, CSS, JavaScript** - Utilizados para a interface de usuário, proporcionando uma experiência responsiva e interativa.

## Funcionalidades Principais

- **Cadastro de Produtos**: Permite o registro de produtos com informações como nome, categoria, preço e quantidade em estoque.
- **Controle de Vendas**: Registro de vendas com detalhes de produtos vendidos, quantidade, e valores totais.
- **Consulta de Estoque**: Visualização do inventário atualizado em tempo real para evitar falta de produtos.

## Estrutura do Projeto

O sistema é dividido em camadas, seguindo os princípios de desenvolvimento de sistemas distribuídos e orientado a serviços:

1. **Camada de Apresentação (Frontend)**: Desenvolvida com HTML, CSS e JavaScript, junto ao Thymeleaf para criação de interfaces amigáveis e responsivas.
2. **Camada de Serviço (Backend)**: Implementada com Spring Boot, oferecendo serviços REST para manipulação de dados de produtos e vendas.
3. **Camada de Persistência (Banco de Dados)**: Utiliza MySQL para gerenciar e armazenar as informações de maneira eficiente e escalável.

## Endpoints da API

| Método   | Endpoint             | Descrição                                |
|----------|-----------------------|------------------------------------------|
| `GET`    | `/api/produtos`      | Listar todos os produtos                 |
| `POST`   | `/api/produtos`      | Cadastrar um novo produto                |
| `PUT`    | `/api/produtos/{id}` | Editar um produto                        |
| `DELETE` | `/api/produtos/{id}` | Excluir um produto                       |
| `GET`    | `/api/vendas`        | Listar todas as vendas                   |
| `POST`   | `/api/vendas`        | Registrar uma nova venda                 |
