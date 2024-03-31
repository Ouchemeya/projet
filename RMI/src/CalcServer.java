import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcServer extends UnicastRemoteObject implements CalcInterface {

    protected CalcServer() throws RemoteException {
        super();
    }

    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }

    public double divide(double a, double b) throws RemoteException {
        if (b == 0) throw new RemoteException("Division par zéro");
        return a / b;
    }

    public static void main(String[] args) {
        try {
            Naming.rebind("rmi://localhost/CalcService", new CalcServer());
            System.out.println("Serveur de calcul est prêt.");
        } catch (Exception e) {
            System.out.println("Serveur de calcul échec: " + e);
        }
    }
}
