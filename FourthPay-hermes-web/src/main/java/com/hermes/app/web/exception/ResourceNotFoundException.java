package com.hermes.app.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 404资源找不到异常
 *
 * @author of546
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private Object resourceId;

    public ResourceNotFoundException(Object resourceId) {
        this.resourceId = resourceId;
    }

    public Object getResourceId() {
        return resourceId;
    }

}
