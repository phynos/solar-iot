package com.phynos.framework.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DictTypeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public DictTypeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameIsNull() {
            addCriterion("dict_type_name is null");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameIsNotNull() {
            addCriterion("dict_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameEqualTo(String value) {
            addCriterion("dict_type_name =", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotEqualTo(String value) {
            addCriterion("dict_type_name <>", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameGreaterThan(String value) {
            addCriterion("dict_type_name >", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("dict_type_name >=", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameLessThan(String value) {
            addCriterion("dict_type_name <", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameLessThanOrEqualTo(String value) {
            addCriterion("dict_type_name <=", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameLike(String value) {
            addCriterion("dict_type_name like", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotLike(String value) {
            addCriterion("dict_type_name not like", value, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameIn(List<String> values) {
            addCriterion("dict_type_name in", values, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotIn(List<String> values) {
            addCriterion("dict_type_name not in", values, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameBetween(String value1, String value2) {
            addCriterion("dict_type_name between", value1, value2, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeNameNotBetween(String value1, String value2) {
            addCriterion("dict_type_name not between", value1, value2, "dictTypeName");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyIsNull() {
            addCriterion("dict_type_key is null");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyIsNotNull() {
            addCriterion("dict_type_key is not null");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyEqualTo(String value) {
            addCriterion("dict_type_key =", value, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyNotEqualTo(String value) {
            addCriterion("dict_type_key <>", value, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyGreaterThan(String value) {
            addCriterion("dict_type_key >", value, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyGreaterThanOrEqualTo(String value) {
            addCriterion("dict_type_key >=", value, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyLessThan(String value) {
            addCriterion("dict_type_key <", value, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyLessThanOrEqualTo(String value) {
            addCriterion("dict_type_key <=", value, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyLike(String value) {
            addCriterion("dict_type_key like", value, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyNotLike(String value) {
            addCriterion("dict_type_key not like", value, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyIn(List<String> values) {
            addCriterion("dict_type_key in", values, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyNotIn(List<String> values) {
            addCriterion("dict_type_key not in", values, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyBetween(String value1, String value2) {
            addCriterion("dict_type_key between", value1, value2, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andDictTypeKeyNotBetween(String value1, String value2) {
            addCriterion("dict_type_key not between", value1, value2, "dictTypeKey");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`status` not between", value1, value2, "status");
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

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_dict_type
     *
     * @mbg.generated do_not_delete_during_merge Thu Sep 26 09:31:07 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sys_dict_type
     *
     * @mbg.generated Thu Sep 26 09:31:07 CST 2019
     */
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