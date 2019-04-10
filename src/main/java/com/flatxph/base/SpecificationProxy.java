package com.flatxph.base;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

public class SpecificationProxy<T> {
    public static <T> Specification<T> where(@Nullable Specification<T> spec) {
        //noinspection ConstantConditions
        return Specification.where(null);
    }
}
