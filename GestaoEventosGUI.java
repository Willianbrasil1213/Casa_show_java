
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;
import DAO.EventosoperacionaisDAO;
import Metodos.Cliente;
import Metodos.CriarEventos;
import Metodos.EventosLista;
import Metodos.Verificacoes;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;

public class GestaoEventosGUI {
    static String nomeUsuarioLogado = "";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> telaLogin());
    }

    private static void centralizarTela(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }

    private static void telaLogin() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel(new GridLayout(6, 4));

        JLabel emailLabel = new JLabel("E-mail:");
        JTextField emailField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        JPasswordField senhaField = new JPasswordField();

        JButton loginButton = new JButton("Entrar!");

        JButton cadastroButton = new JButton("Cadastre-se.");

        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(senhaLabel);
        loginPanel.add(senhaField);
        loginPanel.add(loginButton);
        loginPanel.add(cadastroButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());

                EventosoperacionaisDAO dbConnection = new EventosoperacionaisDAO();


                if (dbConnection.verificarLogin(email, senha)) {
                    nomeUsuarioLogado = EventosoperacionaisDAO.buscarCliente(email,senha).getNome_cliente();
                    loginFrame.dispose();
                    JOptionPane.showMessageDialog(loginFrame, "Login realizado com sucesso!");
                    telaInicial();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Erro: E-mail ou senha incorretos. Tente novamente.");
                    emailField.setText("");
                    senhaField.setText("");
                }

            }
        });

        cadastroButton.addActionListener(e -> {
            loginFrame.dispose();
            telaCadastro();
        });

        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginFrame.setSize(450, 250);
        centralizarTela(loginFrame);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }

    private static void telaCadastro() {
        JFrame cadastroFrame = new JFrame("Cadastro");
        cadastroFrame.setLayout(new BorderLayout());

        JPanel cadastroPanel = new JPanel(new GridLayout(5, 2));

        JLabel nomeLabel = new JLabel("Nome e Sobrenome:");
        JTextField nomeField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel telefoneLabel = new JLabel("Número de Telefone:");
        MaskFormatter telefoneMask = null;
        try {
            telefoneMask = new MaskFormatter("(##) #####-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField telefoneFieldFormatted = new JFormattedTextField(telefoneMask);
        telefoneFieldFormatted.setColumns(10);
        JLabel senhaLabel = new JLabel("Senha:");
        JTextField senhaField = new JPasswordField();
        JButton voltarButton = new JButton("Voltar.");

        cadastroPanel.add(nomeLabel);
        cadastroPanel.add(nomeField);
        cadastroPanel.add(emailLabel);
        cadastroPanel.add(emailField);
        cadastroPanel.add(telefoneLabel);
        cadastroPanel.add(telefoneFieldFormatted);
        cadastroPanel.add(senhaLabel);
        cadastroPanel.add(senhaField);
        cadastroPanel.add(voltarButton);

        JButton cadastroButton = new JButton("Cadastrar!");
        cadastroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String email = emailField.getText();
                String telefone = telefoneFieldFormatted.getText().replaceAll("[^0-9]", "");
                String senha = senhaField.getText();


                if (!Verificacoes.testaEmail(email)) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Email deve ser do tipo @gmail.com.",
                            "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                            return;
                    
                }

                if (!Verificacoes.testaTelefone(telefone)) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Telefone inválido. Use o formato (DD) 9xxxx-xxxx.",
                            "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                                           return;
                }

                if (!Verificacoes.testaNome(nome)) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Insira um nome valido, por favor.",
                            "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                if (senha.isBlank()) {
                    JOptionPane.showMessageDialog(cadastroFrame, "Por favor, preencha sua senha.",
                            "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                            return;
                } 

//adicionar aqui a verificacao de email existente
                
                String NOME_CLIENTE = nomeField.getText();
                String EMAIL_CLIENTE = emailField.getText();
                String TELEFONE = telefoneFieldFormatted.getText();
                String SENHA_CLIENTE = senhaField.getText();

                Cliente cadastraCliente = new Cliente();

                cadastraCliente.setNome_cliente(NOME_CLIENTE);
                cadastraCliente.setEmail_cliente(EMAIL_CLIENTE);
                cadastraCliente.setNumero_tel_cliente(TELEFONE);
                cadastraCliente.setSenha_cliente(SENHA_CLIENTE);

                EventosoperacionaisDAO.cadastraCliente(cadastraCliente);
                JOptionPane.showMessageDialog(cadastroFrame, "Cadastro realizado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                cadastroFrame.dispose();
                telaLogin();

            }
        });
        cadastroPanel.add(cadastroButton);

        voltarButton.addActionListener(e -> {
            cadastroFrame.dispose();
            telaLogin();
        });


        cadastroFrame.add(cadastroPanel, BorderLayout.CENTER);
        cadastroFrame.setSize(400, 250);
        centralizarTela(cadastroFrame);
        cadastroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadastroFrame.setVisible(true);
    }

    private static void telaInicial() {
        JFrame inicialFrame = new JFrame("Página Principal");
        inicialFrame.setLayout(new BorderLayout());

        JLabel nomeUsuarioLabel = new JLabel("Bem-vindo, " + nomeUsuarioLogado + "!");
        nomeUsuarioLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inicialFrame.add(nomeUsuarioLabel, BorderLayout.NORTH);

        JPanel inicialPanel = new JPanel(new GridLayout(2, 2));
        JButton criarEventoButton = new JButton("Criar Evento");
        JButton meusEventosButton = new JButton("Meus Eventos");
        JButton removerEventoButton = new JButton("Remover Evento");

        inicialPanel.add(criarEventoButton);
        inicialPanel.add(meusEventosButton);
        inicialPanel.add(removerEventoButton);

       

        criarEventoButton.addActionListener(e -> {
            inicialFrame.dispose();
            telaCadastrarEvento();
        });

        meusEventosButton.addActionListener(e -> {
            exibirListaEventos();
        });

 

        removerEventoButton.addActionListener(e -> {
            telaRemoverEvento();
        });

        inicialFrame.add(inicialPanel, BorderLayout.CENTER);
        inicialFrame.setSize(600, 400);
        centralizarTela(inicialFrame);
        inicialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicialFrame.setVisible(true);
    }

       private static void telaCadastrarEvento() {
        JFrame cadastroEventoFrame = new JFrame("Cadastro de Evento");
        JPanel cadastroPanel = new JPanel(new GridLayout(13, 5));

        JLabel nomeEventoLabel = new JLabel("Nome do Evento:");
        JTextField nomeEventoField = new JTextField();

        JLabel dataLabel = new JLabel("Data: (dd/MM/2023)");
        MaskFormatter dataMask = null;
        try {
            dataMask = new MaskFormatter("##/##");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JFormattedTextField dataField = new JFormattedTextField(dataMask);
        dataField.setColumns(5);

        JLabel extrasLabel = new JLabel("Extras:");
        JTextField extrasField = new JTextField();

        JLabel ingressoConviteLabel = new JLabel("Ingresso ou Convite:");
        String[] ingressoConviteOptions = { "Ingresso", "Convite" };
        JComboBox<String> ingressoConviteComboBox = new JComboBox<>(ingressoConviteOptions);

        JLabel precoIngressoLabel = new JLabel("Preço do Ingresso:");
        JTextField precoIngressoField = new JTextField();

        JLabel numConvidadosLabel = new JLabel("Número de Convidados:");
        JTextField numConvidadosField = new JTextField();

        JLabel atracaoLabel = new JLabel("Atração:");
        String[] atracaoOptions = { "DJ", "Banda" };
        JComboBox<String> atracaoComboBox = new JComboBox<>(atracaoOptions);

        JLabel categoriaLabel = new JLabel("Categoria:");
        String[] categorias = { "INFANTIL", "ADOLESCENTE", "ADULTO" };
        JComboBox<String> categoriaComboBox = new JComboBox<>(categorias);

        JLabel tipoEventoLabel = new JLabel("Tipo de Evento:");
        JComboBox<String> tipoEventoComboBox = new JComboBox<>();
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton criarPDFButton = new JButton("Criar PDF"); // Botão para criar o PDF

        cadastroPanel.add(nomeEventoLabel);
        cadastroPanel.add(nomeEventoField);
        cadastroPanel.add(dataLabel);
        cadastroPanel.add(dataField);
        cadastroPanel.add(extrasLabel);
        cadastroPanel.add(extrasField);
        cadastroPanel.add(ingressoConviteLabel);
        cadastroPanel.add(ingressoConviteComboBox);
        cadastroPanel.add(precoIngressoLabel);
        cadastroPanel.add(precoIngressoField);
        cadastroPanel.add(numConvidadosLabel);
        cadastroPanel.add(numConvidadosField);
        cadastroPanel.add(atracaoLabel);
        cadastroPanel.add(atracaoComboBox);
        cadastroPanel.add(categoriaLabel);
        cadastroPanel.add(categoriaComboBox);
        cadastroPanel.add(tipoEventoLabel);
        cadastroPanel.add(tipoEventoComboBox);
        cadastroPanel.add(new JLabel());
        cadastroPanel.add(cadastrarButton);
        cadastroPanel.add(criarPDFButton); // Adicione o botão "Criar PDF" à interface

        categoriaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoriaSelecionada = (String) categoriaComboBox.getSelectedItem();
                DefaultComboBoxModel<String> tipoEventoModel = new DefaultComboBoxModel<>();

                switch (categoriaSelecionada) {
                    case "INFANTIL":
                        tipoEventoModel.addElement("CHA DE BEBE");
                        tipoEventoModel.addElement("ANIVERSARIO");
                        break;
                    case "ADOLESCENTE":
                        tipoEventoModel.addElement("15 ANOS");
                        tipoEventoModel.addElement("BALADA");
                        break;
                    case "ADULTO":
                        tipoEventoModel.addElement("CONFERENCIAS");
                        tipoEventoModel.addElement("WORKSHOP");
                        tipoEventoModel.addElement("CASAMENTO");
                        break;
                    default:
                        tipoEventoModel.addElement("Selecione");
                        break;
                }

                tipoEventoComboBox.setModel(tipoEventoModel);
            }
        });

        ingressoConviteComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String escolha = (String) ingressoConviteComboBox.getSelectedItem();

                // Exiba ou oculte o campo Preço do Ingresso com base na seleção
                if ("Ingresso".equals(escolha)) {
                    precoIngressoField.setEnabled(true);
                    numConvidadosField.setEnabled(false);
                } else if ("Convite".equals(escolha)) {
                    precoIngressoField.setEnabled(false);
                    numConvidadosField.setEnabled(true);
                }
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NOME_EVENTO = nomeEventoField.getText();
                String DATA_EVENTO = dataField.getText();
                String EXTRA_EVENTO = extrasField.getText();
                String ingressoOuConvite = (String) ingressoConviteComboBox.getSelectedItem();
                String precoIngresso = precoIngressoField.getText();
                String numConvidados = numConvidadosField.getText();
                String atracao = (String) atracaoComboBox.getSelectedItem();
                String categoriaSelecionada = (String) categoriaComboBox.getSelectedItem();
                String opcaoSelecionada = (String) tipoEventoComboBox.getSelectedItem();

                if (Verificacoes.testaData(DATA_EVENTO) && Verificacoes.testaNomeEvento(NOME_EVENTO)) {
                    CriarEventos cadastraEvento = new CriarEventos();
                    cadastraEvento.setNome_evento(NOME_EVENTO);
                    cadastraEvento.setData(DATA_EVENTO);
                    cadastraEvento.setExtras(EXTRA_EVENTO);
                    cadastraEvento.setPreco_ingresso(precoIngresso);
                    cadastraEvento.setConvidados(numConvidados);
                    cadastraEvento.setAtracao(atracao);
                    cadastraEvento.setClassificacao(categoriaSelecionada);
                    cadastraEvento.setTipo_evento(opcaoSelecionada);
                    cadastraEvento.setIngressoOuConvite(ingressoOuConvite);

                    if (ingressoOuConvite.equals("ingresso")) {
                        cadastraEvento.setAcesso("Publico");
                    } else {
                        cadastraEvento.setAcesso("Privado");
                    }

                    EventosoperacionaisDAO.cadastraevento(cadastraEvento);
                }

                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                            cadastroEventoFrame.dispose();
            telaInicial();
            }
        });

        criarPDFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chame a função createPDF para criar o PDF com base nas informações de extras e DJ/Banda
                createPDF(extrasField.getText(), atracaoComboBox.getSelectedItem().toString());
            }
        });

        cadastroEventoFrame.setSize(700, 350);
        centralizarTela(cadastroEventoFrame);

        cadastroEventoFrame.add(new JLabel());
        cadastroEventoFrame.add(cadastroPanel, BorderLayout.CENTER);

        cadastroEventoFrame.setVisible(true);
        cadastroEventoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void createPDF(String extras, String atracao) {
        Document document = new Document();

        try {
            String baseFileName = "Orcamento_Locacao_Espaco";
            String fileExtension = ".pdf";
            String fileName = baseFileName + fileExtension;

            int counter = 1;
            while (new File(fileName).exists()) {
                fileName = baseFileName + "(" + counter + ")" + fileExtension;
                counter++;
            }

            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Construa o texto do PDF aqui usando as informações passadas como parâmetros
            String orcamentoText = "Prezado(a) Cliente,\n\n" +
                    "É com grande entusiasmo que apresentamos nosso orçamento completo para a locação do nosso espaço, oferecendo um ambiente excepcional para tornar seu evento verdadeiramente memorável. Abaixo, detalhamos os serviços inclusos nos custos fixos, juntamente com os respectivos valores, e algumas comodidades adicionais.\n\n" +
                    "1. **Locação do Espaço (Incluindo):**\n" +
                    "   - Salão Principal: $800\n" +
                    "   - Área Externa: $400\n" +
                    "   - Mesas e Cadeiras: $580\n" +
                    "   - Decoração Personalizada: $300\n" +
                    "   - Sistema de Som e Iluminação: $250\n" +
                    "   - Estacionamento: $400\n" +
                    "   - Coordenador de Eventos: $350\n" +
                    "   - Equipe de Serviço: $920\n" +
                    "   - Taxa de Limpeza: $340\n\n" +
                    "*Total Atual (Itens Fixos):* $4,340\n\n" +
                    "2. **Serviços Opcionais (A serem discutidos e personalizados):**\n\n" +
                    "Extras: " + extras + "\n"; // Adicione "Extras" ao texto

            // Adicione opções artísticas ao texto
            orcamentoText += "Opções Artísticas Selecionadas: ";
            if (atracao.equals("DJ")) {
                orcamentoText += "DJ";
            }
            if (atracao.equals("Banda")) {
                orcamentoText += "Banda";
            }
            orcamentoText += "\n\n";


            orcamentoText += "Estamos à disposição para ajustes e para discutir todos os detalhes adicionais. Agradecemos pela confiança e pela oportunidade de fazer do seu evento um momento verdadeiramente especial.\n\n" +
                    "Atenciosamente,\n\n" +
                    "Equipe do Finaceiro.";

            document.add(new Paragraph(orcamentoText));

            document.close();
            JOptionPane.showMessageDialog(null, "Orçamento criado com sucesso! Verifique o arquivo '" + fileName + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   private static void telaRemoverEvento() {
    JFrame removerEventoFrame = new JFrame("Remover Evento");
    removerEventoFrame.setLayout(new BorderLayout());

    JPanel removerEventoPanel = new JPanel(new GridLayout(2, 2));

    JLabel idEventoLabel = new JLabel("ID do Evento:");
    JTextField idEventoField = new JTextField();
    JButton removerButton = new JButton("Remover");

    removerEventoPanel.add(idEventoLabel);
    removerEventoPanel.add(idEventoField);
    removerEventoPanel.add(new JLabel()); 
    removerEventoPanel.add(removerButton);

    removerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int idEvento = Integer.parseInt(idEventoField.getText());

            if (EventosoperacionaisDAO.removerEvento(idEvento)) {
                JOptionPane.showMessageDialog(removerEventoFrame, "Evento removido com sucesso.");
            } else {
                JOptionPane.showMessageDialog(removerEventoFrame, "Erro ao remover o evento.");
            }

            idEventoField.setText(""); 
        }
    });

    removerEventoFrame.add(removerEventoPanel, BorderLayout.CENTER);
    removerEventoFrame.setSize(300, 150);
    centralizarTela(removerEventoFrame);
    removerEventoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    removerEventoFrame.setVisible(true);
}

private static void exibirListaEventos() {
    JFrame frame = new JFrame("Lista de Eventos");
    frame.setLayout(new BorderLayout());

    ArrayList<CriarEventos> eventos = EventosoperacionaisDAO.listarEventos();

    EventosLista tableModel = new EventosLista(eventos);
    JTable table = new JTable(tableModel);

    JScrollPane scrollPane = new JScrollPane(table);
    frame.add(scrollPane, BorderLayout.CENTER);

    JButton backButton = new JButton("Voltar à Tela Inicial");

    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); 
            telaInicial();
        }
    });

    frame.add(backButton, BorderLayout.NORTH);
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}
    
}


    



    
    
