package biblioteca;

public class Livro extends ItemBiblioteca {
    public Livro(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazo() {
        return 14;
    }

    @Override
    public double getMulta() {
        return 0.50;
    }
}
