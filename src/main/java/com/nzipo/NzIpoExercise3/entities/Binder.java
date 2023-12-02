package com.nzipo.NzIpoExercise3.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Binder {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("firstAction")
    private String firstAction;
    @JsonProperty("firstActionDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private LocalDate firstActionDate;

    @JsonProperty("applicantDetails")
    private List<ApplicantDetails> applicantDetails;
    @JsonProperty("docket")
    private List<Docket> docket;
    @JsonProperty("decisions")
    private List<Decisions> decisions;
    @JsonProperty("party")
    private List<Party> party;
    @JsonProperty("right")
    private List<Right> right;
}
