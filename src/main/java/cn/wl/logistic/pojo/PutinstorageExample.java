package cn.wl.logistic.pojo;

import java.util.ArrayList;
import java.util.List;

public class PutinstorageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PutinstorageExample() {
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

        public Criteria andTakeExpenseIsNull() {
            addCriterion("take_expense is null");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseIsNotNull() {
            addCriterion("take_expense is not null");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseEqualTo(Double value) {
            addCriterion("take_expense =", value, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseNotEqualTo(Double value) {
            addCriterion("take_expense <>", value, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseGreaterThan(Double value) {
            addCriterion("take_expense >", value, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseGreaterThanOrEqualTo(Double value) {
            addCriterion("take_expense >=", value, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseLessThan(Double value) {
            addCriterion("take_expense <", value, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseLessThanOrEqualTo(Double value) {
            addCriterion("take_expense <=", value, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseIn(List<Double> values) {
            addCriterion("take_expense in", values, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseNotIn(List<Double> values) {
            addCriterion("take_expense not in", values, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseBetween(Double value1, Double value2) {
            addCriterion("take_expense between", value1, value2, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andTakeExpenseNotBetween(Double value1, Double value2) {
            addCriterion("take_expense not between", value1, value2, "takeExpense");
            return (Criteria) this;
        }

        public Criteria andStoreClerkIsNull() {
            addCriterion("store_clerk is null");
            return (Criteria) this;
        }

        public Criteria andStoreClerkIsNotNull() {
            addCriterion("store_clerk is not null");
            return (Criteria) this;
        }

        public Criteria andStoreClerkEqualTo(String value) {
            addCriterion("store_clerk =", value, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkNotEqualTo(String value) {
            addCriterion("store_clerk <>", value, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkGreaterThan(String value) {
            addCriterion("store_clerk >", value, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkGreaterThanOrEqualTo(String value) {
            addCriterion("store_clerk >=", value, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkLessThan(String value) {
            addCriterion("store_clerk <", value, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkLessThanOrEqualTo(String value) {
            addCriterion("store_clerk <=", value, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkLike(String value) {
            addCriterion("store_clerk like", value, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkNotLike(String value) {
            addCriterion("store_clerk not like", value, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkIn(List<String> values) {
            addCriterion("store_clerk in", values, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkNotIn(List<String> values) {
            addCriterion("store_clerk not in", values, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkBetween(String value1, String value2) {
            addCriterion("store_clerk between", value1, value2, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andStoreClerkNotBetween(String value1, String value2) {
            addCriterion("store_clerk not between", value1, value2, "storeClerk");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceIsNull() {
            addCriterion("volumn_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceIsNotNull() {
            addCriterion("volumn_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceEqualTo(Double value) {
            addCriterion("volumn_unit_price =", value, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceNotEqualTo(Double value) {
            addCriterion("volumn_unit_price <>", value, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceGreaterThan(Double value) {
            addCriterion("volumn_unit_price >", value, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("volumn_unit_price >=", value, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceLessThan(Double value) {
            addCriterion("volumn_unit_price <", value, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceLessThanOrEqualTo(Double value) {
            addCriterion("volumn_unit_price <=", value, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceIn(List<Double> values) {
            addCriterion("volumn_unit_price in", values, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceNotIn(List<Double> values) {
            addCriterion("volumn_unit_price not in", values, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceBetween(Double value1, Double value2) {
            addCriterion("volumn_unit_price between", value1, value2, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andVolumnUnitPriceNotBetween(Double value1, Double value2) {
            addCriterion("volumn_unit_price not between", value1, value2, "volumnUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceIsNull() {
            addCriterion("weight_unit_price is null");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceIsNotNull() {
            addCriterion("weight_unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceEqualTo(Double value) {
            addCriterion("weight_unit_price =", value, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceNotEqualTo(Double value) {
            addCriterion("weight_unit_price <>", value, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceGreaterThan(Double value) {
            addCriterion("weight_unit_price >", value, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("weight_unit_price >=", value, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceLessThan(Double value) {
            addCriterion("weight_unit_price <", value, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceLessThanOrEqualTo(Double value) {
            addCriterion("weight_unit_price <=", value, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceIn(List<Double> values) {
            addCriterion("weight_unit_price in", values, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceNotIn(List<Double> values) {
            addCriterion("weight_unit_price not in", values, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceBetween(Double value1, Double value2) {
            addCriterion("weight_unit_price between", value1, value2, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWeightUnitPriceNotBetween(Double value1, Double value2) {
            addCriterion("weight_unit_price not between", value1, value2, "weightUnitPrice");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIsNull() {
            addCriterion("warehouse_id is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIsNotNull() {
            addCriterion("warehouse_id is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdEqualTo(Integer value) {
            addCriterion("warehouse_id =", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotEqualTo(Integer value) {
            addCriterion("warehouse_id <>", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThan(Integer value) {
            addCriterion("warehouse_id >", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id >=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThan(Integer value) {
            addCriterion("warehouse_id <", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id <=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIn(List<Integer> values) {
            addCriterion("warehouse_id in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotIn(List<Integer> values) {
            addCriterion("warehouse_id not in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id not between", value1, value2, "warehouseId");
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