public class Order {
    private Mange_o Mange_o = new Mange_o();
    private String[][] data;

    public void addOrder(String[] data) {
        this.Mange_o.setOrder(data);
    }

    public String[][] getOrder() {
        return this.Mange_o.getOrder();
    }

    public boolean deleteOrder() {
        return this.Mange_o.deleteOrder();
    }

    public int[] getCost() {
        return this.Mange_o.CaleCost();
    }

    public int pay(int money, int cost) {
        this.Mange_o.payment(cost);
        return money - cost;
    }
}
