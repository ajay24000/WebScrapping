package com.nzipo.NzIpoExercise3.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classification {

    @JsonProperty("markType")
    private String markType;
    @JsonProperty("markName")
    private String markName;
    @JsonProperty("markImage")
    private String markImage;
    @JsonProperty("className")
    private String className;
}
