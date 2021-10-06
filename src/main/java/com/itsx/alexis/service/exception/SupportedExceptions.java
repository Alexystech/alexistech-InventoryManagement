package com.itsx.alexis.service.exception;

import org.springframework.http.HttpStatus;

public enum SupportedExceptions {

    ADMINISTRATOR_NOT_FOUND(AdministratorNotFoundException.class, HttpStatus.NOT_FOUND),
    ADMINISTRATOR_IS_NULL(AdministratorIsNullException.class, HttpStatus.UNPROCESSABLE_ENTITY),
    CATEGORY_NOT_FOUND(CategoryNotFoundException.class, HttpStatus.NOT_FOUND),
    CATEGORY_IS_NULL(CategoryIsNullException.class, HttpStatus.UNPROCESSABLE_ENTITY),
    PRODUCT_NOT_FOUND(ProductNotFoundException.class, HttpStatus.NOT_FOUND),
    PRODUCT_IS_NULL(ProductIsNullException.class, HttpStatus.UNPROCESSABLE_ENTITY),
    SUPPLIER_NOT_FOUND(SupplierNotFoundException.class, HttpStatus.NOT_FOUND),
    SUPPLIER_IS_NULL(SupplierIsNullException.class, HttpStatus.UNPROCESSABLE_ENTITY);

    private final Class<? extends Throwable> exceptionClass;
    private final HttpStatus httpStatus;

    SupportedExceptions(Class<? extends Throwable> exceptionClass, HttpStatus httpStatus) {
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Class<? extends Throwable> getExceptionClass() {
        return this.exceptionClass;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

}
