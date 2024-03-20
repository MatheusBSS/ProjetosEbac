import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class Main extends JFrame {
    private GerenciadorDeUsuarios gerenciador;

    public Main() {
        super("Cadastro de Usuários");
        gerenciador = new GerenciadorDeUsuarios();

        JButton btnCadastro = new JButton("1 - Cadastro");
        JButton btnPesquisa = new JButton("2 - Pesquisa");
        JButton btnEditar = new JButton("3 - Editar");
        JButton btnExcluir = new JButton("4 - Excluir");
        JButton btnSair = new JButton("5 - Sair");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.add(btnCadastro);
        panel.add(btnPesquisa);
        panel.add(btnEditar);
        panel.add(btnExcluir);
        panel.add(btnSair);

        add(panel);

        btnCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });

        btnPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarUsuario();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirUsuario();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saindo do programa...");
                System.exit(0);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void cadastrarUsuario() {
        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField enderecoField = new JTextField();
        JTextField cidadeField = new JTextField();
        JTextField estadoField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("CPF:"));
        panel.add(cpfField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(new JLabel("Endereço:"));
        panel.add(enderecoField);
        panel.add(new JLabel("Cidade:"));
        panel.add(cidadeField);
        panel.add(new JLabel("Estado:"));
        panel.add(estadoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Usuário",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String telefone = telefoneField.getText();
            String endereco = enderecoField.getText();
            String cidade = cidadeField.getText();
            String estado = estadoField.getText();

            gerenciador.cadastrarUsuario(nome, cpf, telefone, endereco, cidade, estado);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        }
    }

    private void pesquisarUsuario() {
        JTextField cpfField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("CPF do usuário:"));
        panel.add(cpfField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Pesquisa de Usuário",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String cpf = cpfField.getText();
            Usuario usuario = gerenciador.pesquisarUsuario(cpf);
            if (usuario != null) {
                JOptionPane.showMessageDialog(null, "Nome: " + usuario.getNome() + "\nCPF: " + usuario.getCpf(), "Informações do Usuário", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
            }
        }
    }

    private void editarUsuario() {
        JTextField cpfField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("CPF do usuário a ser editado:"));
        panel.add(cpfField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edição de Usuário",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String cpf = cpfField.getText();
            Usuario usuario = gerenciador.pesquisarUsuario(cpf);
            if (usuario != null) {
                editarUsuarioPopup(usuario);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
            }
        }
    }

    private void editarUsuarioPopup(Usuario usuario) {
        JTextField nomeField = new JTextField(usuario.getNome());
        JTextField telefoneField = new JTextField(usuario.getTelefone());
        JTextField enderecoField = new JTextField(usuario.getEndereco());
        JTextField cidadeField = new JTextField(usuario.getCidade());
        JTextField estadoField = new JTextField(usuario.getEstado());

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(new JLabel("Endereço:"));
        panel.add(enderecoField);
        panel.add(new JLabel("Cidade:"));
        panel.add(cidadeField);
        panel.add(new JLabel("Estado:"));
        panel.add(estadoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edição de Usuário",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String telefone = telefoneField.getText();
            String endereco = enderecoField.getText();
            String cidade = cidadeField.getText();
            String estado = estadoField.getText();

            gerenciador.editarUsuario(usuario.getCpf(), nome, telefone, endereco, cidade, estado);
            JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
        }
    }

    private void excluirUsuario() {
        JTextField cpfField = new JTextField();
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("CPF do usuário a ser excluído:"));
        panel.add(cpfField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Exclusão de Usuário",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String cpf = cpfField.getText();
            gerenciador.excluirUsuario(cpf);
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
