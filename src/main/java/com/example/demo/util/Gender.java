package com.example.demo.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    @JsonProperty("male")
    MALE,
    @JsonProperty("female")
    FEMALE,
    @JsonProperty("other")
    OTHER;
}

//public enum Gender {
//    MALE, FEMALE, OTHER;
//
//    @JsonCreator
//    public static Gender from(String value) {
//        return Gender.valueOf(value.toUpperCase());
//    }
//
//    @JsonValue
//    public String toValue() {
//        return name().toLowerCase();
//    }
//}
