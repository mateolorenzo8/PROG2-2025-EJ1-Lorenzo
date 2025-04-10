package service;

public class CuentaCorriente extends Cuenta implements IGestionSaldo {
    private double giroCubierto = saldo;

    public CuentaCorriente(double giroCubierto) {
        this.giroCubierto = giroCubierto;
    }

    public double getGiroCubierto() {
        return giroCubierto;
    }

    public void setGiroCubierto(double giroCubierto) {
        this.giroCubierto = giroCubierto;
    }

    @Override
    public synchronized boolean agregarSaldo(double monto) {
        saldo += monto;
        transacciones++;

        return true;
    }

    @Override
    public synchronized boolean quitarSaldo(double monto) {
        if ((saldo - monto) <= giroCubierto) return false;

        saldo -= monto;
        transacciones++;

        return true;
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
