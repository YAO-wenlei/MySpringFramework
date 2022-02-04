package com.yao.springframework.beans;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyValue {
    private String name;
    private Object value;

    public PropertyValue(String name ,Object value) {
        this.name = name;
        this.value = value;
    }
}
