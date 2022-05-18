import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Product implements ActionListener {
    private Mange_p mange_p = new Mange_p();
    JFrame Product;
    Container c;
    JPanel panel;
    JScrollPane scrollPanel;
    JLabel label;
    JTextField pro[] = new JTextField[4];
    JTextField textField;
    JButton btn_add, btn_close;
    JButton btn_edit[], btn_del[];
    Font font = new Font("TH SarabunPSK", Font.BOLD, 30);
    Font font_textField = new Font("TH SarabunPSK", Font.BOLD, 25);
    boolean error = false;
    String data[][], row;
    Timer time;

    public void start() {
        this.create_head();
        this.time = new Timer(100, this);
        this.create_body();
    }

    public String[] GetCategory() {
        String category[] = new String[this.mange_p.GetCategory().length];

        for (int i = 0; i < category.length; i++) {
            category[i] = this.mange_p.GetCategory()[i][1];
        }
        return category;
    }

    public String[] GetProduct(String key) {
        if (key.equals("0")) {
            String pro[] = new String[this.mange_p.GetProduct().length];
            for (int i = 0; i < pro.length; i++) {
                pro[i] = this.mange_p.GetProduct()[i][1];
            }
            return pro;
        } else {
            int i = 1;
            String pro[] = new String[(this.mange_p.CountCate(key) + 1)];
            pro[0] = "เลือกสินค้า";
            for (int j = 0; j < this.mange_p.GetProduct().length; j++) {
                if (this.mange_p.GetProduct()[j][3].equals(key)) {
                    pro[i] = this.mange_p.GetProduct()[j][1];
                    i++;
                }
            }
            return pro;
        }
    }

    public void create_head() {
        this.Product = new JFrame("ระบบขายสินค้า");
        this.c = this.Product.getContentPane();
        this.c.setLayout(new FlowLayout());
        /// จัดการสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 50));
        this.panel.setBackground(Color.decode("#27ae60"));
        this.panel.setLayout(new GridBagLayout());
        this.label = new JLabel("จัดการสินค้า", SwingConstants.CENTER);
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.panel.add(this.label);
        this.Product.add(this.panel);

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
        this.Product.add(this.panel);
        this.border();
        this.newLine(10);

        /// ชื่อสินค้า
        this.panel.setPreferredSize(new Dimension(400, 50));
        this.label = new JLabel("ชื่อสินค้า  ");
        this.label.setPreferredSize(new Dimension(100, 50));
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.pro[0] = new JTextField(10);
        this.pro[0].setFont(this.font_textField);
        this.pro[0].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.pro[0].addActionListener(this);
        this.panel.add(this.pro[0]);
        this.Product.add(this.panel);

        /// รหัสสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 50));
        this.label = new JLabel("รหัสสินค้า  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.pro[1] = new JTextField(10);
        this.pro[1].setFont(this.font_textField);
        this.pro[1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.pro[1].addActionListener(this);
        this.panel.add(this.pro[1]);
        this.Product.add(this.panel);

        /// ประเภทสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 50));
        this.label = new JLabel("ประเภทสินค้า  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.pro[2] = new JTextField(10);
        this.pro[2].setFont(this.font_textField);
        this.pro[2].addActionListener(this);
        this.pro[2].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.panel.add(this.pro[2]);
        this.Product.add(this.panel);

        /// ราคาสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 50));
        this.label = new JLabel("ราคาสินค้า  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.pro[3] = new JTextField(10);
        this.pro[3].addActionListener(this);
        this.pro[3].setFont(this.font_textField);
        this.pro[3].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.panel.add(this.pro[3]);
        this.Product.add(this.panel);

        /// ปุ่มเพิ่มสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(600, 50));
        this.panel.setLayout(new GridBagLayout());
        this.btn_add = new JButton("เพิ่มสินค้า");
        this.btn_add.setFont(this.font_textField);
        this.btn_add.setToolTipText("คลิกเพื่อเพิ่มสินค้า");
        this.btn_add.setPreferredSize(new Dimension(200, 50));
        this.btn_add.setOpaque(true);
        this.btn_add.setBorderPainted(false);
        this.btn_add.setBackground(Color.decode("#27ae60"));
        this.btn_add.setForeground(Color.WHITE);
        this.btn_add.setOpaque(true);
        this.btn_add.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.btn_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.btn_add.setHorizontalAlignment(JButton.CENTER);
        this.btn_add.addActionListener(this);
        this.panel.add(this.btn_add);
        this.Product.add(this.panel);

        this.newLine(10);
        /// เส้น
        this.border();

        this.newLine(10);
        /// รายการสินค้าในคลัง

        /// ลำดับ
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#27ae60"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("ลำดับ");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Product.add(this.panel);

        /// ชื่อสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#27ae60"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("ชื่อสินค้า");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Product.add(this.panel);

        /// รหัสสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#27ae60"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("รหัสสินค้า");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Product.add(this.panel);

        /// ประเภทสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#27ae60"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("ประเภทสินค้า");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Product.add(this.panel);

        /// ราคาสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode("#27ae60"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("ราคาสินค้า");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Product.add(this.panel);

        /// จัดการ
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(180, 40));
        this.panel.setBackground(Color.decode("#27ae60"));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("จัดการ");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Product.add(this.panel);

    }

    public void create_body() {
        /// รายการสั่งซื้อ
        this.data = this.mange_p.GetProduct();
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(880, (120 * (this.data.length / 2))));
        this.btn_edit = new JButton[this.data.length];
        this.btn_del = new JButton[this.data.length];
        for (int i = 1; i < this.data.length; i++) {
            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText("" + (i));
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText(this.data[i][1]);
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText(this.data[i][2]);
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText(this.data[i][3]);
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText(this.data[i][4]);
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);

            this.btn_edit[i] = new JButton("แก้ไข");
            this.btn_edit[i].setFont(this.font_textField);
            this.btn_edit[i].setBackground(Color.decode("#f1c40f"));
            this.btn_edit[i].setForeground(Color.WHITE);
            this.btn_edit[i].setToolTipText("คลิกเพื่อแก้ไข");
            this.btn_edit[i].setBorderPainted(false);
            this.btn_edit[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.btn_edit[i].setOpaque(true);
            this.btn_edit[i].setPreferredSize(new Dimension(80, 30));
            this.btn_edit[i].addActionListener(this);
            this.panel.add(this.btn_edit[i]);

            this.btn_del[i] = new JButton("ลบ");
            this.btn_del[i].addActionListener(this);
            this.btn_del[i].setFont(this.font_textField);
            this.btn_del[i].setBackground(Color.decode("#e74c3c"));
            this.btn_del[i].setToolTipText("คลิกเพื่อลบ");
            this.btn_del[i].setForeground(Color.WHITE);
            this.btn_del[i].setBorderPainted(false);
            this.btn_del[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.btn_del[i].setOpaque(true);
            this.btn_del[i].setPreferredSize(new Dimension(80, 30));
            this.panel.add(this.btn_del[i]);
        }
        this.scrollPanel = new JScrollPane(this.panel);
        this.scrollPanel.setPreferredSize(new Dimension(920, 400));
        this.Product.add(this.scrollPanel);
        this.Product.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Product.setSize(1200, 1113);
        this.Product.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btn_close) {
            this.time.stop();
            this.Product.setVisible(false);
            this.Product.dispose();
            new App();
        } else if (e.getSource() == this.btn_add) {
            /// check ค่าว่าง textfield
            for (int i = 0; i < 4; i++) {
                if (this.pro[i].getText().equals("")) {
                    JOptionPane.showMessageDialog(this.Product, "กรุณากรอกให้ครบถ้วน!", "เกิดข้อผิดพลาด",
                            JOptionPane.ERROR_MESSAGE);
                    this.error = true;
                    break;
                } else {
                    this.error = false;
                }
            }
            if (!this.error) {
                switch (this.btn_add.getText()) {
                    case "เพิ่มสินค้า":
                        String[] add = { this.pro[0].getText(), this.pro[1].getText(), this.pro[2].getText(),
                                this.pro[3].getText() };
                        if (this.mange_p.AddProduct(add)) {
                            JOptionPane.showMessageDialog(this.Product, "เพิ่มสินค้าสำเร็จ!", "สำเร็จ",
                                    JOptionPane.INFORMATION_MESSAGE);
                            this.Product.setVisible(false);
                            this.Product.dispose();
                            this.start();
                        } else {
                            JOptionPane.showMessageDialog(this.Product, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง!",
                                    "เกิดข้อผิดพลาด",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        break;
                    case "แก้ไข":
                        if (JOptionPane.showConfirmDialog(this.Product, "คุณต้องการแก้ไขสินค้าหรือไม่?", "ยืนยัน",
                                JOptionPane.YES_NO_OPTION) == 0) {
                            String[] edit = { this.row, this.pro[0].getText(), this.pro[1].getText(),
                                    this.pro[2].getText(), this.pro[3].getText() };
                            if (this.mange_p.EditProduct(edit)) {
                                JOptionPane.showMessageDialog(this.Product, "แก้ไขสินค้าสำเร็จ!", "สำเร็จ",
                                        JOptionPane.INFORMATION_MESSAGE);
                                this.Product.setVisible(false);
                                this.Product.dispose();
                                this.start();
                            } else {
                                JOptionPane.showMessageDialog(this.Product, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง!",
                                        "เกิดข้อผิดพลาด",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                        }
                        break;
                }

            }
        }
        for (int i = 0; i < this.data.length; i++) {
            /// แก้ไขสินค้า
            if (e.getSource() == btn_edit[i]) {
                this.row = this.data[i][0];
                this.pro[0].setText(this.data[i][1]);
                this.pro[1].setText(this.data[i][2]);
                this.pro[2].setText(this.data[i][3]);
                this.pro[3].setText(this.data[i][4]);
                this.btn_add.setText("แก้ไข");
                this.btn_add.setBackground(Color.decode("#ffb142"));
                this.time.start();
            } else if (e.getSource() == btn_del[i]) {
                this.row = this.data[i][0];
                if (JOptionPane.showConfirmDialog(this.Product, "คุณต้องการลบสินค้าหรือไม่?", "ยืนยัน",
                        JOptionPane.YES_NO_OPTION) == 0) {

                    String[] del = { "" + this.row, this.pro[0].getText(), this.pro[1].getText(),
                            this.pro[2].getText(), this.pro[3].getText() };
                    if (this.mange_p.DeleteProduct(del)) {
                        JOptionPane.showMessageDialog(this.Product, "ลบสินค้าสำเร็จ!", "สำเร็จ",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.Product.setVisible(false);
                        this.Product.dispose();
                        this.start();
                    } else {
                        JOptionPane.showMessageDialog(this.Product, "เกิดข้อผิดพลาดโปรดลองอีกครั้ง!",
                                "เกิดข้อผิดพลาด",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            /// check ค่าว่าง textfield
            if (this.pro[0].getText().isEmpty() && this.pro[1].getText().isEmpty() && this.pro[2].getText().isEmpty()
                    && this.pro[3].getText().isEmpty()) {
                this.btn_add.setText("เพิ่มสินค้า");
                this.btn_add.setBackground(Color.decode("#27ae60"));
                this.time.stop();
            }
        }
    }

    public void border() {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 1));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.Product.add(this.panel);
    }

    public void newLine(int h) {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1200, h));
        this.Product.add(this.panel);
    }

    public int getAmount() {
        return this.mange_p.GetProduct().length;
    }
}