package cn.wl.logistic.pojo;

import java.util.List;

public class Offer {
    private Long orderId;

    private Long orderDetailId;

    private Double offerVolumnUnitPrice;

    private Double offerWeightUnitPrice;

    private Double offerTakeExpense;

    private Double offerValumn;

    private Double offerWeight;
    
    private List<OrderDetail> orderDetails;
    
    @Override
	public String toString() {
		return "Offer [orderId=" + orderId + ", orderDetailId=" + orderDetailId + ", offerVolumnUnitPrice="
				+ offerVolumnUnitPrice + ", offerWeightUnitPrice=" + offerWeightUnitPrice + ", offerTakeExpense="
				+ offerTakeExpense + ", offerValumn=" + offerValumn + ", offerWeight=" + offerWeight + ", orderDetails="
				+ orderDetails + "]";
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Double getOfferVolumnUnitPrice() {
        return offerVolumnUnitPrice;
    }

    public void setOfferVolumnUnitPrice(Double offerVolumnUnitPrice) {
        this.offerVolumnUnitPrice = offerVolumnUnitPrice;
    }

    public Double getOfferWeightUnitPrice() {
        return offerWeightUnitPrice;
    }

    public void setOfferWeightUnitPrice(Double offerWeightUnitPrice) {
        this.offerWeightUnitPrice = offerWeightUnitPrice;
    }

    public Double getOfferTakeExpense() {
        return offerTakeExpense;
    }

    public void setOfferTakeExpense(Double offerTakeExpense) {
        this.offerTakeExpense = offerTakeExpense;
    }

    public Double getOfferValumn() {
        return offerValumn;
    }

    public void setOfferValumn(Double offerValumn) {
        this.offerValumn = offerValumn;
    }

    public Double getOfferWeight() {
        return offerWeight;
    }

    public void setOfferWeight(Double offerWeight) {
        this.offerWeight = offerWeight;
    }
}