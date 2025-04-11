package entities;

public abstract class Cuenta {
    protected int id;
    protected double saldo;
    protected int transacciones = 0;

    public abstract void agregarId(int id);
    public abstract int obtenerId();
}
