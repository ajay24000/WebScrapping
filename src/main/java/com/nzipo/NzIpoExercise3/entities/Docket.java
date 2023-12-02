package com.nzipo.NzIpoExercise3.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Docket {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("docketReference")
    private String docketReference;
    @JsonProperty("courtId")
    private String courtId;
    @JsonProperty("judge")
    private String judge;
}
