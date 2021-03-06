package cn.wl.logistic.pojo;

import java.util.ArrayList;
import java.util.List;

public class OfferExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OfferExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdIsNull() {
            addCriterion("order_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdIsNotNull() {
            addCriterion("order_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdEqualTo(Long value) {
            addCriterion("order_detail_id =", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdNotEqualTo(Long value) {
            addCriterion("order_detail_id <>", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdGreaterThan(Long value) {
            addCriterion("order_detail_id >", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_detail_id >=", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdLessThan(Long value) {
            addCriterion("order_detail_id <", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("order_detail_id <=", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdIn(List<Long> values) {
            addCriterion("order_detail_id in", values, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdNotIn(List<Long> values) {
            addCriterion("order_detail_id not in", values, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdBetween(Long value1, Long value2) {
            addCriterion("order_detail_id between", value1, value2, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("order_detail_id not between", value1, value2, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceIsNull() {
            addCriterion("offer_volumn_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceIsNotNull() {
            addCriterion("offer_volumn_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceEqualTo(Double value) {
            addCriterion("offer_volumn_unit_price =", value, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceNotEqualTo(Double value) {
            addCriterion("offer_volumn_unit_price <>", value, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceGreaterThan(Double value) {
            addCriterion("offer_volumn_unit_price >", value, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("offer_volumn_unit_price >=", value, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceLessThan(Double value) {
            addCriterion("offer_volumn_unit_price <", value, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceLessThanOrEqualTo(Double value) {
            addCriterion("offer_volumn_unit_price <=", value, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceIn(List<Double> values) {
            addCriterion("offer_volumn_unit_price in", values, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceNotIn(List<Double> values) {
            addCriterion("offer_volumn_unit_price not in", values, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceBetween(Double value1, Double value2) {
            addCriterion("offer_volumn_unit_price between", value1, value2, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferVolumnUnitPriceNotBetween(Double value1, Double value2) {
            addCriterion("offer_volumn_unit_price not between", value1, value2, "offerVolumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceIsNull() {
            addCriterion("offer_weight_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceIsNotNull() {
            addCriterion("offer_weight_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceEqualTo(Double value) {
            addCriterion("offer_weight_unit_price =", value, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceNotEqualTo(Double value) {
            addCriterion("offer_weight_unit_price <>", value, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceGreaterThan(Double value) {
            addCriterion("offer_weight_unit_price >", value, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("offer_weight_unit_price >=", value, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceLessThan(Double value) {
            addCriterion("offer_weight_unit_price <", value, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceLessThanOrEqualTo(Double value) {
            addCriterion("offer_weight_unit_price <=", value, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceIn(List<Double> values) {
            addCriterion("offer_weight_unit_price in", values, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceNotIn(List<Double> values) {
            addCriterion("offer_weight_unit_price not in", values, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceBetween(Double value1, Double value2) {
            addCriterion("offer_weight_unit_price between", value1, value2, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferWeightUnitPriceNotBetween(Double value1, Double value2) {
            addCriterion("offer_weight_unit_price not between", value1, value2, "offerWeightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseIsNull() {
            addCriterion("offer_take_expense is null");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseIsNotNull() {
            addCriterion("offer_take_expense is not null");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseEqualTo(Double value) {
            addCriterion("offer_take_expense =", value, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseNotEqualTo(Double value) {
            addCriterion("offer_take_expense <>", value, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseGreaterThan(Double value) {
            addCriterion("offer_take_expense >", value, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseGreaterThanOrEqualTo(Double value) {
            addCriterion("offer_take_expense >=", value, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseLessThan(Double value) {
            addCriterion("offer_take_expense <", value, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseLessThanOrEqualTo(Double value) {
            addCriterion("offer_take_expense <=", value, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseIn(List<Double> values) {
            addCriterion("offer_take_expense in", values, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseNotIn(List<Double> values) {
            addCriterion("offer_take_expense not in", values, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseBetween(Double value1, Double value2) {
            addCriterion("offer_take_expense between", value1, value2, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferTakeExpenseNotBetween(Double value1, Double value2) {
            addCriterion("offer_take_expense not between", value1, value2, "offerTakeExpense");
            return (Criteria) this;
        }

        public Criteria andOfferValumnIsNull() {
            addCriterion("offer_valumn is null");
            return (Criteria) this;
        }

        public Criteria andOfferValumnIsNotNull() {
            addCriterion("offer_valumn is not null");
            return (Criteria) this;
        }

        public Criteria andOfferValumnEqualTo(Double value) {
            addCriterion("offer_valumn =", value, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferValumnNotEqualTo(Double value) {
            addCriterion("offer_valumn <>", value, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferValumnGreaterThan(Double value) {
            addCriterion("offer_valumn >", value, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferValumnGreaterThanOrEqualTo(Double value) {
            addCriterion("offer_valumn >=", value, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferValumnLessThan(Double value) {
            addCriterion("offer_valumn <", value, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferValumnLessThanOrEqualTo(Double value) {
            addCriterion("offer_valumn <=", value, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferValumnIn(List<Double> values) {
            addCriterion("offer_valumn in", values, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferValumnNotIn(List<Double> values) {
            addCriterion("offer_valumn not in", values, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferValumnBetween(Double value1, Double value2) {
            addCriterion("offer_valumn between", value1, value2, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferValumnNotBetween(Double value1, Double value2) {
            addCriterion("offer_valumn not between", value1, value2, "offerValumn");
            return (Criteria) this;
        }

        public Criteria andOfferWeightIsNull() {
            addCriterion("offer_weight is null");
            return (Criteria) this;
        }

        public Criteria andOfferWeightIsNotNull() {
            addCriterion("offer_weight is not null");
            return (Criteria) this;
        }

        public Criteria andOfferWeightEqualTo(Double value) {
            addCriterion("offer_weight =", value, "offerWeight");
            return (Criteria) this;
        }

        public Criteria andOfferWeightNotEqualTo(Double value) {
            addCriterion("offer_weight <>", value, "offerWeight");
            return (Criteria) this;
        }

        public Criteria andOfferWeightGreaterThan(Double value) {
            addCriterion("offer_weight >", value, "offerWeight");
            return (Criteria) this;
        }

        public Criteria andOfferWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("offer_weight >=", value, "offerWeight");
            return (Criteria) this;
        }

        public Criteria andOfferWeightLessThan(Double value) {
            addCriterion("offer_weight <", value, "offerWeight");
            return (Criteria) this;
        }

        public Criteria andOfferWeightLessThanOrEqualTo(Double value) {
            addCriterion("offer_weight <=", value, "offerWeight");
            return (Criteria) this;
        }

        public Criteria andOfferWeightIn(List<Double> values) {
            addCriterion("offer_weight in", values, "offerWeight");
            return (Criteria) this;
        }

        public Criteria andOfferWeightNotIn(List<Double> values) {
            addCriterion("offer_weight not in", values, "offerWeight");
            return (Criteria) this;
        }

        public Criteria andOfferWeightBetween(Double value1, Double value2) {
            addCriterion("offer_weight between", value1, value2, "offerWeight");
            return (Criteria) this;
        }

        public Criteria andOfferWeightNotBetween(Double value1, Double value2) {
            addCriterion("offer_weight not between", value1, value2, "offerWeight");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}