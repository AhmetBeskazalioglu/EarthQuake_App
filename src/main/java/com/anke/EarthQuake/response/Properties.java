package com.anke.EarthQuake.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Properties {

    private String country;
    private String place;
    private double mag;
    private String time;

}
