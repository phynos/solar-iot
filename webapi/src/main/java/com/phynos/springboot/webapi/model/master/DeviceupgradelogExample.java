package com.phynos.springboot.webapi.model.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceupgradelogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceupgradelogExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeIsNull() {
            addCriterion("created_datetime is null");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeIsNotNull() {
            addCriterion("created_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeEqualTo(Date value) {
            addCriterion("created_datetime =", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeNotEqualTo(Date value) {
            addCriterion("created_datetime <>", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeGreaterThan(Date value) {
            addCriterion("created_datetime >", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_datetime >=", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeLessThan(Date value) {
            addCriterion("created_datetime <", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("created_datetime <=", value, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeIn(List<Date> values) {
            addCriterion("created_datetime in", values, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeNotIn(List<Date> values) {
            addCriterion("created_datetime not in", values, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeBetween(Date value1, Date value2) {
            addCriterion("created_datetime between", value1, value2, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andCreatedDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("created_datetime not between", value1, value2, "createdDatetime");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNull() {
            addCriterion("device_id is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIsNotNull() {
            addCriterion("device_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceIdEqualTo(Integer value) {
            addCriterion("device_id =", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotEqualTo(Integer value) {
            addCriterion("device_id <>", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThan(Integer value) {
            addCriterion("device_id >", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_id >=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThan(Integer value) {
            addCriterion("device_id <", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdLessThanOrEqualTo(Integer value) {
            addCriterion("device_id <=", value, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdIn(List<Integer> values) {
            addCriterion("device_id in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotIn(List<Integer> values) {
            addCriterion("device_id not in", values, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdBetween(Integer value1, Integer value2) {
            addCriterion("device_id between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andDeviceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("device_id not between", value1, value2, "deviceId");
            return (Criteria) this;
        }

        public Criteria andStutus0IsNull() {
            addCriterion("stutus_0 is null");
            return (Criteria) this;
        }

        public Criteria andStutus0IsNotNull() {
            addCriterion("stutus_0 is not null");
            return (Criteria) this;
        }

        public Criteria andStutus0EqualTo(Integer value) {
            addCriterion("stutus_0 =", value, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus0NotEqualTo(Integer value) {
            addCriterion("stutus_0 <>", value, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus0GreaterThan(Integer value) {
            addCriterion("stutus_0 >", value, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus0GreaterThanOrEqualTo(Integer value) {
            addCriterion("stutus_0 >=", value, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus0LessThan(Integer value) {
            addCriterion("stutus_0 <", value, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus0LessThanOrEqualTo(Integer value) {
            addCriterion("stutus_0 <=", value, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus0In(List<Integer> values) {
            addCriterion("stutus_0 in", values, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus0NotIn(List<Integer> values) {
            addCriterion("stutus_0 not in", values, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus0Between(Integer value1, Integer value2) {
            addCriterion("stutus_0 between", value1, value2, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus0NotBetween(Integer value1, Integer value2) {
            addCriterion("stutus_0 not between", value1, value2, "stutus0");
            return (Criteria) this;
        }

        public Criteria andStutus1IsNull() {
            addCriterion("stutus_1 is null");
            return (Criteria) this;
        }

        public Criteria andStutus1IsNotNull() {
            addCriterion("stutus_1 is not null");
            return (Criteria) this;
        }

        public Criteria andStutus1EqualTo(Integer value) {
            addCriterion("stutus_1 =", value, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus1NotEqualTo(Integer value) {
            addCriterion("stutus_1 <>", value, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus1GreaterThan(Integer value) {
            addCriterion("stutus_1 >", value, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus1GreaterThanOrEqualTo(Integer value) {
            addCriterion("stutus_1 >=", value, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus1LessThan(Integer value) {
            addCriterion("stutus_1 <", value, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus1LessThanOrEqualTo(Integer value) {
            addCriterion("stutus_1 <=", value, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus1In(List<Integer> values) {
            addCriterion("stutus_1 in", values, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus1NotIn(List<Integer> values) {
            addCriterion("stutus_1 not in", values, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus1Between(Integer value1, Integer value2) {
            addCriterion("stutus_1 between", value1, value2, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus1NotBetween(Integer value1, Integer value2) {
            addCriterion("stutus_1 not between", value1, value2, "stutus1");
            return (Criteria) this;
        }

        public Criteria andStutus2IsNull() {
            addCriterion("stutus_2 is null");
            return (Criteria) this;
        }

        public Criteria andStutus2IsNotNull() {
            addCriterion("stutus_2 is not null");
            return (Criteria) this;
        }

        public Criteria andStutus2EqualTo(Integer value) {
            addCriterion("stutus_2 =", value, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus2NotEqualTo(Integer value) {
            addCriterion("stutus_2 <>", value, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus2GreaterThan(Integer value) {
            addCriterion("stutus_2 >", value, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus2GreaterThanOrEqualTo(Integer value) {
            addCriterion("stutus_2 >=", value, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus2LessThan(Integer value) {
            addCriterion("stutus_2 <", value, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus2LessThanOrEqualTo(Integer value) {
            addCriterion("stutus_2 <=", value, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus2In(List<Integer> values) {
            addCriterion("stutus_2 in", values, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus2NotIn(List<Integer> values) {
            addCriterion("stutus_2 not in", values, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus2Between(Integer value1, Integer value2) {
            addCriterion("stutus_2 between", value1, value2, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus2NotBetween(Integer value1, Integer value2) {
            addCriterion("stutus_2 not between", value1, value2, "stutus2");
            return (Criteria) this;
        }

        public Criteria andStutus3IsNull() {
            addCriterion("stutus_3 is null");
            return (Criteria) this;
        }

        public Criteria andStutus3IsNotNull() {
            addCriterion("stutus_3 is not null");
            return (Criteria) this;
        }

        public Criteria andStutus3EqualTo(Integer value) {
            addCriterion("stutus_3 =", value, "stutus3");
            return (Criteria) this;
        }

        public Criteria andStutus3NotEqualTo(Integer value) {
            addCriterion("stutus_3 <>", value, "stutus3");
            return (Criteria) this;
        }

        public Criteria andStutus3GreaterThan(Integer value) {
            addCriterion("stutus_3 >", value, "stutus3");
            return (Criteria) this;
        }

        public Criteria andStutus3GreaterThanOrEqualTo(Integer value) {
            addCriterion("stutus_3 >=", value, "stutus3");
            return (Criteria) this;
        }

        public Criteria andStutus3LessThan(Integer value) {
            addCriterion("stutus_3 <", value, "stutus3");
            return (Criteria) this;
        }

        public Criteria andStutus3LessThanOrEqualTo(Integer value) {
            addCriterion("stutus_3 <=", value, "stutus3");
            return (Criteria) this;
        }

        public Criteria andStutus3In(List<Integer> values) {
            addCriterion("stutus_3 in", values, "stutus3");
            return (Criteria) this;
        }

        public Criteria andStutus3NotIn(List<Integer> values) {
            addCriterion("stutus_3 not in", values, "stutus3");
            return (Criteria) this;
        }

        public Criteria andStutus3Between(Integer value1, Integer value2) {
            addCriterion("stutus_3 between", value1, value2, "stutus3");
            return (Criteria) this;
        }

        public Criteria andStutus3NotBetween(Integer value1, Integer value2) {
            addCriterion("stutus_3 not between", value1, value2, "stutus3");
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