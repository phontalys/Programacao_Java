import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GerenciadorGUI extends JFrame {
    private static final Color COR_PRINCIPAL = new Color(116, 100, 161); // #7464a1
    private static final Color COR_FUNDO = new Color(245, 245, 250);
    private static final Color COR_TEXTO = new Color(33, 33, 33);
    
    private GerenciadorItens gerenciador;
    
    // componentes principais
    private JTextField txtTitulo;
    private JTextArea txtDescricao;
    private JComboBox<String> cmbTipo;
    private JPanel panelCamposEspecificos;
    private JTextField txtCampoEsp1; // Autor ou Diretor
    private JTextField txtCampoEsp2; // Num Páginas ou Duração
    private JTextField txtBusca;
    private JTextArea txtResultados;
    private JLabel lblCampoEsp1;
    private JLabel lblCampoEsp2;
    
    public GerenciadorGUI() {
        gerenciador = new GerenciadorItens();
        inicializarInterface();
    }
    
    private void inicializarInterface() {
        setTitle("Sistema de Gerenciamento de Itens");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // layout principal
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(COR_FUNDO);
        
        // painel superior com título
        add(criarPainelTitulo(), BorderLayout.NORTH);
        
        // painel central com formulário e resultados
        JPanel painelCentral = new JPanel(new GridLayout(1, 2, 10, 10));
        painelCentral.setBackground(COR_FUNDO);
        painelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        painelCentral.add(criarPainelFormulario());
        painelCentral.add(criarPainelResultados());
        
        add(painelCentral, BorderLayout.CENTER);
        
        // painel inferior com botões de I/O
        add(criarPainelInferior(), BorderLayout.SOUTH);
    }
    
    private JPanel criarPainelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(COR_PRINCIPAL);
        panel.setPreferredSize(new Dimension(0, 60));
        
        JLabel lblTitulo = new JLabel("📚 Gerenciador de Itens");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        
        panel.add(lblTitulo);
        return panel;
    }
    
    private JPanel criarPainelFormulario() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_PRINCIPAL, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // título da seção
        JLabel lblSecao = new JLabel("Adicionar Novo Item");
        lblSecao.setFont(new Font("Arial", Font.BOLD, 16));
        lblSecao.setForeground(COR_PRINCIPAL);
        lblSecao.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblSecao);
        panel.add(Box.createVerticalStrut(15));
        
        // campo título
        panel.add(criarLabel("Título:"));
        txtTitulo = new JTextField();
        estilizarCampoTexto(txtTitulo);
        panel.add(txtTitulo);
        panel.add(Box.createVerticalStrut(10));
        
        // campo descriçãoo
        panel.add(criarLabel("Descrição:"));
        txtDescricao = new JTextArea(3, 20);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescricao);
        scrollDesc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        estilizarCampoTexto(txtDescricao);
        panel.add(scrollDesc);
        panel.add(Box.createVerticalStrut(10));
        
        // combox Tipo
        panel.add(criarLabel("Tipo:"));
        cmbTipo = new JComboBox<>(new String[]{"Livro", "Filme"});
        cmbTipo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        cmbTipo.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbTipo.addActionListener(e -> atualizarCamposEspecificos());
        panel.add(cmbTipo);
        panel.add(Box.createVerticalStrut(10));
        
        // painel para campos especificos
        panelCamposEspecificos = new JPanel();
        panelCamposEspecificos.setLayout(new BoxLayout(panelCamposEspecificos, BoxLayout.Y_AXIS));
        panelCamposEspecificos.setBackground(Color.WHITE);
        panelCamposEspecificos.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        lblCampoEsp1 = criarLabel("Autor:");
        txtCampoEsp1 = new JTextField();
        estilizarCampoTexto(txtCampoEsp1);
        
        lblCampoEsp2 = criarLabel("Número de Páginas:");
        txtCampoEsp2 = new JTextField();
        estilizarCampoTexto(txtCampoEsp2);
        
        panelCamposEspecificos.add(lblCampoEsp1);
        panelCamposEspecificos.add(txtCampoEsp1);
        panelCamposEspecificos.add(Box.createVerticalStrut(10));
        panelCamposEspecificos.add(lblCampoEsp2);
        panelCamposEspecificos.add(txtCampoEsp2);
        
        panel.add(panelCamposEspecificos);
        panel.add(Box.createVerticalStrut(15));
        
        // botoes
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelBotoes.setBackground(Color.WHITE);
        panelBotoes.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton btnAdicionar = criarBotao("Adicionar", true);
        btnAdicionar.addActionListener(e -> adicionarItem());
        
        JButton btnListar = criarBotao("Listar", false);
        btnListar.addActionListener(e -> listarItens());
        
        panelBotoes.add(btnAdicionar);
        panelBotoes.add(btnListar);
        
        panel.add(panelBotoes);
        
        return panel;
    }
    
    private JPanel criarPainelResultados() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COR_PRINCIPAL, 2),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // titulo da seção
        JLabel lblSecao = new JLabel("Resultados");
        lblSecao.setFont(new Font("Arial", Font.BOLD, 16));
        lblSecao.setForeground(COR_PRINCIPAL);
        panel.add(lblSecao, BorderLayout.NORTH);
        
        // painel de busca
        JPanel panelBusca = new JPanel(new BorderLayout(5, 0));
        panelBusca.setBackground(Color.WHITE);
        
        txtBusca = new JTextField();
        estilizarCampoTexto(txtBusca);
        
        JButton btnFiltrar = criarBotao("Filtrar", true);
        btnFiltrar.setPreferredSize(new Dimension(100, 35));
        btnFiltrar.addActionListener(e -> filtrarItens());
        
        panelBusca.add(txtBusca, BorderLayout.CENTER);
        panelBusca.add(btnFiltrar, BorderLayout.EAST);
        
        panel.add(panelBusca, BorderLayout.NORTH);
        
        // area de resultados
        txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtResultados.setLineWrap(true);
        txtResultados.setWrapStyleWord(true);
        
        JScrollPane scrollResultados = new JScrollPane(txtResultados);
        panel.add(scrollResultados, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel criarPainelInferior() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(COR_FUNDO);
        
        JButton btnExportar = criarBotao("📤 Exportar Dados", true);
        btnExportar.addActionListener(e -> exportarDados());
        
        JButton btnImportar = criarBotao("📥 Importar Dados", false);
        btnImportar.addActionListener(e -> importarDados());
        
        panel.add(btnExportar);
        panel.add(btnImportar);
        
        return panel;
    }
    
    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(COR_TEXTO);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    private void estilizarCampoTexto(JTextComponent campo) {
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            new EmptyBorder(5, 8, 5, 8)
        ));
        if (campo instanceof JTextField) {
            ((JTextField) campo).setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        }
    }
    
    private JButton criarBotao(String texto, boolean principal) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 13));
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (principal) {
            botao.setBackground(COR_PRINCIPAL);
            botao.setForeground(Color.WHITE);
        } else {
            botao.setBackground(Color.WHITE);
            botao.setForeground(COR_PRINCIPAL);
            botao.setBorder(BorderFactory.createLineBorder(COR_PRINCIPAL, 2));
        }
        
        return botao;
    }
    
    private void atualizarCamposEspecificos() {
        String tipo = (String) cmbTipo.getSelectedItem();
        
        if ("Livro".equals(tipo)) {
            lblCampoEsp1.setText("Autor:");
            lblCampoEsp2.setText("Número de Páginas:");
        } else {
            lblCampoEsp1.setText("Diretor:");
            lblCampoEsp2.setText("Duração (minutos):");
        }
        
        txtCampoEsp1.setText("");
        txtCampoEsp2.setText("");
    }
    
    private void adicionarItem() {
        try {
            String titulo = txtTitulo.getText().trim();
            String descricao = txtDescricao.getText().trim();
            String tipo = (String) cmbTipo.getSelectedItem();
            String campoEsp1 = txtCampoEsp1.getText().trim();
            String campoEsp2 = txtCampoEsp2.getText().trim();
            
            // validações
            if (titulo.isEmpty()) {
                throw new TituloVazioException("O título não pode estar vazio");
            }
            
            if (descricao.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "A descrição não pode estar vazia", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (campoEsp1.isEmpty() || campoEsp2.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Todos os campos devem ser preenchidos", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            LocalDate dataCadastro = LocalDate.now();
            Item item;
            
            if ("Livro".equals(tipo)) {
                int numPaginas = Integer.parseInt(campoEsp2);
                if (numPaginas <= 0) {
                    throw new IllegalArgumentException("Número de páginas deve ser positivo");
                }
                item = new Livro(titulo, descricao, dataCadastro, campoEsp1, numPaginas);
            } else {
                int duracao = Integer.parseInt(campoEsp2);
                if (duracao <= 0) {
                    throw new IllegalArgumentException("Duração deve ser positiva");
                }
                item = new Filme(titulo, descricao, dataCadastro, campoEsp1, duracao);
            }
            
            gerenciador.adicionarItem(item);
            
            JOptionPane.showMessageDialog(this, 
                "Item adicionado com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
            
            limparFormulario();
            listarItens();
            
        } catch (TituloVazioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Número de páginas/duração deve ser um valor numérico válido", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao adicionar item: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void listarItens() {
        txtResultados.setText("");
        var itens = gerenciador.listarTodos();
        
        if (itens.isEmpty()) {
            txtResultados.setText("Nenhum item cadastrado.");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(50)).append("\n");
        sb.append("LISTA DE ITENS CADASTRADOS\n");
        sb.append("=".repeat(50)).append("\n\n");
        
        for (Item item : itens) {
            sb.append(item.exibirDetalhes()).append("\n");
        }
        
        var stats = gerenciador.contarPorTipo();
        sb.append("=".repeat(50)).append("\n");
        sb.append(String.format("Total: %d | Livros: %d | Filmes: %d\n", 
            stats.get("Total"), stats.get("Livros"), stats.get("Filmes")));
        sb.append("=".repeat(50));
        
        txtResultados.setText(sb.toString());
        txtResultados.setCaretPosition(0);
    }
    
    private void filtrarItens() {
        String busca = txtBusca.getText().trim();
        
        if (busca.isEmpty()) {
            listarItens();
            return;
        }
        
        try {
            var resultados = gerenciador.buscarPorTitulo(busca);
            
            StringBuilder sb = new StringBuilder();
            sb.append("=".repeat(50)).append("\n");
            sb.append(String.format("RESULTADOS DA BUSCA: '%s'\n", busca));
            sb.append("=".repeat(50)).append("\n\n");
            
            for (Item item : resultados) {
                sb.append(item.exibirDetalhes()).append("\n");
            }
            
            sb.append("=".repeat(50)).append("\n");
            sb.append(String.format("Total de resultados: %d\n", resultados.size()));
            sb.append("=".repeat(50));
            
            txtResultados.setText(sb.toString());
            txtResultados.setCaretPosition(0);
            
        } catch (ItemNaoEncontradoException e) {
            txtResultados.setText("Nenhum item encontrado com o termo: '" + busca + "'");
        }
    }
    
    private void exportarDados() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exportar Dados");
        fileChooser.setSelectedFile(new java.io.File("acervo.txt"));
        
        int resultado = fileChooser.showSaveDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                String caminho = fileChooser.getSelectedFile().getAbsolutePath();
                gerenciador.exportarParaArquivo(caminho);
                
                JOptionPane.showMessageDialog(this, 
                    "Dados exportados com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
            } catch (ArquivoException e) {
                JOptionPane.showMessageDialog(this, 
                    "Erro ao exportar: " + e.getMessage(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void importarDados() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Importar Dados");
        
        int resultado = fileChooser.showOpenDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                String caminho = fileChooser.getSelectedFile().getAbsolutePath();
                gerenciador.importarDeArquivo(caminho);
                
                JOptionPane.showMessageDialog(this, 
                    "Dados importados com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                listarItens();
                
            } catch (ArquivoException e) {
                JOptionPane.showMessageDialog(this, 
                    "Erro ao importar: " + e.getMessage(), 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void limparFormulario() {
        txtTitulo.setText("");
        txtDescricao.setText("");
        txtCampoEsp1.setText("");
        txtCampoEsp2.setText("");
        cmbTipo.setSelectedIndex(0);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            GerenciadorGUI gui = new GerenciadorGUI();
            gui.setVisible(true);
        });
    }
}