package com.phynos.solar.distributed.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author by lupc
 * @date 2021-02-25 14:22
 */
public class Converter {

    public static byte[] objectToByte(Object object) {
        byte[] bytes = null;
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(object);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();


        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return bytes;
    }

}
