package com.phynos.springboot.webapi.model.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationlogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperationlogExample() {
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

        public Criteria andModuleTitleIsNull() {
            addCriterion("module_title is null");
            return (Criteria) this;
        }

        public Criteria andModuleTitleIsNotNull() {
            addCriterion("module_title is not null");
            return (Criteria) this;
        }

        public Criteria andModuleTitleEqualTo(String value) {
            addCriterion("module_title =", value, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleNotEqualTo(String value) {
            addCriterion("module_title <>", value, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleGreaterThan(String value) {
            addCriterion("module_title >", value, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleGreaterThanOrEqualTo(String value) {
            addCriterion("module_title >=", value, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleLessThan(String value) {
            addCriterion("module_title <", value, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleLessThanOrEqualTo(String value) {
            addCriterion("module_title <=", value, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleLike(String value) {
            addCriterion("module_title like", value, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleNotLike(String value) {
            addCriterion("module_title not like", value, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleIn(List<String> values) {
            addCriterion("module_title in", values, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleNotIn(List<String> values) {
            addCriterion("module_title not in", values, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleBetween(String value1, String value2) {
            addCriterion("module_title between", value1, value2, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andModuleTitleNotBetween(String value1, String value2) {
            addCriterion("module_title not between", value1, value2, "moduleTitle");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNull() {
            addCriterion("action_name is null");
            return (Criteria) this;
        }

        public Criteria andActionNameIsNotNull() {
            addCriterion("action_name is not null");
            return (Criteria) this;
        }

        public Criteria andActionNameEqualTo(String value) {
            addCriterion("action_name =", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotEqualTo(String value) {
            addCriterion("action_name <>", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThan(String value) {
            addCriterion("action_name >", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameGreaterThanOrEqualTo(String value) {
            addCriterion("action_name >=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThan(String value) {
            addCriterion("action_name <", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLessThanOrEqualTo(String value) {
            addCriterion("action_name <=", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameLike(String value) {
            addCriterion("action_name like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotLike(String value) {
            addCriterion("action_name not like", value, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameIn(List<String> values) {
            addCriterion("action_name in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotIn(List<String> values) {
            addCriterion("action_name not in", values, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameBetween(String value1, String value2) {
            addCriterion("action_name between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andActionNameNotBetween(String value1, String value2) {
            addCriterion("action_name not between", value1, value2, "actionName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNull() {
            addCriterion("method_name is null");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNotNull() {
            addCriterion("method_name is not null");
            return (Criteria) this;
        }

        public Criteria andMethodNameEqualTo(String value) {
            addCriterion("method_name =", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotEqualTo(String value) {
            addCriterion("method_name <>", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThan(String value) {
            addCriterion("method_name >", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThanOrEqualTo(String value) {
            addCriterion("method_name >=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThan(String value) {
            addCriterion("method_name <", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThanOrEqualTo(String value) {
            addCriterion("method_name <=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLike(String value) {
            addCriterion("method_name like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotLike(String value) {
            addCriterion("method_name not like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIn(List<String> values) {
            addCriterion("method_name in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotIn(List<String> values) {
            addCriterion("method_name not in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameBetween(String value1, String value2) {
            addCriterion("method_name between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotBetween(String value1, String value2) {
            addCriterion("method_name not between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodParamsIsNull() {
            addCriterion("method_params is null");
            return (Criteria) this;
        }

        public Criteria andMethodParamsIsNotNull() {
            addCriterion("method_params is not null");
            return (Criteria) this;
        }

        public Criteria andMethodParamsEqualTo(String value) {
            addCriterion("method_params =", value, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsNotEqualTo(String value) {
            addCriterion("method_params <>", value, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsGreaterThan(String value) {
            addCriterion("method_params >", value, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsGreaterThanOrEqualTo(String value) {
            addCriterion("method_params >=", value, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsLessThan(String value) {
            addCriterion("method_params <", value, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsLessThanOrEqualTo(String value) {
            addCriterion("method_params <=", value, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsLike(String value) {
            addCriterion("method_params like", value, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsNotLike(String value) {
            addCriterion("method_params not like", value, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsIn(List<String> values) {
            addCriterion("method_params in", values, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsNotIn(List<String> values) {
            addCriterion("method_params not in", values, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsBetween(String value1, String value2) {
            addCriterion("method_params between", value1, value2, "methodParams");
            return (Criteria) this;
        }

        public Criteria andMethodParamsNotBetween(String value1, String value2) {
            addCriterion("method_params not between", value1, value2, "methodParams");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Boolean value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Boolean value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Boolean value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Boolean value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Boolean> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Boolean> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOperUserIdIsNull() {
            addCriterion("oper_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOperUserIdIsNotNull() {
            addCriterion("oper_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserIdEqualTo(Integer value) {
            addCriterion("oper_user_id =", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdNotEqualTo(Integer value) {
            addCriterion("oper_user_id <>", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdGreaterThan(Integer value) {
            addCriterion("oper_user_id >", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("oper_user_id >=", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdLessThan(Integer value) {
            addCriterion("oper_user_id <", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("oper_user_id <=", value, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdIn(List<Integer> values) {
            addCriterion("oper_user_id in", values, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdNotIn(List<Integer> values) {
            addCriterion("oper_user_id not in", values, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdBetween(Integer value1, Integer value2) {
            addCriterion("oper_user_id between", value1, value2, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("oper_user_id not between", value1, value2, "operUserId");
            return (Criteria) this;
        }

        public Criteria andOperUrlIsNull() {
            addCriterion("oper_URL is null");
            return (Criteria) this;
        }

        public Criteria andOperUrlIsNotNull() {
            addCriterion("oper_URL is not null");
            return (Criteria) this;
        }

        public Criteria andOperUrlEqualTo(String value) {
            addCriterion("oper_URL =", value, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlNotEqualTo(String value) {
            addCriterion("oper_URL <>", value, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlGreaterThan(String value) {
            addCriterion("oper_URL >", value, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlGreaterThanOrEqualTo(String value) {
            addCriterion("oper_URL >=", value, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlLessThan(String value) {
            addCriterion("oper_URL <", value, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlLessThanOrEqualTo(String value) {
            addCriterion("oper_URL <=", value, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlLike(String value) {
            addCriterion("oper_URL like", value, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlNotLike(String value) {
            addCriterion("oper_URL not like", value, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlIn(List<String> values) {
            addCriterion("oper_URL in", values, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlNotIn(List<String> values) {
            addCriterion("oper_URL not in", values, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlBetween(String value1, String value2) {
            addCriterion("oper_URL between", value1, value2, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperUrlNotBetween(String value1, String value2) {
            addCriterion("oper_URL not between", value1, value2, "operUrl");
            return (Criteria) this;
        }

        public Criteria andOperIpIsNull() {
            addCriterion("oper_IP is null");
            return (Criteria) this;
        }

        public Criteria andOperIpIsNotNull() {
            addCriterion("oper_IP is not null");
            return (Criteria) this;
        }

        public Criteria andOperIpEqualTo(String value) {
            addCriterion("oper_IP =", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotEqualTo(String value) {
            addCriterion("oper_IP <>", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpGreaterThan(String value) {
            addCriterion("oper_IP >", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpGreaterThanOrEqualTo(String value) {
            addCriterion("oper_IP >=", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpLessThan(String value) {
            addCriterion("oper_IP <", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpLessThanOrEqualTo(String value) {
            addCriterion("oper_IP <=", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpLike(String value) {
            addCriterion("oper_IP like", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotLike(String value) {
            addCriterion("oper_IP not like", value, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpIn(List<String> values) {
            addCriterion("oper_IP in", values, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotIn(List<String> values) {
            addCriterion("oper_IP not in", values, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpBetween(String value1, String value2) {
            addCriterion("oper_IP between", value1, value2, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperIpNotBetween(String value1, String value2) {
            addCriterion("oper_IP not between", value1, value2, "operIp");
            return (Criteria) this;
        }

        public Criteria andOperLocationIsNull() {
            addCriterion("oper_location is null");
            return (Criteria) this;
        }

        public Criteria andOperLocationIsNotNull() {
            addCriterion("oper_location is not null");
            return (Criteria) this;
        }

        public Criteria andOperLocationEqualTo(String value) {
            addCriterion("oper_location =", value, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationNotEqualTo(String value) {
            addCriterion("oper_location <>", value, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationGreaterThan(String value) {
            addCriterion("oper_location >", value, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationGreaterThanOrEqualTo(String value) {
            addCriterion("oper_location >=", value, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationLessThan(String value) {
            addCriterion("oper_location <", value, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationLessThanOrEqualTo(String value) {
            addCriterion("oper_location <=", value, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationLike(String value) {
            addCriterion("oper_location like", value, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationNotLike(String value) {
            addCriterion("oper_location not like", value, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationIn(List<String> values) {
            addCriterion("oper_location in", values, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationNotIn(List<String> values) {
            addCriterion("oper_location not in", values, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationBetween(String value1, String value2) {
            addCriterion("oper_location between", value1, value2, "operLocation");
            return (Criteria) this;
        }

        public Criteria andOperLocationNotBetween(String value1, String value2) {
            addCriterion("oper_location not between", value1, value2, "operLocation");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNull() {
            addCriterion("error_msg is null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNotNull() {
            addCriterion("error_msg is not null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgEqualTo(String value) {
            addCriterion("error_msg =", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotEqualTo(String value) {
            addCriterion("error_msg <>", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThan(String value) {
            addCriterion("error_msg >", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThanOrEqualTo(String value) {
            addCriterion("error_msg >=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThan(String value) {
            addCriterion("error_msg <", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThanOrEqualTo(String value) {
            addCriterion("error_msg <=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLike(String value) {
            addCriterion("error_msg like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotLike(String value) {
            addCriterion("error_msg not like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIn(List<String> values) {
            addCriterion("error_msg in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotIn(List<String> values) {
            addCriterion("error_msg not in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgBetween(String value1, String value2) {
            addCriterion("error_msg between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotBetween(String value1, String value2) {
            addCriterion("error_msg not between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeIsNull() {
            addCriterion("operation_datetime is null");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeIsNotNull() {
            addCriterion("operation_datetime is not null");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeEqualTo(Date value) {
            addCriterion("operation_datetime =", value, "operationDatetime");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeNotEqualTo(Date value) {
            addCriterion("operation_datetime <>", value, "operationDatetime");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeGreaterThan(Date value) {
            addCriterion("operation_datetime >", value, "operationDatetime");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operation_datetime >=", value, "operationDatetime");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeLessThan(Date value) {
            addCriterion("operation_datetime <", value, "operationDatetime");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("operation_datetime <=", value, "operationDatetime");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeIn(List<Date> values) {
            addCriterion("operation_datetime in", values, "operationDatetime");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeNotIn(List<Date> values) {
            addCriterion("operation_datetime not in", values, "operationDatetime");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeBetween(Date value1, Date value2) {
            addCriterion("operation_datetime between", value1, value2, "operationDatetime");
            return (Criteria) this;
        }

        public Criteria andOperationDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("operation_datetime not between", value1, value2, "operationDatetime");
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