import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

public class Aplicacao {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in, StandardCharsets.UTF_8);

        menuInicial();
        String tipoLogin = teclado.nextLine();


        teclado.close();

    }

    public static void menuInicial(){
        System.out.println("---- Bem vindo ao sistema de faculdades! ----");
        System.out.println("Deseja fazer login como :");
        System.out.println("1 - Secretário");
        System.out.println("2 - Aluno");
        System.out.println("3 - Professor");

    }

    public static String[] login(String tipoLogin){
        try{
            int tipoLoginInt = Integer.parseInt(tipoLogin);
            switch(tipoLoginInt){
                case 1:
                System.out.println("---- Bem vindo ao sistema de faculdades! ----");
                System.out.println("Deseja fazer login como :");
                System.out.println("1 - Secretário");
                System.out.println("2 - Aluno");
                    System.out.println("3 - Professor");
            }

        }
        catch(){

        }
        System.out.println("---- Bem vindo ao sistema de faculdades! ----");
        System.out.println("Deseja fazer login como :");
        System.out.println("1 - Secretário");
        System.out.println("2 - Aluno");
        System.out.println("3 - Professor");

    }
}
