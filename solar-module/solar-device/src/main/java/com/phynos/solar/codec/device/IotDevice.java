package com.phynos.solar.codec.device;

import com.phynos.solar.codec.product.IotProduct;

public interface IotDevice extends IotProduct {

    String getSn();

    void refresh(String key, String value);

}
