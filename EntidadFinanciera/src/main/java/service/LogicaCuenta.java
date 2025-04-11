package service;

import entities.CajaDeAhorro;
import entities.Cuenta;
import entities.CuentaCorriente;

import java.util.List;

public final class LogicaCuenta {
    private static LogicaCuenta instancia;
    protected List<Cuenta> cuentaList;

    public static LogicaCuenta obtenerInstancia() {
        if (instancia == null) {
            instancia = new LogicaCuenta();
        }
        return instancia;
    }

    public void agregarCuenta(String tipo) {
        switch (tipo) {
            case "Caja Ahorro":
                CajaDeAhorro caja = new CajaDeAhorro();
                caja.agregarId(cuentaList.size() + 1);
            case "Cuenta Corriente":
                CuentaCorriente cuenta = new CuentaCorriente();
                cuenta.agregarId(cuentaList.size() + 1);
        }
    }

    public boolean agregarSaldo(int cuenta, double monto) {
        Cuenta cuentaObjeto = cuentaList.stream()
                .filter(x -> x.obtenerId() == cuenta)
                .findFirst()
                .orElse(null);

        if (cuentaObjeto == null) return false;

        if (cuentaObjeto.getClass() == CuentaCorriente.class) {
            CuentaCorriente cuentaCorriente = (CuentaCorriente)cuentaObjeto;

            cuentaCorriente.agregarSaldo(monto);

            cuentaList.removeIf(x -> x.obtenerId() == cuenta);
            cuentaList.add(cuentaCorriente);

        }
        else if (cuentaObjeto.getClass() == CajaDeAhorro.class) {
            CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cuentaObjeto;

            cajaDeAhorro.agregarSaldo(monto);

            cuentaList.removeIf(x -> x.obtenerId() == cuenta);
            cuentaList.add(cajaDeAhorro);
        }
        else {
            return false;
        }

        return true;
    }

    public boolean quitarSaldo(int cuenta, double monto) {
        Cuenta cuentaObjeto = cuentaList.stream()
                .filter(x -> x.obtenerId() == cuenta)
                .findFirst()
                .orElse(null);

        if (cuentaObjeto == null) return false;

        if (cuentaObjeto.getClass() == CuentaCorriente.class) {
            CuentaCorriente cuentaCorriente = (CuentaCorriente)cuentaObjeto;

            cuentaCorriente.quitarSaldo(monto);

            cuentaList.removeIf(x -> x.obtenerId() == cuenta);
            cuentaList.add(cuentaCorriente);

        }
        else if (cuentaObjeto.getClass() == CajaDeAhorro.class) {
            CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cuentaObjeto;

            cajaDeAhorro.quitarSaldo(monto);

            cuentaList.removeIf(x -> x.obtenerId() == cuenta);
            cuentaList.add(cajaDeAhorro);
        }
        else {
            return false;
        }

        return true;
    }

    public double consultarSaldo(int cuenta) {
        Cuenta cuentaObjeto = cuentaList.stream()
                .filter(x -> x.obtenerId() == cuenta)
                .findFirst()
                .orElse(null);

        if (cuentaObjeto.getClass() == CuentaCorriente.class) {
            CuentaCorriente cuentaCorriente = (CuentaCorriente)cuentaObjeto;

            cuentaCorriente.getSaldo();
        }
        else if (cuentaObjeto.getClass() == CajaDeAhorro.class) {
            CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cuentaObjeto;

            cajaDeAhorro.getSaldo();
        }

        return 0;
    }

    public int getTransacciones(int cuenta) {
        Cuenta cuentaObjeto = cuentaList.stream()
                .filter(x -> x.obtenerId() == cuenta)
                .findFirst()
                .orElse(null);

        if (cuentaObjeto.getClass() == CuentaCorriente.class) {
            CuentaCorriente cuentaCorriente = (CuentaCorriente)cuentaObjeto;

            cuentaCorriente.getOperaciones();
        }
        else if (cuentaObjeto.getClass() == CajaDeAhorro.class) {
            CajaDeAhorro cajaDeAhorro = (CajaDeAhorro)cuentaObjeto;

            cajaDeAhorro.getOperaciones();
        }

        return 0;
    }

}
