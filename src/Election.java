import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Election extends Remote {
    void vote(String eleitor, String candidato) throws RemoteException;
    String result(String candidato) throws RemoteException;
}