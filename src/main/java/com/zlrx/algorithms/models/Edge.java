package com.zlrx.algorithms.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Edge {

    private double weight;
    private Node from;
    private Node to;

}
