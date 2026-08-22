package biblioteca;

public class Main {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca(10, 10);

        // ==========================
        // CADASTRO DOS LIVROS
        // ==========================

        Livro livro1 = new Livro(
                "LI1",
                "Full Stack Development with Spring Boot 3 and React (3ª Edição, 2023)"
        );

        Livro livro2 = new Livro(
                "LI2",
                "Biblia"
        );

        Revista revista1 = new Revista(
                "RE1",
                "Wired"
        );

        Revista revista2 = new Revista(
                "RE2",
                "MIT Technology Review"
        );

        biblioteca.cadastrarItem(livro1);
        biblioteca.cadastrarItem(livro2);
        biblioteca.cadastrarItem(revista1);
        biblioteca.cadastrarItem(revista2);

        // ==========================
        // CADASTRO DOS USUÁRIOS
        // ==========================

        Aluno aluno = new Aluno("Anderson");

        Professor professor = new Professor("Gustavo Guanabara");

        biblioteca.cadastrarUsuario(aluno);
        biblioteca.cadastrarUsuario(professor);

        // ==========================
        // EMPRÉSTIMOS ALUNO
        // ==========================

        System.out.println("\n===== EMPRÉSTIMOS - ALUNO =====");

        biblioteca.emprestar(
                "LI1",
                "Anderson"
        );

        biblioteca.emprestar(
                "LI2",
                "Anderson"
        );

        biblioteca.emprestar(
                "RE1",
                "Anderson"
        );

        // se o aluno já tem 3 itens o próximo empréstimo será recusado.

        biblioteca.emprestar(
                "RE2",
                "Anderson"
        );

        // ==========================
        // EMPRÉSTIMO PROFESSOR
        // ==========================

        System.out.println("\n===== EMPRÉSTIMO - PROFESSOR =====");

        // "Revista 2" continua disponível, pois foi recusada para o Anderson.
        // Aqui provamos que o professor tem limite maior (5 itens) e
        // consegue pegar o mesmo item que foi negado ao aluno.

        biblioteca.emprestar(
                "RE2",
                "Gustavo Guanabara"
        );

        // ==========================
        //LISTAGEM
        // ==========================

        biblioteca.listarAcervo();

        // ==========================
        // DEVOLUÇÃO
        // ==========================

        System.out.println("\n===== DEVOLUÇÃO =====");

        biblioteca.devolver(
                "LI1",
                "Anderson"
        );

        biblioteca.listarAcervo();
    }
}