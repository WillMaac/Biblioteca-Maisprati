-- ==========================================
-- CRIAÇÃO DAS TABELAS
-- ==========================================
CREATE TABLE usuario
(
    id           INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(100) NOT NULL,
    tipo         VARCHAR(20)  NOT NULL,
    limite_itens INTEGER      NOT NULL,
    CONSTRAINT usuario_tipo_check
        CHECK (tipo IN ('ALUNO', 'PROFESSOR')),
    CONSTRAINT usuario_limite_check
        CHECK (limite_itens > 0)
);

CREATE TABLE item
(
    id         INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo     VARCHAR(20)  NOT NULL UNIQUE,
    titulo     VARCHAR(200) NOT NULL,
    tipo       VARCHAR(20)  NOT NULL,
    autor      VARCHAR(150),
    edicao     VARCHAR(50),
    disponivel BOOLEAN      NOT NULL DEFAULT TRUE,
    CONSTRAINT item_tipo_check
        CHECK (tipo IN ('LIVRO', 'REVISTA'))
);

CREATE TABLE emprestimo
(
    id                      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id                 INTEGER        NOT NULL,
    usuario_id              INTEGER        NOT NULL,
    data_retirada           DATE           NOT NULL,
    data_devolucao_prevista DATE           NOT NULL,
    data_devolucao          DATE,
    valor_multa             NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    CONSTRAINT emprestimo_item_fk
        FOREIGN KEY (item_id)
            REFERENCES item (id),
    CONSTRAINT emprestimo_usuario_fk
        FOREIGN KEY (usuario_id)
            REFERENCES usuario (id),
    CONSTRAINT emprestimo_multa_check
        CHECK (valor_multa >= 0)
);


-- ==========================================
-- DADOS DE TESTE
-- ==========================================
INSERT INTO usuario (nome, tipo, limite_itens)
VALUES ('Anderson', 'ALUNO', 3),
       ('Gustavo Guanabara', 'PROFESSOR', 5);

INSERT INTO item (codigo, titulo, tipo, autor, edicao)
VALUES ('LI1', 'Full Stack Development with Spring Boot 3 and React', 'LIVRO', 'Michael Isvy', '3ª Edição, 2023'),
       ('LI2', 'Biblia', 'LIVRO', 'Discípulos', NULL),
       ('RE1', 'Wired', 'REVISTA', NULL, 'Edição 320'),
       ('RE2', 'MIT Technology Review', 'REVISTA', NULL, 'Edição 128');

UPDATE item
SET disponivel = FALSE
WHERE codigo = 'LI1';

INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES ((SELECT id FROM item WHERE codigo = 'LI1'),
        (SELECT id FROM usuario WHERE nome = 'Anderson'),
        CURRENT_DATE - INTERVAL '5 days',
        CURRENT_DATE + INTERVAL '9 days',
        NULL,
        0.00);

INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa)
VALUES ((SELECT id FROM item WHERE codigo = 'RE1'),
        (SELECT id FROM usuario WHERE nome = 'Gustavo Guanabara'),
        CURRENT_DATE - INTERVAL '20 days',
        CURRENT_DATE - INTERVAL '13 days',
        CURRENT_DATE - INTERVAL '10 days',
        3.00);


-- ==========================================
-- CONSULTAS (EXERCÍCIO 3)
-- ==========================================

-- 1 - Listar todo o acervo
SELECT codigo,
       titulo,
       tipo,
       disponivel
FROM item
ORDER BY tipo, titulo;

-- 2 - Empréstimos em aberto
SELECT u.nome   AS usuario,
       i.titulo AS item,
       e.data_retirada,
       e.data_devolucao_prevista
FROM emprestimo e
         JOIN usuario u ON u.id = e.usuario_id
         JOIN item i ON i.id = e.item_id
WHERE e.data_devolucao IS NULL;

-- 3 - Total de multas acumuladas por usuário
SELECT u.nome             AS usuario,
       SUM(e.valor_multa) AS total_multas
FROM usuario u
         JOIN emprestimo e ON e.usuario_id = u.id
GROUP BY u.nome
ORDER BY total_multas DESC;

-- 4 - Itens que nunca foram emprestados
SELECT i.codigo,
       i.titulo,
       i.tipo
FROM item i
         LEFT JOIN emprestimo e ON e.item_id = i.id
WHERE e.id IS NULL;