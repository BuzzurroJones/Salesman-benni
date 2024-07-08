import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GridDisplay {
    private ArrayList<ArrayList<Cell>> grid;
    private int rows;
    private int cols;
    private ArrayList<int[]> sellers;
    private HashMap<Integer, Color> playerColors;
    private JPanel gridPanel;
    private JTable playerTable;
    private JFrame frame;

    public GridDisplay(ArrayList<ArrayList<Cell>> grid, ArrayList<int[]> sellers, int rows, int cols) {
        this.grid = grid;
        this.rows = rows;
        this.cols = cols;
        this.sellers = sellers;
        initializePlayerColors();
    }

    private void initializePlayerColors() {
        playerColors = new HashMap<>();
        playerColors.put(0, Color.BLUE);
        playerColors.put(1, Color.RED);
        playerColors.put(2, Color.GREEN);
        playerColors.put(3, Color.YELLOW);
        playerColors.put(4, Color.BLACK);
        playerColors.put(5, Color.PINK);
        playerColors.put(6, Color.CYAN);
        playerColors.put(7, Color.MAGENTA);
        playerColors.put(8, Color.GRAY);
        playerColors.put(9, Color.DARK_GRAY);
    }

    public void createAndShowGUI() {
        frame = new JFrame("Grid Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        gridPanel = new JPanel(new GridLayout(rows, cols));
        refreshGrid();

        frame.add(gridPanel, BorderLayout.CENTER);

        // Add player positions table
        String[] columnNames = { "Player", "Row", "Column" };
        Object[][] data = new Object[sellers.size()][3];
        for (int i = 0; i < sellers.size(); i++) {
            data[i][0] = "Player " + (i + 1);
            data[i][1] = sellers.get(i)[0];
            data[i][2] = sellers.get(i)[1];
        }
        playerTable = new JTable(new DefaultTableModel(data, columnNames)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane tableScrollPane = new JScrollPane(playerTable);
        tableScrollPane.setPreferredSize(new Dimension(200, 150)); // Set preferred size for table

        frame.add(tableScrollPane, BorderLayout.SOUTH);

        frame.setSize(800, 600); // Adjust the size as needed
        frame.setVisible(true);
    }

    private boolean isPlayerCell(int row, int col) {
        for (int[] position : sellers) {
            if (position[0] == row && position[1] == col) {
                return true;
            }
        }
        return false;
    }

    private Color getColorForOwner(int owner) {
        if (owner == sellers.size()) {
            return Color.WHITE;
        }
        return playerColors.getOrDefault(owner, Color.GRAY); // Fallback color
    }

    public void refresh(ArrayList<int[]> newSellers) {
        this.sellers = newSellers;
        refreshGrid();

        // Update player positions table
        DefaultTableModel model = (DefaultTableModel) playerTable.getModel();
        model.setRowCount(0); // Clear previous data
        for (int i = 0; i < sellers.size(); i++) {
            model.addRow(new Object[] { "Player " + (i + 1), sellers.get(i)[0], sellers.get(i)[1] });
        }
    }

    private void refreshGrid() {
        gridPanel.removeAll();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBackground(getColorForOwner(grid.get(i).get(j).owner));
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                if (isPlayerCell(i, j)) {
                    JLabel label = new JLabel("P");
                    label.setFont(new Font("Arial", Font.BOLD, 20)); // Make the "P" bigger
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    label.setVerticalAlignment(SwingConstants.CENTER);
                    cellPanel.setLayout(new BorderLayout());
                    cellPanel.add(label, BorderLayout.CENTER);
                }

                gridPanel.add(cellPanel);
            }
        }

        gridPanel.revalidate();
        gridPanel.repaint();
    }
}
