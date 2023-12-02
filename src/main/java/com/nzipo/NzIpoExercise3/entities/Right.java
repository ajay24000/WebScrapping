package com.nzipo.NzIpoExercise3.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Right {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("opponentOrNot")
    private boolean opponentOrNot;
    @JsonProperty("rightName")
    private String rightName;
    @JsonProperty("rightType")
    private String rightType;
    @JsonProperty("rightReference")
    private String rightReference;
    @JsonProperty("classifications")
    private List<Classification> classifications;
}
