import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaBoasVindas extends JFrame {
    
    public JanelaBoasVindas() {
        setTitle("A melhor janela de todos os tempos");
        setSize(800, 800);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));

        JLabel labelBoasVindas = new JLabel("Seja bem vindo(a)!, espero que goste da janela");
        labelBoasVindas.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnFechar = new JButton("Fechar");
        btnFechar.setPreferredSize(new Dimension(100, 30));

        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        add(labelBoasVindas);
        add(btnFechar);

        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JanelaBoasVindas();
            }
        });
    }
}