package com.example.bms.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", setterPrefix = "with")
@Setter
@Getter
@ToString
public class MovieResponse {

    String movieName;
    String movieID;
    String actorName;
    String actoressName;
    String theatreID;

}
