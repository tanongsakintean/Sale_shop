import java.util.Date;
import java.text.*;

public class Mange_o extends Sql {
    DecimalFormat DecimalFormat = new DecimalFormat("#,###,###,###");
    private String[][] pro = this.select("DB/Products/pro.txt");
    private String[][] data;
    private String cus = "";

    public void setOrder(String[] data) {
        String[] pro_detail = new String[3];
        for (int i = 0; i < this.pro.length; i++) {
            if (this.pro[i][1].equals(data[2])) {
                pro_detail[0] = this.pro[i][2];
                pro_detail[1] = this.pro[i][4];
                pro_detail[2] = this.select("DB/Products/Category/cate.txt")[Integer.parseInt(this.pro[i][3])][1];
            }

        }
        String order[] = { "" + (this.maxId(this.select("DB/Orders/order.txt")) + 1), "" + pro_detail[0], "" + data[2],
                "" + pro_detail[2], "" + data[0], "" + pro_detail[1], "" + data[3] };
        this.insert(order, "DB/Orders/order.txt");
    }

    public String[][] getOrder() {
        if (this.select("DB/Orders/order.txt").length == 0) {
            String order[] = { "0", "0", "0", "0", "0", "0", "0" };
            this.insert(order, "DB/Orders/order.txt");
        }
        String[][] order = this.select("DB/Orders/order.txt");
        this.data = new String[order.length][order[0].length];

        for (int i = 0; i < this.data.length; i++) {
            this.data[i][0] = order[i][0];
            this.data[i][1] = order[i][1];
            this.data[i][2] = order[i][2];
            this.data[i][3] = order[i][3];
            this.data[i][4] = order[i][4];
            this.data[i][5] = order[i][5];
            this.data[i][6] = order[i][6];
        }
        return this.data;

    }

    public boolean deleteOrder() {
        return this.deleteFile("DB/Orders/order.txt");
    }

    public int[] CaleCost() {
        int cost[] = new int[2];
        for (int i = 1; i < this.data.length; i++) {
            if (this.data[i][6].equals("0")) {
                cost[1] += 0;
                this.data[i][6] = "GUEST";
            } else {
                if (Integer.parseInt(this.data[i][4]) >= 5) {
                    cost[1] += 40;
                } else if (Integer.parseInt(this.data[i][4]) >= 10) {
                    cost[1] += 100;
                } else {
                    cost[1] += 20;
                }

            }
            cost[0] += (Integer.parseInt(this.data[i][5]) * Integer.parseInt(this.data[i][4]));
        }
        return cost;
    }

    public int countCustomer() {
        int count = 0, same;
        String[] nameCustormer = new String[this.data.length];

        for (int i = 1; i < this.data.length; i++) {
            same = 1;

            for (int j = 0; j < nameCustormer.length; j++)
                if (this.data[i][6].equals(nameCustormer[j]))
                    same = 0;

            if (same == 1) {
                nameCustormer[count++] = this.data[i][6];
                this.cus += this.data[i][6] + ",";
            }
        }

        return count;

    }

    public void payment(int cost) {

        for (int i = 0; i < countCustomer(); i++) {
            int amount = 0, countDiscount = 0, id = 0;
            for (int j = 1; j < this.data.length; j++) {
                if (cus.split(",")[i].equals(this.data[j][6])) {
                    id = Integer.parseInt(this.data[j][0]);
                    amount += Integer.parseInt(this.data[j][4]);
                    if (this.data[j][6].equals("GUEST")) {
                        countDiscount += 0;
                    } else {
                        if (Integer.parseInt(this.data[j][4]) >= 5) {
                            countDiscount += 40;
                        } else if (Integer.parseInt(this.data[j][4]) >= 10) {
                            countDiscount += 100;
                        } else {
                            countDiscount += 20;
                        }
                    }

                }
            }

            String[] payment = { "" + (this.maxId(this.select("DB/Sales/sales.txt")) +
                    1), this.data[id][6],
                    "" + amount,
                    "" + new SimpleDateFormat("dd/MM/yyyy").format(new Date()),
                    "" + ((amount * Integer.parseInt(this.data[id][5])) - countDiscount) };
            this.insert(payment, "DB/Sales/sales.txt");
        }
    }

}
