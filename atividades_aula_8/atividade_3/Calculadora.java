import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {
    
    private JTextField txtNumero1;
    private JTextField txtNumero2;
    private JComboBox<String> cmbOperacao;
    private JCheckBox chkExibirPopup;
    private JButton btnCalcular;
    private JLabel lblResultado;
    
    public Calculadora() {
        setTitle("Calculadora");
        
        setSize(400, 300);
        
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblNumero1 = new JLabel("Número 1:");
        lblNumero1.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        panel.add(lblNumero1, gbc);
        
        txtNumero1 = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        panel.add(txtNumero1, gbc);
        
        JLabel lblNumero2 = new JLabel("Número 2:");
        lblNumero2.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        panel.add(lblNumero2, gbc);
        
        txtNumero2 = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        panel.add(txtNumero2, gbc);
    
        JLabel lblOperacao = new JLabel("Operação:");
        lblOperacao.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        panel.add(lblOperacao, gbc);
        
        String[] operacoes = {"Somar", "Subtrair", "Multiplicar", "Dividir"};
        cmbOperacao = new JComboBox<>(operacoes);
        cmbOperacao.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        panel.add(cmbOperacao, gbc);
  
        chkExibirPopup = new JCheckBox("Exibir resultado em popup (JOptionPane)");
        chkExibirPopup.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 8, 8, 8);
        panel.add(chkExibirPopup, gbc);

        btnCalcular = new JButton("Calcular");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 14));
        btnCalcular.setPreferredSize(new Dimension(120, 35));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(btnCalcular, gbc);
        
        lblResultado = new JLabel("");
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));
        lblResultado.setForeground(new Color(0, 100, 0));
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(lblResultado, gbc);

        btnCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
        
        add(panel);
        setVisible(true);
    }
    
    private void calcular() {
        try {
            double numero1 = Double.parseDouble(txtNumero1.getText());
            double numero2 = Double.parseDouble(txtNumero2.getText());
            
            String operacao = (String) cmbOperacao.getSelectedItem();
            double resultado = 0;
            String simbolo = "";
            
            switch (operacao) {
                case "Somar":
                    resultado = numero1 + numero2;
                    simbolo = "+";
                    break;
                case "Subtrair":
                    resultado = numero1 - numero2;
                    simbolo = "-";
                    break;
                case "Multiplicar":
                    resultado = numero1 * numero2;
                    simbolo = "×";
                    break;
                case "Dividir":
                    if (numero2 == 0) {
                        JOptionPane.showMessageDialog(
                            this,
                            "Não é possível dividir por zero!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    resultado = numero1 / numero2;
                    simbolo = "÷";
                    break;
            }
            
            String mensagem = String.format("%.2f %s %.2f = %.2f", 
                numero1, simbolo, numero2, resultado);

            if (chkExibirPopup.isSelected()) {
                JOptionPane.showMessageDialog(
                    this,
                    mensagem,
                    "Resultado",
                    JOptionPane.INFORMATION_MESSAGE
                );
                lblResultado.setText("");
            } else {
                lblResultado.setText("Resultado: " + mensagem);
            }
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Por favor, digite números válidos!",
                "Erro de Entrada",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculadora();
            }
        });
    }
}