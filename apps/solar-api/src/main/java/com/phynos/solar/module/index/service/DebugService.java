package com.phynos.solar.module.index.service;

import com.phynos.solar.module.index.vo.PersonVO;
import com.phynos.solar.util.json.R;

import java.util.List;

/**
 * @author by lupc
 * @date 2020-12-18 14:06
 */
public interface DebugService {

    R<?> test();


    List<PersonVO> testCache(String name);

    void clearCache(String name);

}
