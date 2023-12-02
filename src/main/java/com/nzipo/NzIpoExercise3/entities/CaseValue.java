package com.nzipo.NzIpoExercise3.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaseValue {

    @JsonProperty("Binder")
    private Binder bind;

}
