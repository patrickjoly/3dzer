/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/printer/Printer.e.vm.java
 */
package com.dddzer.printer;

import static org.apache.commons.lang.StringUtils.isBlank;

import javax.inject.Named;
import javax.inject.Singleton;

import java.util.Locale;

import com.dddzer.domain.ProductType;
import com.dddzer.printer.DiscoverablePrinter;

/**
 * {@link org.springframework.format.Printer} for {@link ProductType} 
 *
 * @see org.springframework.format.Printer
 * @see DiscoverablePrinter
 * @see TypeAwarePrinter
 */
@Named
@Singleton
public class ProductTypePrinter extends DiscoverablePrinter<ProductType> {
    public ProductTypePrinter() {
        super(ProductType.class);
    }

    @Override
    public String print(ProductType productType, Locale locale) {
        if (productType == null) {
            return "";
        }
        StringBuilder ret = new StringBuilder(256);
        if (!isBlank(productType.getName())) {
            if (ret.length() != 0) {
                ret.append('/');
            }
            ret.append(productType.getName().trim());
        }
        return ret.toString();
    }
}
