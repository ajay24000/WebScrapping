package com.nzipo.NzIpoExercise3.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Party {

    @JsonProperty("partyName")
    private String partyName;
    @JsonProperty("partyType")
    private String partyType;
    @JsonProperty("partyRepresentatives")
    private String partyRepresentatives;
}
