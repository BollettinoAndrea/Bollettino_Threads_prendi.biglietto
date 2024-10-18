import java.util.concurrent.TimeUnit;
import java.util.Scanner;
public class PrendiBigliettoThread_consync extends Thread
{

    static Integer cont=0;

    String nomePersona;

    public void settaNomePersona(String nome) {
        nomePersona=nome;
    }

    public int leggiContatore() {
        return cont;
    }

    public void settaContatore(int valore) {
        cont=valore;
    }

    public void prendiBiglietto() {


        //sezione critica: leggi e settaContatore
        int c;
        synchronized(cont) {
            c=leggiContatore();
            settaContatore(c+1);
        }

        System.out.println(nomePersona+": il numero che ho preso +"+(c+1));

        //attivare per mostrare il problema
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch(Exception e) {
            System.out.println(e);
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch(Exception e) {
            System.out.println(e);
        }

    }

    public void run() {
        while(true) {
            prendiBiglietto();
        }
    }

    public static void main(String[] args) {
        PrendiBigliettoThread_consync p1=new PrendiBigliettoThread_consync();
        p1.settaNomePersona("bernini");
        PrendiBigliettoThread_consync p2=new PrendiBigliettoThread_consync();
        p2.settaNomePersona("grena");
        p1.start();
        p2.start();
        System.out.println("Premere ENTER per chiudere");
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
}