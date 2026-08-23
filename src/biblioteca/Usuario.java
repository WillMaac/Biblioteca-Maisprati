package biblioteca;

public abstract class Usuario {
    private String nome;
    private int quantidadeEmprestada;

    public Usuario(String nome) {
        this.nome = nome;
        this.quantidadeEmprestada = 0;
    }

    public abstract int getLimiteItens();

    public abstract String getTipo();

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEmprestada() {
        return quantidadeEmprestada;
    }

    public boolean podeEmprestar() {
        return quantidadeEmprestada < getLimiteItens();
    }

    void adicionarEmprestimo() {
        quantidadeEmprestada++;
    }

    void removerEmprestimo() {
        if (quantidadeEmprestada > 0) {
            quantidadeEmprestada--;
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " - Empréstimos: " + quantidadeEmprestada + "/" + getLimiteItens();
    }
}