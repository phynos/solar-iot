package ${base_package}.core.service.${module_group}.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ${base_package}.core.json.JsonList;
import ${base_package}.core.json.JsonResult;
import ${base_package}.core.params.BaseParam;
import ${base_package}.core.service.BaseServiceImpl;
import ${base_package}.core.service.${module_group}.${model}Service;
import ${base_package}.dao.mapper.${model}Mapper;
import ${base_package}.dao.model.${model};
import ${base_package}.dao.model.${model}Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Author: ${author}
* @Date: ${date}
**/
@Service
public class ${model}ServiceImpl  extends BaseServiceImpl implements ${model}Service {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    ${model}Mapper ${model?uncap_first}Mapper;

    @Override
    public JsonResult list(BaseParam param) {
        Page page = PageHelper.startPage(param.getPageIndex(), param.getPageSize());
        ${model}Example ${model?uncap_first}Example = new ${model}Example();
        ${model}Example.Criteria criteria = ${model?uncap_first}Example.createCriteria();
        List<${model}> data = ${model?uncap_first}Mapper.selectByExample(${model?uncap_first}Example);
        long total = page.getTotal();
        PageHelper.clearPage();//一定要清理
        JsonList jl = JsonList.create(total, param.getPageIndex(), param.getPageSize(), data);
        return JsonResult.data(jl);
    }

    @Override
    public JsonResult add(${model} param) {
        ${model?uncap_first}Mapper.insertSelective(param);
        return OK;
    }

    @Override
    public JsonResult del(List<${primary_id_type}> ids) {
        ids.forEach(id -> ${model?uncap_first}Mapper.deleteByPrimaryKey(id));
        return OK;
    }

    @Override
    public JsonResult mod(${model} param) {
        ${model?uncap_first}Mapper.updateByPrimaryKey(param);
        return OK;
    }

    @Override
    public JsonResult info(${primary_id_type} id) {
        ${model} ${model?uncap_first} = ${model?uncap_first}Mapper.selectByPrimaryKey(id);
        return JsonResult.data(${model?uncap_first});
    }

}
