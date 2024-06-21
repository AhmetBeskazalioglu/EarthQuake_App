package com.anke.EarthQuake.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Earthquake {
    private String country;
    private String place;
    private double magnitude;
    private String time;

    public Earthquake(String message) {
        this.place = message;
    }
}
