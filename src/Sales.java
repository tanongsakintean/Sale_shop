import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Sales implements ActionListener {
    JFrame Sales;
    Container c;
    JPanel panel;
    JScrollPane scrollPanel;
    JLabel label;
    JTextField pro[] = new JTextField[4];
    JTextField textField, date;
    JButton btn_add, btn_close, btn_search;
    JButton btn_edit[], btn_del[];
    Font font = new Font("TH SarabunPSK", Font.BOLD, 30);
    Font font_textField = new Font("TH SarabunPSK", Font.BOLD, 25);

    public void start() {
        this.Sales = new JFrame("ระบบขายสินค้า");
        this.c = this.Sales.getContentPane();
        this.c.setLayout(new FlowLayout());

        /// จัดการสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 50));
        this.panel.setBackground(Color.decode("#ffb142"));
        this.panel.setLayout(new GridBagLayout());
        this.label = new JLabel("จัดการสรุปยอดขาย", SwingConstants.CENTER);
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
        this.btn_close.setToolTipText("คลิกเพื่อปิดหน้าต่าง");
        this.btn_close.addActionListener(this);
        this.panel.add(this.btn_close);
        this.Sales.add(this.panel);
        this.border();
        this.newLine(10);

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 50));
        this.panel.setLayout(new GridBagLayout());
        this.label = new JLabel("รายการสรุปยอดขาย", SwingConstants.CENTER);
        this.label.setFont(this.font);
        this.label.setForeground(Color.BLACK);
        this.panel.add(this.label);
        this.Sales.add(this.panel);

        this.newLine(10);
        /// รายการสินค้าในคลัง

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.label = new JLabel("กรอกวัน/เดือน/ปี");
        this.label.setFont(this.font);
        this.label.setForeground(Color.BLACK);
        this.panel.add(this.label);
        this.date = new JTextField(10);
        this.date.setFont(this.font_textField);
        this.date.setBorder(BorderFactory.createLineBorder(Color.black));
        this.date.setBackground(Color.decode("#f2f2f2"));
        this.date.setForeground(Color.BLACK);
        this.panel.add(this.date);
        this.btn_search = new JButton("ค้นหา");
        this.btn_search.setFont(this.font_textField);
        this.btn_search.setBackground(Color.decode("#4b7bec"));
        this.btn_search.setForeground(Color.WHITE);
        this.btn_search.setOpaque(true);
        this.btn_search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.btn_search.setBorderPainted(false);
        this.btn_search.setToolTipText("คลิกเพื่อค้นหาข้อมูล");
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
        this.label = new JLabel("ลำดับ");
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
        this.label = new JLabel("ชื่อผู้สั่งซื้อ");
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
        this.label = new JLabel("จำนวน");
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
        this.label = new JLabel("วันที่ชำระ");
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
        this.label = new JLabel("ราคารวมทั้งหมด");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Sales.add(this.panel);

        /// รายการสั่งซื้อ
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(730, 400));
        this.btn_edit = new JButton[4];
        this.btn_del = new JButton[4];
        for (int i = 0; i < 4; i++) {
            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText("" + (i + 1));
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText("" + (i + 1));
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText("" + (i + 1));
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);
            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText("" + (i + 1));
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);
            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText("" + (i + 1));
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);

        }
        this.scrollPanel = new JScrollPane(this.panel);
        this.scrollPanel.setPreferredSize(new Dimension(750, 400));
        this.Sales.add(this.scrollPanel);

        this.Sales.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Sales.setSize(1200, 1113);
        this.Sales.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.btn_close) {
            this.Sales.setVisible(false);
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