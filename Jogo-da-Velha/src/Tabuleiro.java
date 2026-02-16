import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Tabuleiro {
    JFrame janela = new JFrame();
    JPanel titulo_painel = new JPanel();
    JPanel botao_painel = new JPanel();
    JLabel texto = new JLabel();
    JButton[] botoes = new JButton[9];
    boolean turno_do_jogador1;

    Tabuleiro(ActionListener listener) {
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
            botoes[i].addActionListener(listener);
            botoes[i].setEnabled(false);
        }


        botao_painel.setLayout(new GridLayout(3,3));
        botao_painel.setBackground(new Color(150,150,150));
        janela.add(botao_painel);
    }

    public String getTexTBotao(int posicao) {
        return botoes[posicao].getText();
    }

    public JButton getBotao(int posicao) {
        return botoes[posicao];
    }

    public void setTexto(String texto) {
        this.texto.setText(texto);
    }

    public void textoAumentarFonte() {
        texto.setFont(new Font("PxPlus IBM VGA",Font.BOLD,60));
    }
}
