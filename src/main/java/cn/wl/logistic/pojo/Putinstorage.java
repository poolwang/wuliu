package cn.wl.logistic.pojo;

import java.util.List;

public class Putinstorage {
    private Long orderId;

    private Double takeExpense;

    private String storeClerk;

    private Double volumnUnitPrice;

    private Double weightUnitPrice;

    private Integer warehouseId;
    
    private List<OrderDetail>  orderDetails;
    
    @Override
	public String toString() {
		return "Putinstorage [orderId=" + orderId + ", takeExpense=" + takeExpense + ", storeClerk=" + storeClerk
				+ ", volumnUnitPrice=" + volumnUnitPrice + ", weightUnitPrice=" + weightUnitPrice + ", warehouseId="
				+ warehouseId + ", orderDetails=" + orderDetails + "]";
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

    public Double getTakeExpense() {
        return takeExpense;
    }

    public void setTakeExpense(Double takeExpense) {
        this.takeExpense = takeExpense;
    }

    public String getStoreClerk() {
        return storeClerk;
    }

    public void setStoreClerk(String storeClerk) {
        this.storeClerk = storeClerk;
    }

    public Double getVolumnUnitPrice() {
        return volumnUnitPrice;
    }

    public void setVolumnUnitPrice(Double volumnUnitPrice) {
        this.volumnUnitPrice = volumnUnitPrice;
    }

    public Double getWeightUnitPrice() {
        return weightUnitPrice;
    }

    public void setWeightUnitPrice(Double weightUnitPrice) {
        this.weightUnitPrice = weightUnitPrice;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
}