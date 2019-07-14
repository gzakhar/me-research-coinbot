package domain;

public class Trades {
    private String time;
    private Double quantity;
    private Double rate;

    @Override
    public String toString() {
        return "Trades{" +
                "time='" + time + '\'' +
                ", quantity=" + quantity +
                ", rate=" + rate +
                ", takeSide='" + takeSide + '\'' +
                '}';
    }

    public Trades(String time, Double quantity, Double rate, String takeSide) {
        this.time = time;
        this.quantity = quantity;
        this.rate = rate;
        this.takeSide = takeSide;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTakeSide() {
        return takeSide;
    }

    public void setTakeSide(String takeSide) {
        this.takeSide = takeSide;
    }

    private String takeSide;
}