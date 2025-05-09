package dto;

public class CajaDeAhorro extends Cuenta implements IGestionSaldo {

    public CajaDeAhorro(int id) {
        this.id = id;
    }

    @Override
    public synchronized boolean agregarSaldo(double monto) {
        saldo += monto;
        transacciones++;

        return false;
    }

    @Override
    public synchronized boolean quitarSaldo(double monto) {
        if (saldo < monto) return false;

        saldo -= monto;
        transacciones++;

        return false;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public int getOperaciones() {
        return transacciones;
    }

}
