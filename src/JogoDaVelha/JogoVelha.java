package JogoDaVelha;

import JogoDaVelha.Metodos;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class JogoVelha {
    private int[][] jogo = new int[3][3];
    private int computador;
    private int humano;
    private List<Metodos.Jogada> jogadas = new ArrayList<>();

    public JogoVelha(int quemComeca) {
        if (quemComeca > 2 || quemComeca < 1) {
            throw new IllegalArgumentException("O jogador pode ser 1 ou 2");
        }
        computador = (quemComeca == 1) ? 2 : 1;
        humano = (quemComeca == 1) ? 1 : 2;

        // Inicializa o tabuleiro com 0 (vazio)
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                jogo[l][c] = 0;
            }
        }

        // Começa o jogo com base na escolha do jogador
        if (quemComeca == 1) {
            JogaHumano();
        } else {
            JogaComputador();
        }
    }

        //Metodo que mostra o estado atual do tabuleiro
        public void mostraTabuleiro() {
            System.out.println("\nTabuleiro Atual:");

            for (int l = 0; l < 3; l++) {
                for (int c = 0; c < 3; c++) {
                    if (jogo[l][c] == 0) {
                        System.out.print("   ");
                    } else if (jogo[l][c] == 1) {
                        System.out.print(" X ");
                    } else if (jogo[l][c] == 2) {
                        System.out.print(" O ");
                    }

                    if (c < 2) {
                        System.out.print("|");
                    }
                }
                System.out.println();
                if (l < 2) {
                    System.out.println("---+---+---");
                }
            }
        }

        private void RegistraJogada(Metodos.Jogada j) {
            jogo[j.linha][j.coluna] = j.jogador;
            jogadas.add(j);

        }

        private boolean estaCheia() {
            for (int l = 0; l < 3; l++) {
                for (int c = 0; c < 3; c++) {
                    if (jogo[l][c] == 0) {
                        return false;
                    }
                }
            }
            return true;
        }

        private int ganhador() {
            // Verifica linhas
            for (int i = 0; i < 3; i++) {
                if (jogo[i][0] == jogo[i][1] && jogo[i][1] == jogo[i][2] && jogo[i][0] != 0) {
                    return jogo[i][0];
                }
            }

            // Verifica colunas
            for (int i = 0; i < 3; i++) {
                if (jogo[0][i] == jogo[1][i] && jogo[1][i] == jogo[2][i] && jogo[0][i] != 0) {
                    return jogo[0][i];
                }
            }

            // Verifica diagonal principal
            if (jogo[0][0] == jogo[1][1] && jogo[1][1] == jogo[2][2] && jogo[0][0] != 0) {
                return jogo[0][0];
            }

            // Verifica diagonal secundária
            if (jogo[0][2] == jogo[1][1] && jogo[1][1] == jogo[2][0] && jogo[0][2] != 0) {
                return jogo[0][2];
            }

            // Se o tabuleiro estiver cheio e não houve ganhador, retorna 0 (empate)
            return estaCheia() ? 0 : -1;
        }

        private void JogaHumano() {
            Scanner sc = new Scanner(System.in);
            mostraTabuleiro();
            System.out.println("\nSua vez. Escolha uma linha (0-2) e coluna (0-2):");

            int linha = sc.nextInt();
            int coluna = sc.nextInt();

            if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2 || jogo[linha][coluna] != 0) {
                System.out.println("Posição inválida! Tente novamente.");
                JogaHumano();
            } else {
                RegistraJogada(new Metodos.Jogada(humano, linha, coluna));
            }

            int estadoGanhador = ganhador();
            if (estadoGanhador != -1) {
                Encerra(estadoGanhador);
            } else {
                JogaComputador();
            }
        }

        private void JogaComputador() {
            System.out.println("\nVez do computador.");

            // Lógica para o computador escolher a próxima jogada
            for (int l = 0; l < 3; l++) {
                for (int c = 0; c < 3; c++) {
                    if (jogo[l][c] == 0) {
                        RegistraJogada(new Metodos.Jogada(computador, l, c));
                        int estadoGanhador = ganhador();
                        if (estadoGanhador != -1) {
                            Encerra(estadoGanhador);
                        }
                        JogaHumano();
                        return;
                    }
                }
            }
        }

        public void Encerra(int ganhador) {
            mostraTabuleiro();
            if (ganhador == humano) {
                System.out.println("\n### O GANHADOR foi VOCÊ! ###");
            } else if (ganhador == computador) {
                System.out.println("\n### O COMPUTADOR GANHOU! ###");
            } else {
                System.out.println("\n### EMPATE! ###");
            }
            System.out.println("\n### Fim do jogo ###");
            System.exit(0);
        }
    };


