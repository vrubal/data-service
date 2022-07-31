package com.csg.cro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
public class Counterparty {
    Long id;
    String name;
    List<Exposure> exposure;
}
