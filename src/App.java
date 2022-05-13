import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JScrollPane;

public class App implements ActionListener {
    Product pro = new Product();
    Order or;
    Member mem = new Member();
    JFrame dashboard;
    Container c;
    JScrollPane scrollPanel;
    JPanel panel;
    JButton btn[] = new JButton[4];
    JButton btn_buy, btn_pay, btn_cancle;
    JButton btn_clear[];
    JTextField textField[] = new JTextField[3];
    JTextField order;
    String txt_btn[] = { "เพิ่มสินค้า", "เพิ่มลูกค้า", "เพิ่มพนักงาน", "สรุปยอดขาย" };
    String color_btn[] = { "#27ae60", "#00a8ff", "#8c7ae6", "#ffb142" };
    String txt_textField[] = { "สินค้า : " + (this.pro.getAmount() - 1), "ลูกค้า : " + this.mem.getAmount(1),
            "พนักงาน : " + this.mem.getAmount(2) };

    JComboBox<String> comboBox_pro, comboBox_cus, comboBox_cate;
    JLabel label;
    JTextField amount_TextField;
    Font font = new Font("TH SarabunPSK", Font.BOLD, 40);
    Font font_textField = new Font("TH SarabunPSK", Font.BOLD, 25);
    String order_detail = "";

    public App() {
        this.menu();
    }

    public void menu() {
        this.dashboard = new JFrame("ระบบการขายสินค้า");
        this.c = this.dashboard.getContentPane();
        this.c.setLayout(new FlowLayout());
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1200, 30));
        this.dashboard.add(this.panel);
        for (int i = 0; i < textField.length; i++) {
            this.panel = new JPanel();
            this.panel.setLayout(new GridBagLayout());
            this.panel.setPreferredSize(new Dimension(300, 50));
            this.textField[i] = new JTextField(10);
            this.textField[i].setPreferredSize(new Dimension(200, 50));
            this.textField[i].setEditable(false);
            this.textField[i].setFont(this.font);
            this.textField[i].setText(this.txt_textField[i]);
            this.textField[i].setHorizontalAlignment(JTextField.CENTER);
            this.textField[i].setForeground(Color.decode(color_btn[i]));
            this.textField[i].setOpaque(true);
            this.textField[i].setBackground(Color.decode("#ced6e0"));
            this.panel.add(this.textField[i]);
            this.dashboard.add(this.panel);
        }

        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1200, 10));
        this.dashboard.add(this.panel);

        for (int i = 0; i < btn.length; i++) {
            this.panel = new JPanel();
            this.panel.setLayout(new GridBagLayout());
            this.panel.setPreferredSize(new Dimension(200, 100));
            this.btn[i] = new JButton(txt_btn[i]);
            this.btn[i].setFont(this.font);
            this.btn[i].setBorderPainted(false);
            this.btn[i].setToolTipText("คลิกเพื่อเข้าสู่หน้าต่างที่ต้องการ");
            this.btn[i].setForeground(Color.WHITE);
            this.btn[i].setOpaque(true);
            this.btn[i].setMargin(new Insets(5, 5, 5, 5));
            this.btn[i].setBackground(Color.decode(color_btn[i]));
            this.btn[i].addActionListener(this);
            this.btn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.panel.add(this.btn[i]);
            this.dashboard.add(this.panel);
        }

        /// ข่ึ้นบรรทัดใหม่
        this.newLine(10);

        /// เส้น
        this.border();

        /// ข่ึ้นบรรทัดใหม่
        this.newLine(30);

        /// ใส่จำนวนสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(200, 100));
        this.label = new JLabel("ใส่จำนวนสินค้า  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.amount_TextField = new JTextField(10);
        this.amount_TextField.setFont(this.font_textField);
        this.amount_TextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.amount_TextField.setHorizontalAlignment(JTextField.CENTER);
        this.panel.add(this.amount_TextField);
        this.dashboard.add(this.panel);

        /// เลิอกประเภทสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(200, 100));
        this.label = new JLabel("เลือกประเภทสินค้า  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.comboBox_cate = new JComboBox<>(this.pro.GetCategory());
        this.comboBox_cate.addActionListener(this);
        this.comboBox_cate.setFont(this.font_textField);
        this.comboBox_cate.setBackground(Color.WHITE);
        this.comboBox_cate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.comboBox_cate.setAutoscrolls(true);
        this.panel.add(this.comboBox_cate);
        this.dashboard.add(this.panel);

        /// เลิอกสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(200, 100));
        this.label = new JLabel("เลือกประเภทสินค้า     ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.comboBox_pro = new JComboBox<>(this.pro.GetProduct("0"));
        this.comboBox_pro.addActionListener(this);
        this.comboBox_pro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.comboBox_pro.setFont(this.font_textField);
        this.comboBox_pro.setAutoscrolls(true);
        this.comboBox_pro.setBackground(Color.WHITE);
        this.panel.add(this.comboBox_pro);
        this.dashboard.add(this.panel);

        /// เลือกลูกค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(200, 100));
        this.label = new JLabel("เลือกลูกค้า (ส่วนลด)  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.comboBox_cus = new JComboBox<>(this.mem.GetMember());
        this.comboBox_cus.setFont(this.font_textField);
        this.comboBox_cus.setBackground(Color.WHITE);
        this.comboBox_cus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.comboBox_pro.setAutoscrolls(true);
        this.panel.add(this.comboBox_cus);
        this.dashboard.add(this.panel);

        /// เพิ่มออเดอร์
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(200, 100));
        this.btn_buy = new JButton("เพิ่มออเดอร์");
        this.btn_buy.setToolTipText("คลิกเพื่อเพิ่มออเดอร์");
        this.btn_buy.setFont(this.font_textField);
        this.btn_buy.setForeground(Color.WHITE);
        this.btn_buy.setBorderPainted(false);
        this.btn_buy.setBackground(Color.decode("#4b7bec"));
        this.btn_buy.setOpaque(true);
        this.btn_buy.setMargin(new Insets(5, 10, 5, 10));
        this.btn_buy.addActionListener(this);
        this.btn_buy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.panel.add(this.btn_buy);
        this.dashboard.add(this.panel);

        /// เส้น
        this.border();

        /// ข่ึ้นบรรทัดใหม่
        this.newLine(10);

        /// รายการสั่งซื้อ
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1200, 30));
        this.panel.setLayout(new GridLayout(1, 2));
        this.label = new JLabel("รายการสั่งซื้อ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.dashboard.add(this.panel);

        /// ลำดับ
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#70a1ff"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("ลำดับ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.white);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.dashboard.add(this.panel);

        /// รหัสสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#70a1ff"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("รหัสสินค้า");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.white);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.dashboard.add(this.panel);

        /// สินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#70a1ff"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("สินค้า");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.white);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.dashboard.add(this.panel);

        /// ประเภท
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(Color.decode("#70a1ff"));
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("ประเภท");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.white);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.dashboard.add(this.panel);

        /// จำนวน
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#70a1ff"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("จำนวน");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.white);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.dashboard.add(this.panel);

        /// ราคา(บาท
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#70a1ff"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("ราคา(บาท)");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.white);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.dashboard.add(this.panel);

        /// ข่ึ้นบรรทัดใหม่
        this.newLine(5);

        /// รายการสั่งซื้อ
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(880, 150));
        for (int i = 0; i < 4; i++) {
            this.order = new JTextField(8);
            this.order.setEditable(false);
            this.order.setFont(this.font_textField);
            this.order.setText("" + (i + 1));
            this.order.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.order);

            this.order = new JTextField(8);
            this.order.setText("" + (i + 1));
            this.order.setHorizontalAlignment(JTextField.CENTER);
            this.order.setEditable(false);
            this.order.setFont(this.font_textField);
            this.panel.add(this.order);

            this.order = new JTextField(8);
            this.order.setText("" + (i + 1));
            this.order.setHorizontalAlignment(JTextField.CENTER);
            this.order.setEditable(false);
            this.order.setFont(this.font_textField);
            this.panel.add(this.order);

            this.order = new JTextField(8);
            this.order.setEditable(false);
            this.order.setText("" + (i + 1));
            this.order.setHorizontalAlignment(JTextField.CENTER);
            this.order.setFont(this.font_textField);
            this.panel.add(this.order);

            this.order = new JTextField(8);
            this.order.setText("" + (i + 1));
            this.order.setHorizontalAlignment(JTextField.CENTER);
            this.order.setEditable(false);
            this.order.setFont(this.font_textField);
            this.panel.add(this.order);

            this.order = new JTextField(8);
            this.order.setText("" + (i + 1));
            this.order.setHorizontalAlignment(JTextField.CENTER);
            this.order.setEditable(false);
            this.order.setFont(this.font_textField);
            this.panel.add(this.order);

        }
        this.scrollPanel = new JScrollPane(this.panel);
        this.scrollPanel.setPreferredSize(new Dimension(920, 150));
        this.dashboard.add(this.scrollPanel);

        /// เส้น
        this.border();

        /// ข่ึ้นบรรทัดใหม่
        this.newLine(5);

        /// ชำระเงิน
        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.panel.setBackground(Color.decode("#20bf6b"));
        this.btn_pay = new JButton("ชำระเงิน");
        this.btn_pay.setFont(this.font);
        this.btn_pay.setBackground(Color.decode("#70a1ff"));
        this.btn_pay.setForeground(Color.white);
        this.btn_pay.setToolTipText("คลิกเพื่อชำระเงิน");
        this.btn_pay.setBorderPainted(false);
        this.btn_pay.setPreferredSize(new Dimension(170, 30));
        this.btn_pay.setMargin(new Insets(5, 5, 5, 5));
        this.btn_pay.setHorizontalAlignment(JButton.CENTER);
        this.btn_pay.addActionListener(this);
        this.btn_pay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.panel.add(this.btn_pay);
        this.dashboard.add(this.panel);

        /// ยกเลิก
        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.panel.setBackground(Color.decode("#eb3b5a"));
        this.btn_cancle = new JButton("ยกเลิก");
        this.btn_cancle.setBorderPainted(false);
        this.btn_cancle.setToolTipText("คลิกเพื่อยกเลิก");
        this.btn_cancle.setFont(this.font);
        this.btn_cancle.setBackground(Color.decode("#70a1ff"));
        this.btn_cancle.setForeground(Color.white);
        this.btn_cancle.setPreferredSize(new Dimension(140, 30));
        this.btn_cancle.setMargin(new Insets(5, 5, 5, 5));
        this.btn_cancle.setHorizontalAlignment(JButton.CENTER);
        this.btn_cancle.addActionListener(this);
        this.btn_cancle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.panel.add(this.btn_cancle);
        this.dashboard.add(this.panel);

        this.dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dashboard.setSize(1200, 1113);
        this.dashboard.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == this.btn[0]) {
            this.pro.start();
            this.dashboard.setVisible(false);
            this.dashboard.dispose();
        } else if (event.getSource() == this.btn[1]) {
            this.mem.start(1);
            this.dashboard.setVisible(false);
            this.dashboard.dispose();
        } else if (event.getSource() == this.btn[2]) {
            this.mem.start(2);
        } else if (event.getSource() == this.btn[3]) {
            this.dashboard.setVisible(false);
            this.dashboard.dispose();
            // SwingUtilities.updateComponentTreeUI(this.dashboard);
        }
        if (this.comboBox_cate.getSelectedIndex() > 1) {
            this.comboBox_pro.setModel(
                    new DefaultComboBoxModel<String>(
                            this.pro.GetProduct(this.comboBox_cate.getSelectedItem().toString())));
            this.comboBox_cate.setSelectedIndex(0);
        } else if (this.comboBox_cate.getSelectedIndex() == 1) {
            this.comboBox_pro.setModel(new DefaultComboBoxModel<String>(this.pro.GetProduct("0")));
            this.comboBox_cate.setSelectedIndex(0);

        }
    }

    public void border() {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 1));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.dashboard.add(this.panel);
    }

    public void newLine(int h) {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1200, h));
        this.dashboard.add(this.panel);
    }

    public static void main(String[] args) throws Exception {
        new App();
    }
}
