package cn.wl.logistic.pojo;

import java.util.ArrayList;
import java.util.List;

public class FinanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinanceExample() {
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

        public Criteria andTaxRateIsNull() {
            addCriterion("tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(Integer value) {
            addCriterion("tax_rate =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(Integer value) {
            addCriterion("tax_rate <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(Integer value) {
            addCriterion("tax_rate >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("tax_rate >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(Integer value) {
            addCriterion("tax_rate <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(Integer value) {
            addCriterion("tax_rate <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<Integer> values) {
            addCriterion("tax_rate in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<Integer> values) {
            addCriterion("tax_rate not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(Integer value1, Integer value2) {
            addCriterion("tax_rate between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(Integer value1, Integer value2) {
            addCriterion("tax_rate not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnIsNull() {
            addCriterion("total_volumn is null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnIsNotNull() {
            addCriterion("total_volumn is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnEqualTo(Double value) {
            addCriterion("total_volumn =", value, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnNotEqualTo(Double value) {
            addCriterion("total_volumn <>", value, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnGreaterThan(Double value) {
            addCriterion("total_volumn >", value, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnGreaterThanOrEqualTo(Double value) {
            addCriterion("total_volumn >=", value, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnLessThan(Double value) {
            addCriterion("total_volumn <", value, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnLessThanOrEqualTo(Double value) {
            addCriterion("total_volumn <=", value, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnIn(List<Double> values) {
            addCriterion("total_volumn in", values, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnNotIn(List<Double> values) {
            addCriterion("total_volumn not in", values, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnBetween(Double value1, Double value2) {
            addCriterion("total_volumn between", value1, value2, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalVolumnNotBetween(Double value1, Double value2) {
            addCriterion("total_volumn not between", value1, value2, "totalVolumn");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIsNull() {
            addCriterion("total_weight is null");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIsNotNull() {
            addCriterion("total_weight is not null");
            return (Criteria) this;
        }

        public Criteria andTotalWeightEqualTo(Double value) {
            addCriterion("total_weight =", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotEqualTo(Double value) {
            addCriterion("total_weight <>", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightGreaterThan(Double value) {
            addCriterion("total_weight >", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("total_weight >=", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLessThan(Double value) {
            addCriterion("total_weight <", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLessThanOrEqualTo(Double value) {
            addCriterion("total_weight <=", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIn(List<Double> values) {
            addCriterion("total_weight in", values, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotIn(List<Double> values) {
            addCriterion("total_weight not in", values, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightBetween(Double value1, Double value2) {
            addCriterion("total_weight between", value1, value2, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotBetween(Double value1, Double value2) {
            addCriterion("total_weight not between", value1, value2, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalValueIsNull() {
            addCriterion("total_value is null");
            return (Criteria) this;
        }

        public Criteria andTotalValueIsNotNull() {
            addCriterion("total_value is not null");
            return (Criteria) this;
        }

        public Criteria andTotalValueEqualTo(Double value) {
            addCriterion("total_value =", value, "totalValue");
            return (Criteria) this;
        }

        public Criteria andTotalValueNotEqualTo(Double value) {
            addCriterion("total_value <>", value, "totalValue");
            return (Criteria) this;
        }

        public Criteria andTotalValueGreaterThan(Double value) {
            addCriterion("total_value >", value, "totalValue");
            return (Criteria) this;
        }

        public Criteria andTotalValueGreaterThanOrEqualTo(Double value) {
            addCriterion("total_value >=", value, "totalValue");
            return (Criteria) this;
        }

        public Criteria andTotalValueLessThan(Double value) {
            addCriterion("total_value <", value, "totalValue");
            return (Criteria) this;
        }

        public Criteria andTotalValueLessThanOrEqualTo(Double value) {
            addCriterion("total_value <=", value, "totalValue");
            return (Criteria) this;
        }

        public Criteria andTotalValueIn(List<Double> values) {
            addCriterion("total_value in", values, "totalValue");
            return (Criteria) this;
        }

        public Criteria andTotalValueNotIn(List<Double> values) {
            addCriterion("total_value not in", values, "totalValue");
            return (Criteria) this;
        }

        public Criteria andTotalValueBetween(Double value1, Double value2) {
            addCriterion("total_value between", value1, value2, "totalValue");
            return (Criteria) this;
        }

        public Criteria andTotalValueNotBetween(Double value1, Double value2) {
            addCriterion("total_value not between", value1, value2, "totalValue");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseIsNull() {
            addCriterion("volumn_expense is null");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseIsNotNull() {
            addCriterion("volumn_expense is not null");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseEqualTo(Double value) {
            addCriterion("volumn_expense =", value, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseNotEqualTo(Double value) {
            addCriterion("volumn_expense <>", value, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseGreaterThan(Double value) {
            addCriterion("volumn_expense >", value, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseGreaterThanOrEqualTo(Double value) {
            addCriterion("volumn_expense >=", value, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseLessThan(Double value) {
            addCriterion("volumn_expense <", value, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseLessThanOrEqualTo(Double value) {
            addCriterion("volumn_expense <=", value, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseIn(List<Double> values) {
            addCriterion("volumn_expense in", values, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseNotIn(List<Double> values) {
            addCriterion("volumn_expense not in", values, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseBetween(Double value1, Double value2) {
            addCriterion("volumn_expense between", value1, value2, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andVolumnExpenseNotBetween(Double value1, Double value2) {
            addCriterion("volumn_expense not between", value1, value2, "volumnExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseIsNull() {
            addCriterion("weight_expense is null");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseIsNotNull() {
            addCriterion("weight_expense is not null");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseEqualTo(Double value) {
            addCriterion("weight_expense =", value, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseNotEqualTo(Double value) {
            addCriterion("weight_expense <>", value, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseGreaterThan(Double value) {
            addCriterion("weight_expense >", value, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseGreaterThanOrEqualTo(Double value) {
            addCriterion("weight_expense >=", value, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseLessThan(Double value) {
            addCriterion("weight_expense <", value, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseLessThanOrEqualTo(Double value) {
            addCriterion("weight_expense <=", value, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseIn(List<Double> values) {
            addCriterion("weight_expense in", values, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseNotIn(List<Double> values) {
            addCriterion("weight_expense not in", values, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseBetween(Double value1, Double value2) {
            addCriterion("weight_expense between", value1, value2, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andWeightExpenseNotBetween(Double value1, Double value2) {
            addCriterion("weight_expense not between", value1, value2, "weightExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseIsNull() {
            addCriterion("rate_expense is null");
            return (Criteria) this;
        }

        public Criteria andRateExpenseIsNotNull() {
            addCriterion("rate_expense is not null");
            return (Criteria) this;
        }

        public Criteria andRateExpenseEqualTo(Double value) {
            addCriterion("rate_expense =", value, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseNotEqualTo(Double value) {
            addCriterion("rate_expense <>", value, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseGreaterThan(Double value) {
            addCriterion("rate_expense >", value, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseGreaterThanOrEqualTo(Double value) {
            addCriterion("rate_expense >=", value, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseLessThan(Double value) {
            addCriterion("rate_expense <", value, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseLessThanOrEqualTo(Double value) {
            addCriterion("rate_expense <=", value, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseIn(List<Double> values) {
            addCriterion("rate_expense in", values, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseNotIn(List<Double> values) {
            addCriterion("rate_expense not in", values, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseBetween(Double value1, Double value2) {
            addCriterion("rate_expense between", value1, value2, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andRateExpenseNotBetween(Double value1, Double value2) {
            addCriterion("rate_expense not between", value1, value2, "rateExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseIsNull() {
            addCriterion("other_expense is null");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseIsNotNull() {
            addCriterion("other_expense is not null");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseEqualTo(Double value) {
            addCriterion("other_expense =", value, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseNotEqualTo(Double value) {
            addCriterion("other_expense <>", value, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseGreaterThan(Double value) {
            addCriterion("other_expense >", value, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseGreaterThanOrEqualTo(Double value) {
            addCriterion("other_expense >=", value, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseLessThan(Double value) {
            addCriterion("other_expense <", value, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseLessThanOrEqualTo(Double value) {
            addCriterion("other_expense <=", value, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseIn(List<Double> values) {
            addCriterion("other_expense in", values, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseNotIn(List<Double> values) {
            addCriterion("other_expense not in", values, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseBetween(Double value1, Double value2) {
            addCriterion("other_expense between", value1, value2, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andOtherExpenseNotBetween(Double value1, Double value2) {
            addCriterion("other_expense not between", value1, value2, "otherExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseIsNull() {
            addCriterion("total_expense is null");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseIsNotNull() {
            addCriterion("total_expense is not null");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseEqualTo(Double value) {
            addCriterion("total_expense =", value, "totalExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseNotEqualTo(Double value) {
            addCriterion("total_expense <>", value, "totalExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseGreaterThan(Double value) {
            addCriterion("total_expense >", value, "totalExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseGreaterThanOrEqualTo(Double value) {
            addCriterion("total_expense >=", value, "totalExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseLessThan(Double value) {
            addCriterion("total_expense <", value, "totalExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseLessThanOrEqualTo(Double value) {
            addCriterion("total_expense <=", value, "totalExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseIn(List<Double> values) {
            addCriterion("total_expense in", values, "totalExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseNotIn(List<Double> values) {
            addCriterion("total_expense not in", values, "totalExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseBetween(Double value1, Double value2) {
            addCriterion("total_expense between", value1, value2, "totalExpense");
            return (Criteria) this;
        }

        public Criteria andTotalExpenseNotBetween(Double value1, Double value2) {
            addCriterion("total_expense not between", value1, value2, "totalExpense");
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