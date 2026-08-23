#  Sistema de Biblioteca

Lista de exercícios de **Programação Orientada a Objetos (Java)** e **Banco de Dados (PostgreSQL)**, implementando o controle de acervo de uma biblioteca de bairro, livros e revistas emprestados a alunos e professores.

O mesmo domínio é resolvido de duas formas independentes: um sistema em Java usando POO (herança e polimorfismo) e uma modelagem relacional em SQL (sem herança, com tabelas e `tipo`).

---

## Estrutura do projeto

```
Biblioteca/
├── src/biblioteca/
│   ├── ItemBiblioteca.java   # classe abstrata
│   ├── Livro.java
│   ├── Revista.java
│   ├── DVD.java               # categoria extra, prova de extensibilidade
│   ├── Usuario.java           # classe abstrata
│   ├── Aluno.java
│   ├── Professor.java
│   ├── Biblioteca.java
│   └── Main.java              # cenário de teste
└── database/
    └── biblioteca.sql         # criação das tabelas + dados de teste + consultas
```

---

##  Exercício 1 — Java (POO)

Sistema de biblioteca com classes, herança e polimorfismo.

- **`ItemBiblioteca`** (abstrata): `codigo`, `titulo`, `disponivel`. Prazo e multa são métodos abstratos, já que variam por tipo de item.
    - **`Livro`**: 14 dias de prazo, multa de R$ 0,50/dia.
    - **`Revista`**: 7 dias de prazo, multa de R$ 1,00/dia.
    - **`DVD`**: categoria extra, criada sem alterar nenhuma classe existente, prova de que a decisão de tipo não vazou para lugar nenhum do código.
- **`Usuario`** (abstrata): `nome`, `quantidadeEmprestada`. Limite de itens é método abstrato.
    - **`Aluno`**: até 3 itens simultâneos.
    - **`Professor`**: até 5 itens simultâneos.
- **`Biblioteca`**: `emprestar()`, `devolver()`, `listarAcervo()`, usando arrays de tamanho fixo. `listarAcervo()` percorre o acervo em um único laço, sem `if`/`instanceof` verificando o tipo do item.
- **`Main`**: cenário de teste com cadastro, empréstimos bem-sucedidos, um recusado por limite atingido, e devolução.

### Como rodar

```bash
javac -d out src/biblioteca/*.java
java -cp out biblioteca.Main
```

---

##  Exercício 2 — Modelagem SQL

Mesmo cenário do Exercício 1, modelado como tabelas relacionais no PostgreSQL, sem herança: um item é `'LIVRO'` ou `'REVISTA'` por uma coluna `tipo`, e o mesmo vale para o usuário (`'ALUNO'`/`'PROFESSOR'`).

| Tabela | Colunas principais |
|---|---|
| `usuario` | `id`, `nome`, `tipo`, `limite_itens` |
| `item` | `id`, `codigo`, `titulo`, `tipo`, `autor`, `edicao`, `disponivel` |
| `emprestimo` | `id`, `item_id`, `usuario_id`, `data_retirada`, `data_devolucao_prevista`, `data_devolucao`, `valor_multa` |

Chaves primárias com `GENERATED ALWAYS AS IDENTITY`, restrições de tipo com `CHECK`, e chaves estrangeiras ligando `emprestimo` a `item` e `usuario`.

Dados de teste: 4 itens, 2 usuários, 2 empréstimos (um em aberto, um já devolvido com multa).

---

##  Exercício 3 — Consultas SQL

Quatro consultas sobre os dados modelados no Exercício 2:

1. Listagem completa do acervo (código, título, tipo, disponibilidade).
2. Empréstimos em aberto, com nome do usuário e título do item.
3. Total de multas acumuladas por usuário.
4. Itens que nunca foram emprestados (`LEFT JOIN` + `IS NULL`).

### Como rodar

Todo o script (criação das tabelas, dados de teste e as 4 consultas) está em [`database/biblioteca.sql`](database/biblioteca.sql). Basta executá-lo em um cliente PostgreSQL (pgAdmin, DBeaver, `psql`, etc.) contra um banco vazio.

---

##  Tecnologias

- Java 17
- PostgreSQL 17

---
