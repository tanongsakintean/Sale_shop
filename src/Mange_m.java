public class Mange_m extends Sql {

    public boolean AddMember(String mem[], int status) {
        if (status == 1) {
            String member[] = {
                    "" + (this.maxId(this.select("DB/Members/customer.txt")) + 1) + "," + mem[0] + "," + mem[1] + ","
                            + mem[2] + "," + mem[3] + "," + mem[4] };
            return this.insert(member, "DB/Members/customer.txt");
        } else {
            String member[] = {
                    "" + (this.maxId(this.select("DB/Members/saler.txt")) + 1) + "," + mem[0] + "," + mem[1] + ","
                            + mem[2] + "," + mem[3] + "," + mem[4] };
            return this.insert(member, "DB/Members/saler.txt");
        }
    }

    public String[][] GetMember(int status) {
        if (status == 1) {
            if (this.select("DB/Members/customer.txt").length == 0) {
                String member[] = { "0", "GUEST", "", "0", "0", "0" };
                this.insert(member, "DB/Members/customer.txt");
            }
            return this.select("DB/Members/customer.txt");
        } else {
            if (this.select("DB/Members/saler.txt").length == 0) {
                String member[] = { "0", "Saler", "", "0", "0", "0" };
                this.insert(member, "DB/Members/saler.txt");
            }
            return this.select("DB/Members/saler.txt");
        }
    }

    public boolean EditMember(String mem[], int status) {
        if (status == 1) {
            return this.update(mem, "DB/Members/customer.txt");
        } else {
            return this.update(mem, "DB/Members/saler.txt");
        }
    }

    public boolean DeleteMember(String mem[], int status) {
        if (status == 1) {
            return this.delete(mem, "DB/Members/customer.txt");
        } else {
            return this.delete(mem, "DB/Members/saler.txt");
        }
    }

}