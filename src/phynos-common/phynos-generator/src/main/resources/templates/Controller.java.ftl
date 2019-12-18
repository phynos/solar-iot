package ${package_name}.controller;

import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.web.api.controller.BaseController;
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