package ${base_package}.core.core.service;

import ${base_package}.core.json.JsonResult;

/**
* @Author: Lupc
* @Date: 2019/11/27 14:38
**/
public interface ${model}Service {

    JsonResult list();

    JsonResult add(${model} param);

    JsonResult del(List<Integer> ids);

    JsonResult mod(${model} param);

    JsonResult info(String id);

}