package biblioteca;

public class DVD extends ItemBiblioteca {

    public DVD(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazo() {
        return 3;
    }

    @Override
    public double getMulta() {
        return 2.00;
    }

    @Override
    public String getTipo() {
        return "DVD";
    }

    @Override
    public boolean ehFeminino() {
        return false;
    }
}