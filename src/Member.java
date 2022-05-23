import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Member implements ActionListener {
    private Mange_m mange_m = new Mange_m();
    JFrame Member;
    Container c;
    JPanel panel;
    JScrollPane scrollPanel;
    JLabel label;
    JTextField mem[] = new JTextField[3];
    JTextField textField;
    JButton btn_add, btn_close;
    JButton btn_edit[], btn_del[];
    JComboBox<String> gender;
    String txt_gender[] = { "choose gender", "male", "female" }, status[] = new String[4];
    Font font = new Font("Arial", Font.BOLD, 20);
    Font font_textField = new Font("Arial", Font.BOLD, 18);;
    String data[][], row;
    boolean error = false;
    Timer time;

    public void start(int status) {
        if (status == 1) {
            this.status[0] = "Mange Customer";
            this.status[1] = "#00a8ff";
            this.status[2] = "Customer";
            this.status[3] = "1";
        } else if (status == 2) {
            this.status[0] = "Mange Saler";
            this.status[1] = "#8c7ae6";
            this.status[2] = "Saler";
            this.status[3] = "2";
        }

        this.create_head();
        this.time = new Timer(100, this);
        this.create_body();
    }

    public String[] GetMember() {
        String mem[] = new String[(this.mange_m.GetMember(1).length)];
        for (int i = 0; i < mem.length; i++) {
            mem[i] = this.mange_m.GetMember(1)[i][1] + " " + this.mange_m.GetMember(1)[i][2];
        }
        return mem;
    }

    public int getAmount(int status) {
        return this.mange_m.GetMember(status).length;
    }

    public void create_head() {
        this.Member = new JFrame("Sale Management System");
        this.c = this.Member.getContentPane();
        this.c.setLayout(new FlowLayout());

        /// จัดการสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 50));
        this.btn_close = new JButton("Close");
        this.panel.setBackground(Color.decode(this.status[1]));
        this.panel.setLayout(new GridBagLayout());
        this.label = new JLabel(this.status[0], SwingConstants.CENTER);
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.panel.add(this.label);
        this.Member.add(this.panel);

        /// ปิดวินโดว์
        this.panel = new JPanel();
        this.btn_close = new JButton("X");
        this.btn_close.setFont(this.font_textField);
        this.btn_close.setPreferredSize(new Dimension(70, 50));
        this.btn_close.setBackground(Color.red);
        this.btn_close.setToolTipText("Click to close");
        this.btn_close.setOpaque(true);
        this.btn_close.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.btn_close.setBorderPainted(false);
        this.btn_close.setForeground(Color.white);
        this.btn_close.addActionListener(this);
        this.panel.add(this.btn_close);
        this.Member.add(this.panel);

        this.border();
        this.newLine(10);

        /// ชื่อสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 50));
        this.label = new JLabel("Full Name  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.mem[0] = new JTextField(10);
        this.mem[0].setFont(this.font_textField);
        this.mem[0].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.panel.add(this.mem[0]);
        this.Member.add(this.panel);

        /// รหัสสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 50));
        this.label = new JLabel("Last Name  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.mem[1] = new JTextField(10);
        this.mem[1].setFont(this.font_textField);
        this.mem[1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.panel.add(this.mem[1]);
        this.Member.add(this.panel);

        /// ประเภทสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 50));
        this.label = new JLabel("Gender  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.gender = new JComboBox<>(this.txt_gender);
        this.gender.setFont(this.font_textField);
        this.gender.setBackground(Color.WHITE);
        this.gender.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.gender.setAutoscrolls(true);
        this.panel.add(this.gender);
        this.Member.add(this.panel);

        /// ราคาสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 50));
        this.label = new JLabel("Phone  ");
        this.label.setFont(this.font_textField);
        this.label.setForeground(Color.BLACK);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);

        this.mem[2] = new JTextField(10);
        this.mem[2].setFont(this.font_textField);
        this.mem[2].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.panel.add(this.mem[2]);
        this.Member.add(this.panel);

        /// ปุ่มเพิ่มสินค้า
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(600, 50));
        this.panel.setLayout(new GridBagLayout());
        this.btn_add = new JButton(this.status[3]);
        this.btn_add.setFont(this.font_textField);
        this.btn_add.setPreferredSize(new Dimension(200, 50));
        this.btn_add.setOpaque(true);
        this.btn_add.setBorderPainted(false);
        this.btn_add.setBackground(Color.decode(this.status[1]));
        this.btn_add.setForeground(Color.WHITE);
        this.btn_add.setText("Add" + this.status[2]);
        this.btn_add.setToolTipText("click to" + this.status[2]);
        this.btn_add.setOpaque(true);
        this.btn_add.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.btn_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.btn_add.setHorizontalAlignment(JButton.CENTER);
        this.btn_add.addActionListener(this);
        this.panel.add(this.btn_add);
        this.Member.add(this.panel);

        this.newLine(10);
        /// เส้น
        this.border();

        this.newLine(10);
        /// รายการสินค้าในคลัง

        /// ลำดับ
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode(this.status[1]));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("No.");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Member.add(this.panel);

        /// ชื่อสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode(this.status[1]));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("Full Name");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Member.add(this.panel);

        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode(this.status[1]));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("Last Name");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Member.add(this.panel);

        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode(this.status[1]));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("Gender");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Member.add(this.panel);

        /// รหัสสินค้า
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(140, 40));
        this.panel.setBackground(Color.decode(this.status[1]));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("Phone");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Member.add(this.panel);

        /// จัดการ
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.panel.setPreferredSize(new Dimension(180, 40));
        this.panel.setBackground(Color.decode(this.status[1]));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.label = new JLabel("Manage");
        this.label.setFont(this.font);
        this.label.setForeground(Color.WHITE);
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.panel.add(this.label);
        this.Member.add(this.panel);
    }

    public void create_body() {
        /// รายการสั่งซื้อ
        this.data = this.mange_m.GetMember(Integer.parseInt(this.status[3]));
        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(880, (120 * (this.data.length / 2))));
        this.btn_edit = new JButton[this.data.length];
        this.btn_del = new JButton[this.data.length];
        for (int i = 1; i < this.data.length; i++) {
            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.textField.setText("" + ((i - 1) + 1));
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setText(this.data[i][1]);
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setText(this.data[i][2]);
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.textField.setEditable(false);
            this.textField.setFont(this.font_textField);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setText(this.data[i][3]);
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.textField.setFont(this.font_textField);
            this.panel.add(this.textField);

            this.textField = new JTextField(8);
            this.textField.setEditable(false);
            this.textField.setText(this.data[i][4]);
            this.textField.setHorizontalAlignment(JTextField.CENTER);
            this.textField.setFont(this.font_textField);
            this.panel.add(this.textField);

            this.btn_edit[i] = new JButton("Edit");
            this.btn_edit[i].setFont(this.font_textField);
            this.btn_edit[i].setBackground(Color.decode("#f1c40f"));
            this.btn_edit[i].setForeground(Color.WHITE);
            this.btn_edit[i].setToolTipText("click to edit");
            this.btn_edit[i].setBorderPainted(false);
            this.btn_edit[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.btn_edit[i].setOpaque(true);
            this.btn_edit[i].setPreferredSize(new Dimension(80, 30));
            this.btn_edit[i].addActionListener(this);
            this.panel.add(this.btn_edit[i]);

            this.btn_del[i] = new JButton("Delete");
            this.btn_del[i].addActionListener(this);
            this.btn_del[i].setFont(this.font_textField);
            this.btn_del[i].setBackground(Color.decode("#e74c3c"));
            this.btn_del[i].setToolTipText("click to delete");
            this.btn_del[i].setForeground(Color.WHITE);
            this.btn_del[i].setBorderPainted(false);
            this.btn_del[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.btn_del[i].setOpaque(true);
            this.btn_del[i].setPreferredSize(new Dimension(100, 30));
            this.panel.add(this.btn_del[i]);
        }
        this.scrollPanel = new JScrollPane(this.panel);
        this.scrollPanel.setPreferredSize(new Dimension(1000, 400));
        this.Member.add(this.scrollPanel);

        this.Member.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Member.setSize(1200, 1113);
        this.Member.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btn_close) {
            this.time.stop();
            this.Member.setVisible(false);
            this.Member.dispose();
            new App();
        } else if (e.getSource() == this.btn_add) {
            /// check ค่าว่าง textfield
            if (this.gender.getSelectedIndex() != 0) {
                for (int i = 0; i < 3; i++) {
                    if (this.mem[i].getText().equals("")) {
                        JOptionPane.showMessageDialog(this.Member, "Please complete!", "error",
                                JOptionPane.ERROR_MESSAGE);
                        this.error = true;
                        break;
                    } else {
                        this.error = false;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this.Member, "please select gender!", "error",
                        JOptionPane.ERROR_MESSAGE);
                this.error = true;
            }

            if (!this.error) {
                if (this.checkType()) {
                    if (this.btn_add.getText().equals("Add" + this.status[2])) {
                        String[] add = { this.mem[0].getText().toString().trim(),
                                this.mem[1].getText().toString().trim(),
                                this.gender.getSelectedItem().toString(), this.mem[2].getText().toString().trim(),
                                this.status[3] };

                        if (this.mange_m.AddMember(add, Integer.parseInt(this.status[3]))) {
                            JOptionPane.showMessageDialog(this.Member, "add" + this.status[2] + "successfully!",
                                    "successfully",
                                    JOptionPane.INFORMATION_MESSAGE);
                            this.Member.setVisible(false);
                            this.Member.dispose();
                            this.start(Integer.parseInt(this.status[3]));
                        } else {
                            JOptionPane.showMessageDialog(this.Member, "An error occurred, please try again!",
                                    "error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (JOptionPane.showConfirmDialog(this.Member, "you want to edit" + this.status[2] + "or not?",
                                "Confirm",
                                JOptionPane.YES_NO_OPTION) == 0) {
                            String[] edit = { this.row, this.mem[0].getText(), this.mem[1].getText(),
                                    this.gender.getSelectedItem().toString(), this.mem[2].getText(), this.status[3] };
                            if (this.mange_m.EditMember(edit, Integer.parseInt(this.status[3]))) {
                                JOptionPane.showMessageDialog(this.Member, "Edit" + this.status[2] + "successfully!",
                                        "successfully",
                                        JOptionPane.INFORMATION_MESSAGE);
                                this.Member.setVisible(false);
                                this.Member.dispose();
                                this.start(Integer.parseInt(this.status[3]));
                            } else {
                                JOptionPane.showMessageDialog(this.Member, "An error occurred, please try again!",
                                        "error",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
                }
            }

        }
        for (

                int i = 0; i < this.data.length; i++) {
            /// แก้ไขสินค้า
            if (e.getSource() == btn_edit[i]) {
                this.row = this.data[i][0];
                this.mem[0].setText(this.data[i][1]);
                this.mem[1].setText(this.data[i][2]);
                this.mem[2].setText(this.data[i][4]);
                for (int j = 0; j < this.txt_gender.length; j++) {
                    if (this.txt_gender[j].equals(this.data[i][3])) {
                        this.gender.setSelectedIndex(j);
                    }
                }
                this.btn_add.setText("Edit");
                this.btn_add.setBackground(Color.decode("#ffb142"));
                this.time.start();
            } else if (e.getSource() == btn_del[i]) {
                this.row = this.data[i][0];
                if (JOptionPane.showConfirmDialog(this.Member, "you want to delete" + this.status[2] + "or not?",
                        "confirm",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    String[] del = { this.row, this.mem[0].getText(), this.mem[1].getText(),
                            this.gender.getSelectedItem().toString(), this.mem[2].getText(), this.status[3] };

                    if (this.mange_m.DeleteMember(del, Integer.parseInt(this.status[3]))) {
                        JOptionPane.showMessageDialog(this.Member, "Delete" + this.status[2] + "successfully!",
                                "successfully",
                                JOptionPane.INFORMATION_MESSAGE);
                        this.Member.setVisible(false);
                        this.Member.dispose();
                        this.start(Integer.parseInt(this.status[3]));
                    } else {
                        JOptionPane.showMessageDialog(this.Member, "An error occurred, please try again!",
                                "error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            /// check ค่าว่าง textfield
            if (this.mem[0].getText().isEmpty() && this.mem[1].getText().isEmpty() && this.mem[2].getText().isEmpty()
                    && this.gender.getSelectedIndex() == 0) {
                this.btn_add.setText("Add" + this.status[2]);
                this.btn_add.setBackground(Color.decode(this.status[1]));
                this.time.stop();
            }
        }
    }

    public boolean checkType() {
        try {
            if (this.mem[2].getText().toString().length() == 10) {

                for (int i = 0; i < 10; i++) {
                    Integer.parseInt(this.mem[2].getText().toString().substring(i, i + 1));
                }
                return true;
            } else {
                JOptionPane.showMessageDialog(this.Member, "Please fill phone number 10 character", "error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.Member, "Please fill phone number 10 character type  number only!",
                    "error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void border() {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1000, 1));
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.Member.add(this.panel);
    }

    public void newLine(int h) {
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(1200, h));
        this.Member.add(this.panel);
    }
}