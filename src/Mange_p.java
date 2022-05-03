
public class Mange_p extends Sql {

    // private String product[]; /// สินค้า

    public boolean AddProduct(String pro[]) {
        String product[] = { "" + (this.select("DB/Products/pro.txt").length + 1) + "," + pro[0] + "," + pro[1] + ","
                + pro[2] + "," + pro[3] };
        return this.insert(product, "DB/Products/pro.txt");
    }

    public String[][] GetProduct() {
        return this.select("DB/Products/pro.txt");
    }

    public boolean EditProduct(String pro[]) {
        return this.update(pro, "DB/Products/pro.txt");
    }

    public boolean DeleteProduct(String pro[]) {
        return this.delete(pro, "DB/Products/pro.txt");
    }

}