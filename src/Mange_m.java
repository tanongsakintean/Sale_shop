public class Mange_m extends Sql {

    public boolean AddMember(String mem[]) {
        String product[] = { "" + (this.select("DB/Members/mem.txt").length + 1) + "," + mem[0] + "," + mem[1] + ","
                + mem[2] + "," + mem[3] + "," + mem[4] };
        return this.insert(product, "DB/Members/mem.txt");
    }

    public String[][] GetMember() {
        return this.select("DB/Members/mem.txt");
    }

    public boolean EditMember(String mem[]) {
        return this.update(mem, "DB/Members/mem.txt");
    }

    public boolean DeleteMember(String mem[]) {
        return this.delete(mem, "DB/Members/mem.txt");
    }

}