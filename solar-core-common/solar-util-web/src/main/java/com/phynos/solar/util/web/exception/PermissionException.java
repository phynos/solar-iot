package com.phynos.solar.util.web.exception;

/**
 * @author lupc
 * @date 2021/5/24 16:31
 */
public class PermissionException extends RuntimeException {

    public PermissionException() {
        super();
    }

    public PermissionException(String s) {
        super(s);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionException(Throwable cause) {
        super(cause);
    }

}
