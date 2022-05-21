public class Mange_s extends Sql {

    private int count = 0;

    public String[][] getSales() {
        return this.select("DB/Sales/sales.txt");

    }

    public int countSearch(String key) {
        String[][] sales = this.select("DB/Sales/sales.txt");

        for (int i = 0; i < sales.length; i++) {
            if (sales[i][3].equals(key)) {
                this.count++;
            }

        }
        return this.count;
    }

    public String[][] search(String key) {
        /// search and return data equal key
        String[][] sales = this.select("DB/Sales/sales.txt");
        String[][] data = new String[this.countSearch(key)][5];
        for (int i = 0; i < sales.length; i++) {
            if (sales[i][3].equals(key)) {
                data[i][0] = sales[i][0];
                data[i][1] = sales[i][1];
                data[i][2] = sales[i][2];
                data[i][3] = sales[i][3];
                data[i][4] = sales[i][4];
            }

        }
        return data;

    }

}