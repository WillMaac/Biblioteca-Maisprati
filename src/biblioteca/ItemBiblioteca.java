package biblioteca;

public abstract class ItemBiblioteca {

    private String codigo;
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponivel = true;
    }

    public abstract int getPrazo();

    public abstract double getMulta();

    public abstract String getTipo();
    public abstract boolean ehFeminino();

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean estaDisponivel() {
        return disponivel;
    }

    void marcarComoEmprestado() {
        disponivel = false;
    }

    void marcarComoDevolvido() {
        disponivel = true;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
                " - Título: " + titulo +
                " - Disponível: " + (disponivel ? "Sim" : "Não") +
                " - Prazo: " + getPrazo() + " dias" +
                " - Multa: R$ " + String.format("%.2f", getMulta()) + "/dia";
    }
}