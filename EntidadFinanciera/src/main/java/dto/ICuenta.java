package dto;

public interface ICuenta {
    boolean agregarSaldo(double monto);
    boolean quitarSaldo(double monto);
    double getSaldo();
    int getOperaciones();
}
