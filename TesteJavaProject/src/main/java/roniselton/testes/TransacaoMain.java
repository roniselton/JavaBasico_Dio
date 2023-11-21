package roniselton.testes;

import java.util.Scanner;

public class TransacaoMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String entrada = scanner.nextLine();
        String[] partes = entrada.split(",");
        if(partes == null || partes.length != 4)
            throw new IllegalArgumentException("Entrada Invalida");
       Transacao t = new Transacao( partes[0]  , partes[1] , partes[2] , Double.parseDouble(partes[3]) );
        t.imprimir();
    }
}

class Transacao {
    private String data;
    private String hora;
    private String descricao;
    private double valor;

    public Transacao(String data, String hora, String descricao, double valor) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void imprimir() {
        System.out.println(this.descricao);
        System.out.println(this.data);
        System.out.println(this.hora);
        System.out.printf("%.2f", this.valor);
    }
}