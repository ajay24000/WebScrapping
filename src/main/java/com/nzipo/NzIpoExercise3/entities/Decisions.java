package com.nzipo.NzIpoExercise3.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Decisions {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("decisionReference")
    private String decisionReference;
    @JsonProperty("judgementDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate judgementDate;
    @JsonProperty("decisionLevel")
    private String decisionLevel;
    @JsonProperty("decisionNature")
    private String decisionNature;
    @JsonProperty("robotSource")
    private String robotSource;
}
