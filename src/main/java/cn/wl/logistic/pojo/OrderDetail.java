package cn.wl.logistic.pojo;

public class OrderDetail {
	private Long orderDetailId;

    private Long orderId;

    private String goodsName;

    private Integer goodsNumber;

    private Long goodsUnit;

    private Long goodsUnitPrice;

    private Long goodsTotal;

    private String goodsRemark;

    private Double cargoLong;

    private Double cargoWeight;

    private Double cargoHeight;

    private Double valumn;

    private Double weight;
    
    private Double offerValumn;
    
    private Double offerWeight;
    
    private String goodsUnitName;
    
    private String unit;
    
    @Override
  	public String toString() {
  		return "OrderDetail [orderDetailId=" + orderDetailId + ", orderId=" + orderId + ", goodsName=" + goodsName
  				+ ", goodsNumber=" + goodsNumber + ", goodsUnit=" + goodsUnit + ", goodsUnitPrice=" + goodsUnitPrice
  				+ ", goodsTotal=" + goodsTotal + ", goodsRemark=" + goodsRemark + ", cargoLong=" + cargoLong
  				+ ", cargoWeight=" + cargoWeight + ", cargoHeight=" + cargoHeight + ", valumn=" + valumn + ", weight="
  				+ weight + ", offerValumn=" + offerValumn + ", offerWeight=" + offerWeight + ", goodsUnitName="
  				+ goodsUnitName + ", unit=" + unit + "]";
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

	public String getGoodsUnitName() {
		return goodsUnitName;
	}

	public void setGoodsUnitName(String goodsUnitName) {
		this.goodsUnitName = goodsUnitName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Long getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(Long goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Long getGoodsUnitPrice() {
        return goodsUnitPrice;
    }

    public void setGoodsUnitPrice(Long goodsUnitPrice) {
        this.goodsUnitPrice = goodsUnitPrice;
    }

    public Long getGoodsTotal() {
        return goodsTotal;
    }

    public void setGoodsTotal(Long goodsTotal) {
        this.goodsTotal = goodsTotal;
    }

    public String getGoodsRemark() {
        return goodsRemark;
    }

    public void setGoodsRemark(String goodsRemark) {
        this.goodsRemark = goodsRemark;
    }

    public Double getCargoLong() {
        return cargoLong;
    }

    public void setCargoLong(Double cargoLong) {
        this.cargoLong = cargoLong;
    }

    public Double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public Double getCargoHeight() {
        return cargoHeight;
    }

    public void setCargoHeight(Double cargoHeight) {
        this.cargoHeight = cargoHeight;
    }

    public Double getValumn() {
        return valumn;
    }

    public void setValumn(Double valumn) {
        this.valumn = valumn;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
}