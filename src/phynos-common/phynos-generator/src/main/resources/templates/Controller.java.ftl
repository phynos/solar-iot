package ${base_package}.${controller_module}.${module_group};

import ${base_package}.core.annotation.OperationRecord;
import ${base_package}.core.constant.BusinessType;

import ${base_package}.${controller_module}.BaseController;

import ${base_package}.core.json.JsonResult;
import ${base_package}.core.params.BaseParam;
import ${base_package}.core.params.IdParam;
import ${base_package}.core.service.${model}Service;
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
@RequestMapping("/api/sys/${model?uncap_first}")
public class ${model}Controller extends BaseController {

    @Autowired
    ${model}Service ${model?uncap_first}Service;

    @PostMapping("/list")
    public JsonResult list(BaseParam param) {
        return ${model?uncap_first}Service.list(param);
    }

    @PostMapping("/add")
    public JsonResult add(@RequestBody ${model} param) {
        return ${model?uncap_first}Service.add(param);
    }

    @PostMapping("/del")
    public JsonResult del(@RequestBody List<Long> ids) {
        return ${model?uncap_first}Service.del(ids);
    }

    @PostMapping("/mod")
    public JsonResult mod(@RequestBody ${model} param) {
        return ${model?uncap_first}Service.mod(param);
    }

    @PostMapping("/info")
    public JsonResult info(@RequestBody IdParam param) {
        return ${model?uncap_first}Service.info(param.getId());
    }

}