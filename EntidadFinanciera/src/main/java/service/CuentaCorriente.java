package service;

public class CuentaCorriente extends Cuenta implements IGestionSaldo {
    private double giroCubierto = saldo;

    public double getGiroCubierto() {
        return giroCubierto;
    }

    public void setGiroCubierto(double giroCubierto) {
        this.giroCubierto = giroCubierto;
    }

    @Override
    public boolean agregarSaldo(double monto) {
        saldo += monto;

        return true;
    }

    @Override
    public boolean quitarSaldo(double monto) {
        if (saldo + giroCubierto < monto) return false;

        saldo -= monto;

        return true;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    //VER!
    @Override
    public int getOperaciones() {
        return 0;
    }
}
