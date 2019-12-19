package ${controller_package};

import ${base_package}.core.annotation.OperationRecord;
import ${base_package}.core.constant.BusinessType;
import ${base_package}.core.json.JsonResult;
import ${base_package}.core.params.IdStringParam;

import ${controller_package}.BaseController;
import ${service_package}.${model}Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public JsonResult list() {
        return ${model?uncap_first}Service.list();
    }

    @PostMapping("/add")
    public JsonResult add(@RequestBody ${model} param) {
        return ${model?uncap_first}Service.add(param);
    }

    @PostMapping("/del")
    public JsonResult del(@RequestBody List<Integer> ids) {
        return ${model?uncap_first}Service.del(ids);
    }

    @PostMapping("/mod")
    public JsonResult mod(@RequestBody ${model} param) {
        return ${model?uncap_first}Service.mod(param);
    }

    @PostMapping("/info")
    public JsonResult info(@RequestBody IdParam id) {
        return ${model?uncap_first}Service.info(id);
    }

}