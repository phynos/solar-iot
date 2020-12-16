package ${base_package}.${controller_module}.${module_group};

import ${base_package}.core.annotation.OperationRecord;
import ${base_package}.core.constant.BusinessType;

import ${base_package}.${controller_module}.BaseController;

import ${base_package}.core.json.JsonResult;
import ${base_package}.core.params.BaseParam;
import ${base_package}.core.params.IdParam;
import ${base_package}.core.service.${module_group}.${model}Service;
import ${base_package}.dao.model.${model};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Author: ${author}
* @Date: ${date}
**/
@RestController
@RequestMapping("/api/${module_group}/${model?uncap_first}")
@Api(value="${model_chs}管理",tags={"${model_chs}管理"})
public class ${model}Controller extends BaseController {

    @Autowired
    ${model}Service ${model?uncap_first}Service;

    @PostMapping("/list")
    public JsonResult list(BaseParam param) {
        return ${model?uncap_first}Service.list(param);
    }

    @PostMapping("/add")
    @OperationRecord(module = "${model_chs}管理",action = BusinessType.INSERT)
    @ApiOperation(value = "${model_chs}新增",notes = "${model_chs}新增")
    public JsonResult add(@RequestBody ${model} param) {
        return ${model?uncap_first}Service.add(param);
    }

    @PostMapping("/del")
    @OperationRecord(module = "${model_chs}管理",action = BusinessType.DELETE)
    @ApiOperation(value = "${model_chs}删除",notes = "${model_chs}删除")
    public JsonResult del(@RequestBody List<${primary_id_type}> ids) {
        return ${model?uncap_first}Service.del(ids);
    }

    @PostMapping("/mod")
    @OperationRecord(module = "${model_chs}管理",action = BusinessType.UPDATE)
    @ApiOperation(value = "${model_chs}修改",notes = "${model_chs}修改")
    public JsonResult mod(@RequestBody ${model} param) {
        return ${model?uncap_first}Service.mod(param);
    }

    @PostMapping("/info")
    public JsonResult info(@RequestBody IdParam<${primary_id_type}> param) {
        return ${model?uncap_first}Service.info(param.getId());
    }

}