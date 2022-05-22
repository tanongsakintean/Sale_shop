import java.awt.*;
import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JScrollPane;

public class App implements ActionListener {
    DecimalFormat DecimalFormat = new DecimalFormat("#,###,###,###");
    private Product pro = new Product();
    private Order or = new Order();
    private Sales sa = new Sales();
    private Member mem = new Member();
    JFrame dashboard;
    Container c;
    JScrollPane scrollPanel;
    JPanel panel, panelorder;
    JButton btn[] = new JButton[4];
    JButton btn_buy, btn_pay, btn_cancle;
    JButton btn_clear[];
    JTextField textField[] = new JTextField[3];
    JTextField order;
    private String txt_btn[] = { "Product", "Customer", "Saler", "Sales" },
            color_btn[] = { "#27ae60", "#00a8ff", "#8c7ae6", "#ffb142" };
    private String txt_textField[] = { "products : " + (this.pro.getAmount() - 1),
            "customers : " + (this.mem.getAmount(1) - 1),
            "salers : " + (this.mem.getAmount(2) - 1) };
    JComboBox<String> comboBox_pro, comboBox_cus, comboBox_cate;
    JLabel label;
    JTextField amount_TextField;
    Font font = new Font("Arial", Font.BOLD, 25);
    Font font_textField = new Font("Arial", Font.BOLD, 18);

    public App() {
        this.menu();
    }

    public void menu() {
        this.dashboard = new JFrame("Sale Management System");
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
            this.btn[i].setToolTipText("Click to enter the desired window.");
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
        this.label = new JLabel("Enter amount");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.amount_TextField = new JTextField(10);
        this.amount_TextField.setFont(this.font_textField);
        this.amount_TextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.amount_TextField.addActionListener(this);
        this.amount_TextField.setHorizontalAlignment(JTextField.CENTER);
        this.panel.add(this.amount_TextField);
        this.dashboard.add(this.panel);

        /// เลิอกประเภทสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(200, 100));
        this.label = new JLabel("select category  ");
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
        this.label = new JLabel("choose products ");
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
        this.label = new JLabel("discount");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.comboBox_cus = new JComboBox<>(this.mem.GetMember());
        this.comboBox_cus.setFont(this.font_textField);
        this.comboBox_cus.setBackground(Color.WHITE);
        this.comboBox_cus.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.comboBox_cus.addActionListener(this);
        this.comboBox_pro.setAutoscrolls(true);
        this.panel.add(this.comboBox_cus);
        this.dashboard.add(this.panel);

        /// เพิ่มออเดอร์
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(200, 100));
        this.btn_buy = new JButton("add order");
        this.btn_buy.setToolTipText("Click to add order");
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
        this.label = new JLabel("ORDER LIST");
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
        this.label = new JLabel("No.");
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
        this.label = new JLabel("Product code");
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
        this.label = new JLabel("product");
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
        this.label = new JLabel("category");
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
        this.label = new JLabel("amount");
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
        this.label = new JLabel("price (THB)");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.white);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.dashboard.add(this.panel);

        /// ข่ึ้นบรรทัดใหม่
        this.newLine(5);

        /// รายการสั่งซื้อ
        this.panelorder = new JPanel();
        this.panelorder.setLayout(new FlowLayout());
        this.panelorder.setPreferredSize(new Dimension(880, (150 * (this.or.getOrder().length / 2))));
        this.orderList(this.or.getOrder());
        this.scrollPanel = new JScrollPane(this.panelorder);
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
        this.btn_pay = new JButton("Pay");
        this.btn_pay.setFont(this.font);
        this.btn_pay.setBackground(Color.decode("#70a1ff"));
        this.btn_pay.setForeground(Color.white);
        this.btn_pay.setToolTipText("click to pay");
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
        this.btn_cancle = new JButton("Cancle");
        this.btn_cancle.setBorderPainted(false);
        this.btn_cancle.setToolTipText("click to cancle");
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
            this.sa.start();
            this.dashboard.setVisible(false);
            this.dashboard.dispose();
            // SwingUtilities.updateComponentTreeUI(this.dashboard);
        } else if (event.getSource() == this.btn_buy) {
            if (this.amount_TextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the number of products you wish to purchase.",
                        "Please fill out",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (this.checkType()) {
                    if (this.comboBox_pro.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(this.dashboard, "Please select the product.", "Please select",
                                JOptionPane.WARNING_MESSAGE);
                    } else {

                        if (JOptionPane.showConfirmDialog(this.dashboard, "Would you like to add a product order?",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION) == 0) {
                            this.panelorder.removeAll();
                            SwingUtilities.updateComponentTreeUI(this.dashboard);
                            String cus = "";
                            if (this.comboBox_cus.getSelectedIndex() == 0) {
                                cus = "0";
                            } else {
                                cus = this.comboBox_cus.getSelectedItem().toString();
                            }
                            String[] data = { this.amount_TextField.getText(),
                                    this.comboBox_cate.getSelectedItem().toString(),
                                    this.comboBox_pro.getSelectedItem().toString(), cus };
                            this.or.addOrder(data);
                            this.orderList(this.or.getOrder());
                            this.amount_TextField.setText("");
                            this.comboBox_pro.setSelectedIndex(0);
                            this.comboBox_cus.setSelectedIndex(0);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this.dashboard, "Please enter the correct type!",
                            "error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (event.getSource() == this.btn_cancle)

        {
            if (this.or.getOrder().length <= 1) {
                JOptionPane.showMessageDialog(this.dashboard, "Please fill out the order information first.",
                        "Please fill out",
                        JOptionPane.ERROR_MESSAGE);

            } else {

                if (JOptionPane.showConfirmDialog(this.dashboard, "Do you want to cancel all orders?", "Confirm",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    if (this.or.deleteOrder()) {
                        this.panelorder.removeAll();
                        SwingUtilities.updateComponentTreeUI(this.dashboard);
                        this.orderList(this.or.getOrder());
                    } else {
                        JOptionPane.showMessageDialog(null, "An error occurred, please contact the admin.", "error",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        } else if (event.getSource() == this.btn_pay) {
            if (this.or.getOrder().length > 1) {
                if (JOptionPane.showConfirmDialog(this.dashboard,
                        "Do you want to pay?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {
                    int money = Integer
                            .parseInt(JOptionPane.showInputDialog(this.dashboard,
                                    "total : " + DecimalFormat.format(this.or.getCost()[0] - this.or.getCost()[1])
                                            + " THB" + " Discount : "
                                            + DecimalFormat.format(this.or.getCost()[1]) + " THB",
                                    "Enter the amount received",
                                    JOptionPane.QUESTION_MESSAGE));
                    if (money >= (this.or.getCost()[0] - this.or.getCost()[1])) {
                        JOptionPane.showMessageDialog(this.dashboard,
                                " change is "
                                        + DecimalFormat.format(
                                                this.or.pay(money, (this.or.getCost()[0] - this.or.getCost()[1])))
                                        + " THB",
                                "successful payment",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.or.deleteOrder();
                        this.panelorder.removeAll();
                        SwingUtilities.updateComponentTreeUI(this.dashboard);
                    } else {
                        JOptionPane.showMessageDialog(this.dashboard, "Incorrect amount received",
                                "Payment failed, please try again.",
                                JOptionPane.ERROR_MESSAGE);

                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "There are no orders to pay.",
                        "error", JOptionPane.ERROR_MESSAGE);
            }
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

    public void orderList(String[][] data) {
        if (data.length > 1) {
            for (int i = 1; i < data.length; i++) {
                this.order = new JTextField(8);
                this.order.setEditable(false);
                this.order.setFont(this.font_textField);
                this.order.setText("" + data[i][0]);
                this.order.setHorizontalAlignment(JTextField.CENTER);
                this.panelorder.add(this.order);

                this.order = new JTextField(8);
                this.order.setText("" + data[i][1]);
                this.order.setHorizontalAlignment(JTextField.CENTER);
                this.order.setEditable(false);
                this.order.setFont(this.font_textField);
                this.panelorder.add(this.order);

                this.order = new JTextField(8);
                this.order.setText("" + data[i][2]);
                this.order.setHorizontalAlignment(JTextField.CENTER);
                this.order.setEditable(false);
                this.order.setFont(this.font_textField);
                this.panelorder.add(this.order);

                this.order = new JTextField(8);
                this.order.setEditable(false);
                this.order.setText("" + data[i][3]);
                this.order.setHorizontalAlignment(JTextField.CENTER);
                this.order.setFont(this.font_textField);
                this.panelorder.add(this.order);

                this.order = new JTextField(8);
                this.order.setText("" + data[i][4]);
                this.order.setHorizontalAlignment(JTextField.CENTER);
                this.order.setEditable(false);
                this.order.setFont(this.font_textField);
                this.panelorder.add(this.order);

                this.order = new JTextField(8);
                this.order.setText("" + data[i][5]);
                this.order.setHorizontalAlignment(JTextField.CENTER);
                this.order.setEditable(false);
                this.order.setFont(this.font_textField);
                this.panelorder.add(this.order);

            }
        }

    }

    public boolean checkType() {
        try {

            Integer.parseInt(this.amount_TextField.getText().toString().trim());
            return true;
        } catch (

        Exception e) {
            return false;
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
