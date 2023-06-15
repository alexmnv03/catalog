package com.alex.catalog.error;

import com.alex.catalog.error.constant.ErrorConstant;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id, ErrorConstant debugMessage) {
        super(debugMessage.getName() + " with id equal to " + id);
    }

    public NotFoundException(String name, ErrorConstant debugMessage) {
        super(debugMessage.getName() + " with name equal to " + name);
    }
    
}
