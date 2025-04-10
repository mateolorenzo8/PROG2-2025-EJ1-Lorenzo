package service;

import dto.CajaDeAhorro;
import dto.Cuenta;
import dto.CuentaCorriente;

import java.util.ArrayList;
import java.util.List;

public final class LogicaCuenta {
    private static LogicaCuenta instancia;
    protected List<Cuenta> cuentaList;

    private LogicaCuenta() {
        cuentaList = new ArrayList<>();
    }

    public static LogicaCuenta obtenerInstancia() {
        if (instancia == null) {
            instancia = new LogicaCuenta();
        }
        return instancia;
    }

    public boolean agregarCuenta(Cuenta cuenta) {
        Cuenta cuentaObjeto = cuentaList.stream()
                .filter(x -> x.getId() == cuenta.getId())
                .findFirst()
                .orElse(null);

        if (cuentaObjeto != null) return false;

        cuentaList.add(cuenta);
        return true;
    }

    public boolean agregarSaldo(int cuenta, double monto) {
        Cuenta cuentaObjeto = cuentaList.stream()
                .filter(x -> x.getId() == cuenta)
                .findFirst()
                .orElse(null);

        if (cuentaObjeto == null) return false;

        if (cuentaObjeto.getClass() == CuentaCorriente.class) {
            CuentaCorriente cuentaCorriente = (CuentaCorriente)cuentaObjeto;

            cuentaCorriente.agregarSaldo(monto);

            cuentaList.removeIf(x -> x.getId() == cuenta);
            cuentaList.add(cuentaCorriente);

        }
        else if (cuentaObjeto.getClass() == CajaDeAhorro.class) {
            CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cuentaObjeto;

            cajaDeAhorro.agregarSaldo(monto);

            cuentaList.removeIf(x -> x.getId() == cuenta);
            cuentaList.add(cajaDeAhorro);
        }
        else {
            return false;
        }

        return true;
    }

    public boolean quitarSaldo(int cuenta, double monto) {
        Cuenta cuentaObjeto = cuentaList.stream()
                .filter(x -> x.getId() == cuenta)
                .findFirst()
                .orElse(null);

        if (cuentaObjeto == null) return false;

        if (cuentaObjeto.getClass() == CuentaCorriente.class) {
            CuentaCorriente cuentaCorriente = (CuentaCorriente)cuentaObjeto;

            cuentaCorriente.quitarSaldo(monto);

            cuentaList.removeIf(x -> x.getId() == cuenta);
            cuentaList.add(cuentaCorriente);

        }
        else if (cuentaObjeto.getClass() == CajaDeAhorro.class) {
            CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cuentaObjeto;

            cajaDeAhorro.quitarSaldo(monto);

            cuentaList.removeIf(x -> x.getId() == cuenta);
            cuentaList.add(cajaDeAhorro);
        }
        else {
            return false;
        }

        return true;
    }

    public double consultarSaldo(int cuenta) {
        Cuenta cuentaObjeto = cuentaList.stream()
                .filter(x -> x.getId() == cuenta)
                .findFirst()
                .orElse(null);

        if (cuentaObjeto.getClass() == CuentaCorriente.class) {
            CuentaCorriente cuentaCorriente = (CuentaCorriente)cuentaObjeto;

            return cuentaCorriente.getSaldo();
        }
        else if (cuentaObjeto.getClass() == CajaDeAhorro.class) {
            CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cuentaObjeto;

            return cajaDeAhorro.getSaldo();
        }

        return 0;
    }

    public int getTransacciones(int cuenta) {
        Cuenta cuentaObjeto = cuentaList.stream()
                .filter(x -> x.getId() == cuenta)
                .findFirst()
                .orElse(null);

        if (cuentaObjeto.getClass() == CuentaCorriente.class) {
            CuentaCorriente cuentaCorriente = (CuentaCorriente)cuentaObjeto;

            return cuentaCorriente.getOperaciones();
        }
        else if (cuentaObjeto.getClass() == CajaDeAhorro.class) {
            CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cuentaObjeto;

            return cajaDeAhorro.getOperaciones();
        }

        return 0;
    }

}
