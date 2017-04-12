package com.megshan.splitnot.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by shantanu on 4/12/17.
 */
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

    private String id;
    private String name;

    public Account(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
