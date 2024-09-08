package JogoDaVelha;

import java.util.Scanner;

public class Inicia {
    public void iniciarJogo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem-vindo ao Jogo da Velha!");

        System.out.println("Quem deve começar? (1 - Você, 2 - Computador): ");
        int quemComeca = sc.nextInt();

        while (quemComeca != 1 && quemComeca != 2) {
            System.out.println("Escolha inválida! Digite 1 para você ou 2 para o computador.");
            quemComeca = sc.nextInt();
        }

        // Instancia a classe JogoVelha corretamente
        JogoVelha jogo = new JogoVelha(quemComeca);
    }
}
