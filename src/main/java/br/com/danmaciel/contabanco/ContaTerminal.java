package br.com.danmaciel.contabanco;

import br.com.danmaciel.contabanco.model.AccountInfo;
import br.com.danmaciel.contabanco.util.Messages;

import java.util.Scanner;
import java.util.function.Function;

public class ContaTerminal {
    public static void main(String[] args) {
        final AccountInfo info = new AccountInfo();
        final Scanner sc = new Scanner(System.in);

        showMessage(Messages.intro);
        info.setCustomerName(requestInfo(sc, Messages.requestCustomerName, String::toString));
        info.setAgency(requestInfo(sc, Messages.requestAgencyNumber, String::toString));
        info.setNumber(requestInfo(sc, Messages.requestAccountNumber, Integer::parseInt));
        info.setBalance(requestInfo(sc, Messages.requestAccountBalance, Double::parseDouble));
        sc.close();

        final String greeting = String.format(Messages.greetings, info.getCustomerName(), info.getAgency(), info.getNumber(), info.getBalance());
        showMessage(greeting);

    }

    public static void showMessage(String message){
        System.out.println(message);
    }

    // usado generics para evitar repeticao
    public static <T> T requestInfo(Scanner sc, String message, Function<String, T> c){
        showMessage(message);
        T v = null;
        try{
            v =  c.apply(sc.nextLine());
        }catch (Exception e){
            System.out.println("Entrada invalida!");
        }
        return v;
    }
}