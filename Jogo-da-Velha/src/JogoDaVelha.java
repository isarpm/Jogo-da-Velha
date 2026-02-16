import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JogoDaVelha implements ActionListener {
   Random gerador = new Random();
   JFrame janela = new JFrame();
   JPanel titulo_painel = new JPanel();
   JPanel botao_painel = new JPanel();
   JLabel texto = new JLabel();
   JButton[] botoes = new JButton[9];
   boolean turno_do_jogador1;


    JogoDaVelha() {
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(400,400);
        janela.getContentPane().setBackground(new Color(75,0,130));
        janela.setLayout(new BorderLayout());
        janela.setVisible(true);

        texto.setBackground(Color.BLACK);
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font("PxPlus IBM VGA",Font.BOLD,30));
        texto.setHorizontalAlignment(JLabel.CENTER);
        texto.setText("Jogo da Velha");
        texto.setOpaque(true);

        titulo_painel.setLayout(new BorderLayout());
        titulo_painel.setBounds(0,0,300,50);


        titulo_painel.add(texto);
        janela.add(titulo_painel,BorderLayout.NORTH);

        for(int i = 0; i < 9; i++) {
            botoes[i] = new JButton();
            botao_painel.add(botoes[i]);
            botoes[i].setFont(new Font("Dina", Font.BOLD,120));
            botoes[i].setBackground(new Color(75,0,130));
            botoes[i].setFocusable(false);
            botoes[i].addActionListener(this);
            botoes[i].setEnabled(false);
        }


        botao_painel.setLayout(new GridLayout(3,3));
        botao_painel.setBackground(new Color(150,150,150));
        janela.add(botao_painel);
        primeiroTurno();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i =0; i < 9; i++) {
            if(e.getSource()==botoes[i]) {
                if(turno_do_jogador1 == true) {
                    if(botoes[i].getText()=="") {
                        botoes[i].setForeground(Color.BLUE);
                        botoes[i].setText("X");
                        turno_do_jogador1 = false;
                        texto.setText("Turno: O");
                        checkVitoria();
                        if(!(checkVitoria())) {
                            checkEmpate();
                        }
                    }
                } else {
                    if(botoes[i].getText()=="") {
                        botoes[i].setForeground(Color.RED);
                        botoes[i].setText("O");
                        turno_do_jogador1 = true;
                        texto.setText("Turno: X");
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

        texto.setFont(new Font("PxPlus IBM VGA",Font.BOLD,60));

        int turno = gerador.nextInt(2);

        if(turno == 1) {
            turno_do_jogador1 = true;
            texto.setText("Turno: X");
        } else {
            turno_do_jogador1 = false;
            texto.setText("Turno: O");
        }
        for(int i = 0; i < 9; i++) {
            botoes[i].setEnabled(true);
        }
    }

    public void checkEmpate() {
        //verifica se o jogo terminou
        for (int i = 0; i < 9; i++) {
            if (botoes[i].getText() == "") {
                return;
            }
        }
        // limpa e reseta
        for (int j = 0; j < 9; j++) {
            botoes[j].setText("");
            texto.setText("Empate!");
        }
    }


    public boolean checkVitoria() {
        // X linhas
        if((botoes[0].getText()=="X") && (botoes[1].getText()=="X") && botoes[2].getText()=="X") {
            VitoriaDoX(0,1,2);
            return true;
        }
        if((botoes[3].getText()=="X") && (botoes[4].getText()=="X") && botoes[5].getText()=="X") {
            VitoriaDoX(3,4,5);
            return true;
        }
        if((botoes[6].getText()=="X") && (botoes[7].getText()=="X") && botoes[8].getText()=="X") {
            VitoriaDoX(6,7,8);
            return true;
        }
        // X diagonal
        if((botoes[0].getText()=="X") && (botoes[4].getText()=="X") && botoes[8].getText()=="X") {
            VitoriaDoX(0,4,8);
            return true;
        }
        if((botoes[2].getText()=="X") && (botoes[4].getText()=="X") && botoes[6].getText()=="X") {
            VitoriaDoX(2,4,6);
            return true;
        }
        // X coluna
        if((botoes[0].getText()=="X") && (botoes[3].getText()=="X") && botoes[6].getText()=="X") {
            VitoriaDoX(0,3,6);
            return true;
        }
        if((botoes[1].getText()=="X") && (botoes[4].getText()=="X") && botoes[7].getText()=="X") {
            VitoriaDoX(1,4,7);
            return true;
        }
        if((botoes[2].getText()=="X") && (botoes[5].getText()=="X") && botoes[8].getText()=="X") {
            VitoriaDoX(2,5,8);
            return true;
        }


        // O linhas
        if((botoes[0].getText()=="O") && (botoes[1].getText()=="O") && botoes[2].getText()=="O") {
            VitoriaDoO(0,1,2);
            return true;
        }
        if((botoes[3].getText()=="O") && (botoes[4].getText()=="O") && botoes[5].getText()=="O") {
            VitoriaDoO(3,4,5);
            return true;
        }
        if((botoes[6].getText()=="O") && (botoes[7].getText()=="O") && botoes[8].getText()=="O") {
            VitoriaDoO(6,7,8);
            return true;
        }
        // O diagonal
        if((botoes[0].getText()=="O") && (botoes[4].getText()=="O") && botoes[8].getText()=="O") {
            VitoriaDoO(0,4,8);
            return true;
        }
        if((botoes[2].getText()=="O") && (botoes[4].getText()=="O") && botoes[6].getText()=="O") {
            VitoriaDoO(2,4,6);
            return true;
        }
        // O coluna
        if((botoes[0].getText()=="O") && (botoes[3].getText()=="O") && botoes[6].getText()=="O") {
            VitoriaDoO(0,3,6);
            return true;
        }
        if((botoes[1].getText()=="O") && (botoes[4].getText()=="O") && botoes[7].getText()=="O") {
            VitoriaDoO(1,4,7);
            return true;
        }
        if((botoes[2].getText()=="O") && (botoes[5].getText()=="O") && botoes[8].getText()=="O") {
            VitoriaDoO(2,5,8);
            return true;
        }

        return false;
    }

    public void VitoriaDoX(int posicaoA, int posicaoB, int posicaoC) {
        botoes[posicaoA].setBackground(Color.green);
        botoes[posicaoB].setBackground(Color.green);
        botoes[posicaoC].setBackground(Color.green);

        for(int i = 0; i < 9; i++) {
            botoes[i].setEnabled(false);
        }
        texto.setText("X Venceu");
        reiniciar("X", "O", posicaoA, posicaoB, posicaoC);
    }

    public void VitoriaDoO(int posicaoA, int posicaoB, int posicaoC) {
        botoes[posicaoA].setBackground(Color.green);
        botoes[posicaoB].setBackground(Color.green);
        botoes[posicaoC].setBackground(Color.green);

        for(int i = 0; i < 9; i++) {
            botoes[i].setEnabled(false);
        }
        texto.setText("O Venceu");
        reiniciar("0","X",posicaoA, posicaoB, posicaoC);
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
            for(int i = 0; i < 9; i++) {
                botoes[i].setEnabled(true);
                botoes[i].setText("");
            }
            botoes[posicaoA].setBackground(new Color(75,0,130));
            botoes[posicaoB].setBackground(new Color(75,0,130));
            botoes[posicaoC].setBackground(new Color(75,0,130));
            texto.setText("Turno :" + perdedor);

        }

    }




}
