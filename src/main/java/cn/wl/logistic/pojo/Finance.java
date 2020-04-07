package cn.wl.logistic.pojo;

public class Finance {
    private Long orderId;

    private Integer taxRate;

    private Double totalVolumn;

    private Double totalWeight;

    private Double totalValue;

    private Double volumnExpense;

    private Double weightExpense;

    private Double rateExpense;

    private Double otherExpense;

    private Double totalExpense;
    
    private Double offerTakeExpense;
    
    

    public Double getOfferTakeExpense() {
		return offerTakeExpense;
	}

	public void setOfferTakeExpense(Double offerTakeExpense) {
		this.offerTakeExpense = offerTakeExpense;
	}

	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTotalVolumn() {
        return totalVolumn;
    }

    public void setTotalVolumn(Double totalVolumn) {
        this.totalVolumn = totalVolumn;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getVolumnExpense() {
        return volumnExpense;
    }

    public void setVolumnExpense(Double volumnExpense) {
        this.volumnExpense = volumnExpense;
    }

    public Double getWeightExpense() {
        return weightExpense;
    }

    public void setWeightExpense(Double weightExpense) {
        this.weightExpense = weightExpense;
    }

    public Double getRateExpense() {
        return rateExpense;
    }

    public void setRateExpense(Double rateExpense) {
        this.rateExpense = rateExpense;
    }

    public Double getOtherExpense() {
        return otherExpense;
    }

    public void setOtherExpense(Double otherExpense) {
        this.otherExpense = otherExpense;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}