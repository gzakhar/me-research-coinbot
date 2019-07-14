package domain;

public class Summary {
    private String symbol;
    private Double high;
    private Double low;
    private Double volume;
    private Double baseVolume;
    private Double percentChange;
    private String updateAt;

    public Summary(String symbol, Double high, Double low, Double volume, Double baseVolume, Double percentChange, String updateAt) {
        this.symbol = symbol;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.baseVolume = baseVolume;
        this.percentChange = percentChange;
        this.updateAt = updateAt;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(Double baseVolume) {
        this.baseVolume = baseVolume;
    }

    public Double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(Double percentChange) {
        this.percentChange = percentChange;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "summary{" +
                "symbol='" + symbol + '\'' +
                ", high=" + high +
                ", low=" + low +
                ", volume=" + volume +
                ", baseVolume=" + baseVolume +
                ", percentChange=" + percentChange +
                ", updateAt=" + updateAt +
                '}';
    }
}
