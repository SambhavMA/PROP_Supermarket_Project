package presentation.panels;

import controller.presentation.CtrlPresentation;
import presentation.components.ItemListSimilarityTables;
import presentation.components.ProductsListSTLeft;
import presentation.components.STListDistributionLeft;
import presentation.views.ViewPrimary;
import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Panel de generacion de distribucion
 */
public class GenerateDistributionPanel extends JPanel {
    private STListDistributionLeft itemListSTLeft;
    private JPanel selectOptionsPanel;
    private JPanel stPanel;
    private JPanel listsPanel;
    private JLabel title = new JLabel("Generar Distribucion", SwingConstants.CENTER);
    private JLabel idDistributionLabel = new JLabel("Id de la distribucion: ");
    private JLabel selectAlgorithmLabel = new JLabel("Algoritmo generador: ");
    private JComboBox<String> comboBoxAlgorithms = new JComboBox<>(CtrlPresentation.getInstance().getAlgorithms());
    private JButton submitButton = new JButton("Generar");

    private ViewPrimary viewPrimary;
    private Integer selectedSTId = null;
    private int distributionId;

    /**
     * Constructor de la clase
     * @param viewPrimary vista principal
     */
    public GenerateDistributionPanel(ViewPrimary viewPrimary) {
        this.viewPrimary = viewPrimary;
        this.distributionId = CtrlPresentation.getInstance().getDistributionNextId();
        initializeComponents();
    }

    /**
     * Inicializa los componentes del panel
     */
    protected void initializeComponents() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        stPanel = new JPanel(new BorderLayout(5, 5));
        stPanel.setBorder(BorderFactory.createTitledBorder("Tablas de similitudes disponibles"));

        String[] dataPresentation = CtrlPresentation.getInstance().getSimilarityTables();

        if (dataPresentation.length > 0) {
            initializeListLeft(dataPresentation);
        } else {
            initializeListLeft(new String[0]);
        }
        stPanel.add(itemListSTLeft, BorderLayout.CENTER);

        selectOptionsPanel = new JPanel(new BorderLayout(5, 5));
        selectOptionsPanel.setBorder(BorderFactory.createTitledBorder("Opciones"));

        JPanel optionsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.gridy = 0;
        optionsPanel.add(idDistributionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        JLabel idLabel = new JLabel(""+distributionId);
        optionsPanel.add(idLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        optionsPanel.add(selectAlgorithmLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        optionsPanel.add(comboBoxAlgorithms, gbc);

        selectOptionsPanel.add(optionsPanel, BorderLayout.CENTER);

        listsPanel.add(stPanel);
        listsPanel.add(selectOptionsPanel);

        JPanel buttonPanel = new JPanel();
        submitButton.setPreferredSize(new Dimension(120, 30));
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPanel.add(submitButton);

        add(title, BorderLayout.NORTH);
        add(listsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> handleSubmit());
    }

    /**
     * Maneja la generacion de la distribucion una vez se le da al boton de generar
     */
    private void handleSubmit() {
        if (selectedSTId == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, seleccione al menos una tabla de similitud antes de continuar.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            // Generate Distribution
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(
                            new JLabel(),
                            "La generacion se ejecutara en segundo plano, en breve estara lista.",
                            "Espera",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    boolean v = CtrlPresentation.getInstance().generateDistribution(selectedSTId, (String) comboBoxAlgorithms.getSelectedItem());
                    if (v) {
                        viewPrimary.transitionContentPanel(viewPrimary.getDistributionsManagePanel());
                        //For testing: System.out.println(Arrays.deepToString(CtrlPresentation.getInstance().getDistribution(distributionId)));
                    }
                }
            });
            thread.start();
        }

    }

    /**
     * Inicializa la lista de tablas de similitudes
     * @param ids lista de ids de las tablas de similitudes
     */
    private void initializeListLeft(String[] ids) {
        JComponent[] data = new JComponent[ids.length];

        for (int i = 0; i < ids.length; i++) {
            data[i] = new JLabel(ids[i], SwingConstants.CENTER);
        }

        String[] cols = CtrlPresentation.getInstance().getSimilarityTablesCols();
        itemListSTLeft = new STListDistributionLeft(viewPrimary, this, ids.length, cols.length, cols, data, "No hay tablas de similitudes disponibles");
    }

    /**
     * Devuelve el id de la tabla de similitud seleccionada
     * @return id de la tabla de similitud seleccionada
     */
    public void setSelectedSTId(Integer selectedSTId) {
        this.selectedSTId = selectedSTId;
    }

}
