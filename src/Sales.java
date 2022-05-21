import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class Sales implements ActionListener {
    private Mange_s mange_s = new Mange_s();
    JFrame Sales;
    DecimalFormat DecimalFormat = new DecimalFormat("#,###,###,###.#");
    Container c;
    JPanel panel, panelSaleList, panelTotal;
    JScrollPane scrollPanel;
    JLabel label;
    JTextField textField, date, total;
    JButton btn_close, btn_search;
    Font font = new Font("Arial", Font.BOLD, 20);
    Font font_textField = new Font("Arial", Font.BOLD, 18);;

    public void start() {
        this.Sales = new JFrame("Sale Management System");
        this.c = this.Sales.getContentPane();
        this.c.setLayout(new FlowLayout());

        /// จัดการสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 50));
        this.panel.setBackground(Color.decode("#ffb142"));
        this.panel.setLayout(new GridBagLayout());
        this.label = new JLabel("Manage Sales", SwingConstants.CENTER);
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.panel.add(this.label);
        this.Sales.add(this.panel);

        /// ปิดวินโดว์
        this.panel = new JPanel();
        this.btn_close = new JButton("X");
        this.btn_close.setFont(this.font_textField);
        this.btn_close.setPreferredSize(new Dimension(70, 50));
        this.btn_close.setBackground(Color.red);
        this.btn_close.setOpaque(true);
        this.btn_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.btn_close.setBorderPainted(false);
        this.btn_close.setForeground(Color.white);
        this.btn_close.setToolTipText("click to close");
        this.btn_close.addActionListener(this);
        this.panel.add(this.btn_close);
        this.Sales.add(this.panel);
        this.border();
        this.newLine(10);

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 50));
        this.panel.setLayout(new GridBagLayout());
        this.label = new JLabel("Sales Summary", SwingConstants.CENTER);
        this.label.setFont(this.font);
        this.label.setForeground(Color.BLACK);
        this.panel.add(this.label);
        this.Sales.add(this.panel);

        this.newLine(10);
        /// รายการสินค้าในคลัง

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.label = new JLabel("Enter Date ex. 01/01/2560");
        this.label.setFont(this.font);
        this.label.setForeground(Color.BLACK);
        this.panel.add(this.label);
        this.date = new JTextField(10);
        this.date.setFont(this.font_textField);
        this.date.setBorder(BorderFactory.createLineBorder(Color.black));
        this.date.setBackground(Color.decode("#f2f2f2"));
        this.date.setForeground(Color.BLACK);
        this.panel.add(this.date);

        this.btn_search = new JButton("Search");
        this.btn_search.setFont(this.font_textField);
        this.btn_search.setBackground(Color.decode("#4b7bec"));
        this.btn_search.setForeground(Color.WHITE);
        this.btn_search.setOpaque(true);
        this.btn_search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.btn_search.setBorderPainted(false);
        this.btn_search.setToolTipText("click to search");
        this.btn_search.addActionListener(this);
        this.panel.add(this.btn_search);
        this.Sales.add(this.panel);
        this.newLine(10);
        /// ลำดับ
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#ffb142"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("No.");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Sales.add(this.panel);

        /// ชื่อสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#ffb142"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("Name");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Sales.add(this.panel);

        /// รหัสสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#ffb142"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("Amount");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Sales.add(this.panel);

        /// ประเภทสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#ffb142"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("payment date");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Sales.add(this.panel);

        /// ราคาสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(150, 40));
        this.panel.setBackground(Color.decode("#ffb142"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("total price");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Sales.add(this.panel);

        /// รายการสั่งซื้อ
        this.panelSaleList = new JPanel();
        this.panelSaleList.setLayout(new FlowLayout());
        this.panelSaleList.setPreferredSize(new Dimension(730, (120 * (this.mange_s.getSales().length / 2))));
        this.saleLists(this.mange_s.getSales());
        this.scrollPanel = new JScrollPane(this.panelSaleList);
        this.scrollPanel.setPreferredSize(new Dimension(750, 400));
        this.Sales.add(this.scrollPanel);
        this.newLine(10);

        this.Sales.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Sales.setSize(1200, 1113);
        this.Sales.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.btn_close) {
            this.Sales.setVisible(false);
            this.Sales.dispose();
            new App();
        } else if (event.getSource() == this.btn_search) {

            if (this.date.getText().toString().trim().equals("")) {
                this.panelSaleList.removeAll();
                SwingUtilities.updateComponentTreeUI(this.Sales);
                this.saleLists(this.mange_s.getSales());

            } else {
                if (this.mange_s.countSearch(this.date.getText().toString()) == 0) {
                    JOptionPane.showMessageDialog(this.Sales, "The information you searched for was not found.");
                } else {
                    this.panelSaleList.removeAll();
                    SwingUtilities.updateComponentTreeUI(this.Sales);
                    this.saleLists(this.mange_s.search(this.date.getText().toString()));
                }

            }

        }

    }

    public void saleLists(String[][] data) {
        int count = 1, total = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i][1] != null) {
                this.textField = new JTextField(8);
                this.textField.setEditable(false);
                this.textField.setFont(this.font_textField);
                this.textField.setText("" + (count));
                this.textField.setHorizontalAlignment(JTextField.CENTER);
                this.panelSaleList.add(this.textField);

                this.textField = new JTextField(8);
                this.textField.setEditable(false);
                this.textField.setFont(this.font_textField);
                this.textField.setText("" + data[i][1]);
                this.textField.setHorizontalAlignment(JTextField.CENTER);
                this.panelSaleList.add(this.textField);

                this.textField = new JTextField(8);
                this.textField.setEditable(false);
                this.textField.setFont(this.font_textField);
                this.textField.setText("" + data[i][2]);
                this.textField.setHorizontalAlignment(JTextField.CENTER);
                this.panelSaleList.add(this.textField);
                this.textField = new JTextField(8);
                this.textField.setEditable(false);
                this.textField.setFont(this.font_textField);

                this.textField.setText("" + data[i][3]);
                this.textField.setHorizontalAlignment(JTextField.CENTER);
                this.panelSaleList.add(this.textField);

                this.textField = new JTextField(8);
                this.textField.setEditable(false);
                this.textField.setFont(this.font_textField);
                this.textField.setText(DecimalFormat.format(Integer.parseInt(data[i][4])));
                this.textField.setHorizontalAlignment(JTextField.CENTER);
                this.panelSaleList.add(this.textField);
                total += Double.parseDouble(data[i][4]);
                count++;
            }
        }
        if (total != 0) {
            this.textField = new JTextField(26);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setBackground(Color.decode("#eeeeee"));
            this.textField.setBorder(BorderFactory.createLineBorder(Color.decode("#eeeeee")));
            this.textField.setText("");
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panelSaleList.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText("Total");
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.textField.setEditable(false);
            this.panelSaleList.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText(DecimalFormat.format(total));
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panelSaleList.add(this.textField);
        } else {
            JOptionPane.showMessageDialog(this.Sales, "The information you searched for was not found.");
        }
    }

    public void border() {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 1));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.Sales.add(this.panel);
    }

    public void newLine(int h) {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1200, h));
        this.Sales.add(this.panel);
    }
}