package com.travelmaker.home.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Ticket {
    private int tno;
    private String owner;
    private String grade;
}
