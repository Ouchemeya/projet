import java.rmi.Naming;
import java.util.Scanner;

public class CalcClient {

    public static void main(String[] args) {
        try {
            CalcInterface calc = (CalcInterface) Naming.lookup("rmi://localhost/CalcService");
            Scanner scanner = new Scanner(System.in);
            String continuer = "oui";

            do {
                System.out.println("Entrez l'opération (add, subtract, multiply, divide) : ");
                String operation = scanner.next();
                System.out.println("Entrez le premier nombre : ");
                double a = scanner.nextDouble();
                System.out.println("Entrez le deuxième nombre : ");
                double b = scanner.nextDouble();

                double resultat = 0;
                switch (operation) {
                    case "add":
                        resultat = calc.add(a, b);
                        break;
                    case "subtract":
                        resultat = calc.subtract(a, b);
                        break;
                    case "multiply":
                        resultat = calc.multiply(a, b);
                        break;
                    case "divide":
                        resultat = calc.divide(a, b);
                        break;
                    default:
                        System.out.println("Opération invalide.");
                        continue;
                }

                System.out.println("Résultat: " + resultat);
                System.out.println("Voulez-vous continuer ? (oui/non)");
                continuer = scanner.next();
            } while (!continuer.equalsIgnoreCase("non"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
