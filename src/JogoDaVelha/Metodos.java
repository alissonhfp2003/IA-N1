package JogoDaVelha;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metodos {

    public static class Jogada {
        int jogador;
        int linha;
        int coluna;

        Jogada(int Jogador, int Linha, int Coluna) {
            if (Jogador > 2 || Jogador < 1) {
                throw new IllegalArgumentException("Dados Inválidos!");
            }
            if (Linha < 0 || Linha > 2) {
                throw new IllegalArgumentException("Dados Inválidos!");
            }
            if (Coluna < 0 || Coluna > 2) {
                throw new IllegalArgumentException("Dados Inválidos!");
            }
            jogador = Jogador;
            linha = Linha;
            coluna = Coluna;
        }

        public String toString() {
            return "Jogador " + jogador + ": {" + linha + ", " + coluna + "}";
        }
    }
}


