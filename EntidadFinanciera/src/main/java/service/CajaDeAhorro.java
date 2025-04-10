package service;

public class CajaDeAhorro extends Cuenta implements IGestionSaldo {

    @Override
    public boolean agregarSaldo(double monto) {
        return false;
    }

    @Override
    public boolean quitarSaldo(double monto) {
        if (saldo < monto) return false;

        saldo -= monto;

        return false;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    //VER!!!
    @Override
    public int getOperaciones() {
        return 0;
    }
}
