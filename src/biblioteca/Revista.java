package biblioteca;

public class Revista extends ItemBiblioteca {
    public Revista(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazo() {
        return 7;
    }

    @Override
    public double getMulta() {
        return 1.00;
    }
}
