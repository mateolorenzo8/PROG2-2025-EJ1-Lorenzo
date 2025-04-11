package dto;

public class CuentaCorrienteBuilder implements Builder {
    private int id;
    private double giroCubierto;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setGiroCubierto(double giroCubierto) {
        this.giroCubierto = giroCubierto;
    }

    public CuentaCorriente getCuentaCorriente() {
        return new CuentaCorriente(id, giroCubierto);
    }
}
