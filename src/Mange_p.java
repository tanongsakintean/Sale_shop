
public class Mange_p extends Sql {
    private String[][] data;

    public boolean AddProduct(String pro[]) {
        if (this.select("DB/Product/product.txt").length != 0) {
            String product[] = {
                    "" + (this.maxId(this.select("DB/Products/pro.txt")) + 1) + "," + pro[0] + "," + pro[1] + ","
                            + this.addCategory(pro[2], "DB/Products/Category/cate.txt") + "," + pro[3] };
            return this.insert(product, "DB/Products/pro.txt");
        } else {
            String category[] = { "0", "เลือกประเภทสินค้า" };
            this.insert(category, "DB/Products/Category/cate.txt");
            String product[] = { "0", "เลือกสินค้า", "0", "0", "0" };
            return this.insert(product, "DB/Products/pro.txt");

        }

    }

    public String[][] GetCategory() {
        return this.select("DB/Products/Category/cate.txt");
    }

    public String[][] GetProduct() {

        if (this.select("DB/Products/pro.txt").length == 0) {
            String product[] = { "0", "เลือกสินค้า", "0", "0", "0" };
            this.insert(product, "DB/Products/pro.txt");
            String category[] = { "0", "เลือกประเภทสินค้า" };
            this.insert(category, "DB/Products/Category/cate.txt");
        }
        this.setProduct();
        return this.data;

    }

    public void setProduct() {
        this.data = new String[this.select("DB/Products/pro.txt").length][this.select("DB/Products/pro.txt")[0].length];
        for (int i = 0; i < data.length; i++) {
            data[i][0] = this.select("DB/Products/pro.txt")[i][0];
            data[i][1] = this.select("DB/Products/pro.txt")[i][1];
            data[i][2] = this.select("DB/Products/pro.txt")[i][2];
            data[i][3] = this.select(
                    "DB/Products/Category/cate.txt")[Integer.parseInt(this.select("DB/Products/pro.txt")[i][3])][1];
            data[i][4] = this.select("DB/Products/pro.txt")[i][4];
        }
    }

    public boolean EditProduct(String pro[]) {
        return this.update(pro, "DB/Products/pro.txt");
    }

    public boolean DeleteProduct(String pro[]) {
        /// ต้องลบประเภทสินค้าด้วยถ้าสินค้าประเภทนี้นหมดแล้ว
        return this.delete(pro, "DB/Products/pro.txt");
    }
}