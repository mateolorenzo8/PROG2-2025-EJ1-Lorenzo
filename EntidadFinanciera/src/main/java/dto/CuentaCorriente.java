package dto;

import service.LogicaCuenta;

public class CuentaCorriente implements ICuenta {

    @Override
    public boolean agregarSaldo(int cuenta, double monto) {
        return LogicaCuenta.obtenerInstancia().agregarSaldo(cuenta, monto);
    }

    @Override
    public boolean quitarSaldo(int cuenta, double monto) {
        return LogicaCuenta.obtenerInstancia().quitarSaldo(cuenta, monto);
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
