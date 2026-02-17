import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class JogoDaVelha implements ActionListener {
   Random gerador = new Random();
   boolean turno_do_jogador1;
   Tabuleiro tabuleiro = new Tabuleiro(this);


    JogoDaVelha() {
        primeiroTurno();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i =0; i < 9; i++) {
            if(e.getSource()==tabuleiro.getBotao(i)) {
                if(turno_do_jogador1 == true) {
                    if(tabuleiro.getTexTBotao(i).isEmpty()) {
                        tabuleiro.getBotao(i).setForeground(Color.BLUE);
                        tabuleiro.getBotao(i).setText("X");
                        turno_do_jogador1 = false;
                        tabuleiro.setTexto("Turno: O");
                        checkVitoria();
                        if(!(checkVitoria())) {
                            checkEmpate();
                        }
                    }
                } else {
                    if(tabuleiro.getTexTBotao(i).isEmpty()) {
                        tabuleiro.getBotao(i).setForeground(Color.RED);
                        tabuleiro.getBotao(i).setText("O");
                        turno_do_jogador1 = true;
                        tabuleiro.setTexto("Turno: X");
                        checkVitoria();
                        if(!(checkVitoria())) {
                            checkEmpate();
                        }
                    }
                }
            }
        }
    }

    public void primeiroTurno() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        tabuleiro.textoAumentarFonte();

        int turno = gerador.nextInt(2);

        if(turno == 1) {
            turno_do_jogador1 = true;
            tabuleiro.setTexto("Turno: X");
        } else {
            turno_do_jogador1 = false;
            tabuleiro.setTexto("Turno: O");
        }
        for(int i = 0; i < 9; i++) {
            tabuleiro.getBotao(i).setEnabled(true);
        }
    }

    public void checkEmpate() {
        //verifica se o jogo terminou
        for (int i = 0; i < 9; i++) {
            if (tabuleiro.getBotao(i).getText().equals("")) {
                return;
            }
        }
        // limpa e reseta
        for (int j = 0; j < 9; j++) {
            tabuleiro.getBotao(j).setText("");
            tabuleiro.setTexto("Empate!");
        }
    }


    public boolean checkVitoria() {
        int[][] possibilidade_de_vitorias = {
                // linhas
                {0,1,2}, {3,4,5}, {6,7,8},
                // colunas
                {0,3,6}, {1,4,7}, {2,5,8},
                // diagonais
                {0,4,8}, {2,4,6}
        };

        for (int i = 0; i < possibilidade_de_vitorias.length; i++) {
            int a = possibilidade_de_vitorias[i][0];
            int b = possibilidade_de_vitorias[i][1];
            int c = possibilidade_de_vitorias[i][2];

            String textoA = tabuleiro.getTexTBotao(a);
            String textoB = tabuleiro.getTexTBotao(b);
            String textoC = tabuleiro.getTexTBotao(c);

            if (!textoA.isEmpty() && textoA.equals(textoB) && textoA.equals(textoC)) {
                if (textoA.equals("X")) {
                    VitoriaDoX(a, b, c);
                } else {
                    VitoriaDoO(a, b, c);
                }
                return true;
            }
        }

        return false;
    }

    public void VitoriaDoX(int posicaoA, int posicaoB, int posicaoC) {
        tabuleiro.getBotao(posicaoA).setBackground(Color.green);
        tabuleiro.getBotao(posicaoB).setBackground(Color.green);
        tabuleiro.getBotao(posicaoC).setBackground(Color.green);

        for(int i = 0; i < 9; i++) {
           tabuleiro.getBotao(i).setEnabled(false);
        }
        tabuleiro.setTexto("X Venceu");
        reiniciar("X", "O", posicaoA, posicaoB, posicaoC);
    }

    public void VitoriaDoO(int posicaoA, int posicaoB, int posicaoC) {
        tabuleiro.getBotao(posicaoA).setBackground(Color.green);
        tabuleiro.getBotao(posicaoB).setBackground(Color.green);
        tabuleiro.getBotao(posicaoC).setBackground(Color.green);

        for(int i = 0; i < 9; i++) {
            tabuleiro.getBotao(i).setEnabled(false);
        }
        tabuleiro.setTexto("O Venceu");
        reiniciar("O", "X", posicaoA, posicaoB, posicaoC);
    }

    
    public void reiniciar(String vencedor, String perdedor, int posicaoA, int posicaoB, int posicaoC) {
        int respota = JOptionPane.showOptionDialog(
                null,
                "Vitoria do " + vencedor + ", deseja reiniciar o jogo?",
                "Fim de jogo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                JOptionPane.YES_OPTION
        );
        if(respota == JOptionPane.YES_OPTION) {
            for (int i = 0; i < 9; i++) {
                tabuleiro.getBotao(i).setEnabled(true);
                tabuleiro.getBotao(i).setText("");
                tabuleiro.getBotao(i).setBackground(new Color(75,0,130));
            }
            tabuleiro.getBotao(posicaoA).setBackground(new Color(75,0,130));
            tabuleiro.getBotao(posicaoA).setBackground(new Color(75,0,130));
            tabuleiro.getBotao(posicaoA).setBackground(new Color(75,0,130));
            tabuleiro.setTexto("Turno :" + perdedor);
            primeiroTurno();

        }

    }




}
