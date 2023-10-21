package com.nataliiakoval.onlineshopspringboot.entity;

import lombok.*;
import java.time.LocalDateTime;


@Data
@Builder
public class Goods {
    private int id;
    private String name;
    private int price;
    private LocalDateTime date;
}
