package com.nzipo.NzIpoExercise3.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDetails {

    @JsonProperty("applicationNumber")
    private Long applicationNumber;
    @JsonProperty("applicantId")
    private Long applicantId;
    @JsonProperty("applicantName")
    private String applicantName;
    @JsonProperty("applicantAddress")
    private String applicantAddress;
}
