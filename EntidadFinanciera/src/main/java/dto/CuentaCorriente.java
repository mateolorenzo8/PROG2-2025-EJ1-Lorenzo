package dto;

public class CuentaCorriente implements ICuenta {

    @Override
    public boolean agregarSaldo(double monto) {
        return false;
    }

    @Override
    public boolean quitarSaldo(double monto) {
        return false;
    }

    @Override
    public double getSaldo() {
        return 0;
    }

    @Override
    public int getOperaciones() {
        return 0;
    }
}
