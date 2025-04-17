package service;

import dto.CajaDeAhorro;
import dto.CajaDeAhorroBuilder;
import dto.Cuenta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LogicaCuentaTest {
    //Correr todos los test individualmente!
    private LogicaCuenta admin = LogicaCuenta.obtenerInstancia();

    //Trabajado con cajas de ahorro
    @Test
    void agregarCuentaCajaDeAhorroCorrecta() {
        CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
        cajaDeAhorroBuilder.setId(1);
        CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();

        boolean res = admin.agregarCuenta(cajaDeAhorro);
        var cuentas = admin.getCuentaList();

        Assertions.assertTrue(res);
        Assertions.assertEquals(cuentas.get(0).getId(), 1);
        Assertions.assertEquals(cuentas.size(), 1);
    }

    @Test
    void agregarCuentaYaExistente() {
        CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
        cajaDeAhorroBuilder.setId(1);
        CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();

        admin.agregarCuenta(cajaDeAhorro);

        CajaDeAhorroBuilder cajaDeAhorroBuilder2 = new CajaDeAhorroBuilder();
        cajaDeAhorroBuilder2.setId(1);
        CajaDeAhorro cajaDeAhorro2 = cajaDeAhorroBuilder2.getCajaDeAhorro();

        boolean res1 = admin.agregarCuenta(cajaDeAhorro2);
        var cuentas = admin.getCuentaList();

        Assertions.assertFalse(res1);
        Assertions.assertEquals(cuentas.get(0).getId(), 1);
        Assertions.assertEquals(cuentas.size(), 1);
    }

    @Test
    void agregarSaldoCorrecto2Veces() {
        CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
        cajaDeAhorroBuilder.setId(1);
        CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();

        admin.agregarCuenta(cajaDeAhorro);

        boolean res = admin.agregarSaldo(1, 1000);
        boolean res2 = admin.agregarSaldo(1, 2000);
        Cuenta cuenta = admin.getCuentaList().get(0);
        double saldo = ((CajaDeAhorro)cuenta).getSaldo();

        Assertions.assertTrue(res);
        Assertions.assertTrue(res2);
        Assertions.assertEquals(saldo, 3000);
        Assertions.assertEquals(((CajaDeAhorro) cuenta).getOperaciones(), 2);
    }

    @Test
    void agregarSaldoCuentaNoExistente() {
        boolean res = admin.agregarSaldo(1, 1000);

        Assertions.assertFalse(res);
    }

    @Test
    void quitarSaldoExitosamente() {
        CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
        cajaDeAhorroBuilder.setId(1);
        CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();

        boolean res = admin.agregarCuenta(cajaDeAhorro);
        boolean res1 = admin.agregarSaldo(1, 3000);
        boolean res2 = admin.quitarSaldo(1, 2000);
        var cuentas = admin.getCuentaList();
        var cuenta = cuentas.get(0);
        CajaDeAhorro caja = (CajaDeAhorro)cuenta;

        Assertions.assertEquals(1, cuentas.size());
        Assertions.assertTrue(res);
        Assertions.assertTrue(res1);
        Assertions.assertTrue(res2);
        Assertions.assertEquals(1000, caja.getSaldo());
    }

    @Test
    void quitarSaldoInsuficiente() {
        CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
        cajaDeAhorroBuilder.setId(1);
        CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();

        admin.agregarCuenta(cajaDeAhorro);
        admin.agregarSaldo(1, 2000);

        boolean res = admin.quitarSaldo(1, 3000);
        var cuenta = admin.getCuentaList().get(0);

        Assertions.assertFalse(res);
        Assertions.assertEquals(2000, ((CajaDeAhorro)cuenta).getSaldo());
    }

    @Test
    void consultarSaldoCuentaExistente() {
        CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
        cajaDeAhorroBuilder.setId(1);
        CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();

        admin.agregarCuenta(cajaDeAhorro);
        admin.agregarSaldo(1, 2000);

        Assertions.assertEquals(2000, admin.consultarSaldo(1));
    }

    @Test
    void consultarSaldoCuentaInexistente() {
        Assertions.assertEquals(-1, admin.consultarSaldo(1));
    }

    @Test
    void getTransaccionesCuentaExistente() {
        CajaDeAhorroBuilder cajaDeAhorroBuilder = new CajaDeAhorroBuilder();
        cajaDeAhorroBuilder.setId(1);
        CajaDeAhorro cajaDeAhorro = cajaDeAhorroBuilder.getCajaDeAhorro();

        admin.agregarCuenta(cajaDeAhorro);
        admin.agregarSaldo(1, 2000);
        admin.agregarSaldo(1, 2000);
        admin.agregarSaldo(1, 2000);
        admin.agregarSaldo(1, 2000);

        Assertions.assertEquals(4, admin.getTransacciones(1));
    }

    @Test
    void getTransaccionesCuentaNoExistente() {
        Assertions.assertEquals(-1, admin.getTransacciones(1));
    }
}