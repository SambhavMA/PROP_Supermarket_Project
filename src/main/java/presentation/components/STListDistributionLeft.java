package presentation.components;

import presentation.panels.GenerateDistributionPanel;
import presentation.views.ViewPrimary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel de lista de tablas de similitudes a la izquierda
 */
public class STListDistributionLeft extends JPanel {
    private ViewPrimary viewPrimary;
    private GenerateDistributionPanel parentPanel;
    int rows;
    int columns;
    String[] columnsTitles;
    JComponent[] data;
    String emptyMessage;
    private JPanel selectedPanel;

    /**
     * Constructor de la clase
     * @param viewPrimary vista principal
     * @param parentPanel panel padre
     * @param glRow numero de filas
     * @param glColumn numero de columnas
     * @param columnsTitles titulos de las columnas
     * @param data lista de tablas de similitud disponibles
     * @param emptyMessage mensaje en caso de que no tablas de similitud
     */
    public STListDistributionLeft(ViewPrimary viewPrimary, GenerateDistributionPanel parentPanel, int glRow, int glColumn, String[] columnsTitles, JComponent[] data, String emptyMessage) {
        this.viewPrimary = viewPrimary;
        this.parentPanel = parentPanel;
        this.rows = glRow;
        this.columns = glColumn;
        this.columnsTitles = columnsTitles;
        this.data = data;
        this.emptyMessage = emptyMessage;
        this.selectedPanel = null;
        initialize();
    }

    /**
     * Inicializa los componentes del panel
     */
    private void initialize() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        if (rows == 0) {
            containerPanel.removeAll();
            JLabel emptyLabel = new JLabel(emptyMessage, SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.BOLD, 18));
            containerPanel.add(emptyLabel);
        } else {
            Dimension sizes = new Dimension(80, 25);

            // contenedores de filas
            for (int row = 0; row < rows; row++) {
                final int currentRow = row;
                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
                rowPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                JPanel stPanel = new JPanel(new GridLayout(1, columns + 1, 0, 0));
                stPanel.setBackground(Color.LIGHT_GRAY);
                stPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

                data[row].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                data[row].setPreferredSize(sizes);
                stPanel.add(new JLabel("Tabla de similitud con id: " + ((JLabel) data[row]).getText(), SwingConstants.CENTER));

                stPanel.addMouseListener(new MouseAdapter() {
                    private final int stId = Integer.parseInt(((JLabel) data[currentRow]).getText());

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (selectedPanel != null) {
                            selectedPanel.setBackground(Color.LIGHT_GRAY);
                        }

                        stPanel.setBackground(Color.YELLOW);
                        selectedPanel = stPanel;

                        parentPanel.setSelectedSTId(stId);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (selectedPanel != stPanel) {
                            stPanel.setBackground(Color.CYAN);
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (selectedPanel != stPanel) {
                            stPanel.setBackground(Color.LIGHT_GRAY);
                        }
                    }
                });

                rowPanel.add(stPanel);

                containerPanel.add(rowPanel);
            }
        }
        JScrollPane scrollPane = new JScrollPane(containerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
}

