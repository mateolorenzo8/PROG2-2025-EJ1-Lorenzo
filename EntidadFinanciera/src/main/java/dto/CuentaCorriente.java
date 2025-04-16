package dto;

public class CuentaCorriente extends Cuenta implements IGestionSaldo {
    private double giroCubierto = 0;

    public CuentaCorriente(int id, double giroCubierto) {
        this.giroCubierto = giroCubierto;
        this.id = id;
    }

    public double getGiroCubierto() {
        return giroCubierto;
    }

    private void setGiroCubierto(){
        giroCubierto = this.saldo;
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
        setGiroCubierto();

        if ((Math.abs(saldo - monto)) <= giroCubierto) {
            saldo -= monto;
            transacciones++;
            return true;
        }

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
