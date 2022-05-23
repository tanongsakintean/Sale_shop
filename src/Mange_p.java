
public class Mange_p extends Sql {
    private String[][] data;

    public boolean AddProduct(String pro[]) {
        String product[] = {
                "" + (this.maxId(this.select("DB/Products/pro.txt")) + 1) + "," + pro[0] + "," + pro[1] + ","
                        + this.addCategory(pro[2], "DB/Products/Category/cate.txt") + "," + pro[3] };
        return this.insert(product, "DB/Products/pro.txt");
    }

    public String[][] GetCategory() {
        return this.select("DB/Products/Category/cate.txt");
    }

    public void setProduct() {
        this.data = new String[this.select("DB/Products/pro.txt").length][this.select("DB/Products/pro.txt")[0].length];
        for (int i = 0; i < this.data.length; i++) {
            this.data[i][0] = this.select("DB/Products/pro.txt")[i][0];
            this.data[i][1] = this.select("DB/Products/pro.txt")[i][1];
            this.data[i][2] = this.select("DB/Products/pro.txt")[i][2];

            this.data[i][3] = this.select("DB/Products/Category/cate.txt")[Integer
                    .parseInt(this.select("DB/Products/pro.txt")[i][3])][1];

            this.data[i][4] = this.select("DB/Products/pro.txt")[i][4];
        }
    }

    public String[][] GetProduct() {

        if (this.select("DB/Products/pro.txt").length == 0) {
            String product[] = { "0", "choose Product", "0", "0", "0" };
            this.insert(product, "DB/Products/pro.txt");
            String cate1[] = { "0", "choose Category", "0" };
            this.insert(cate1, "DB/Products/Category/cate.txt");
            String cate2[] = { "1", "all products", "0" };
            this.insert(cate2, "DB/Products/Category/cate.txt");
        }
        this.setProduct();
        return this.data;

    }

    public boolean EditProduct(String pro[]) {
        this.DeleteCategory(Integer.parseInt(this.select("DB/Products/pro.txt")[Integer.parseInt(pro[0])][3]));
        String product[] = { pro[0], pro[1], pro[2], "" + this.addCategory(pro[3], "DB/Products/Category/cate.txt"),
                pro[4] };
        return this.update(product, "DB/Products/pro.txt");
    }

    public int CountCate(String key) {
        int count = 0;
        for (int i = 0; i < this.select("DB/Products/Category/cate.txt").length; i++) {
            if (this.select("DB/Products/Category/cate.txt")[i][1].equals(key)) {
                count = Integer.parseInt(this.select("DB/Products/Category/cate.txt")[i][2]);
            }
        }
        return count;
    }

    public void DeleteCategory(int key) {

        String path = "DB/Products/Category/cate.txt";
        this.data = this.select(path);
        int low = 0, high = this.select(path).length - 1, middle;
        while (low <= high) {
            middle = (low + high) / 2;
            if (Integer.parseInt(this.data[middle][0]) == key) {
                if (Integer.parseInt(this.data[middle][2]) > 1) {
                    String cate[] = { "" + this.data[middle][0], "" + this.data[middle][1],
                            "" + (Integer.parseInt(this.data[middle][2]) - 1) };
                    this.update(cate, path);
                    break;
                } else if (Integer.parseInt(this.data[middle][2]) == 1) {
                    String[] cate = { this.select(path)[middle][0],
                            this.select(path)[middle][1], "1" };
                    this.delete(cate, path);
                    break;
                }
            } else if (key < Integer.parseInt(this.data[middle][0])) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

    }

    public boolean DeleteProduct(String[] pro) {
        this.DeleteCategory(Integer.parseInt(this.select("DB/Products/pro.txt")[Integer.parseInt(pro[0])][3]));
        return this.delete(pro, "DB/Products/pro.txt");
    }

}