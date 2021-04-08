package com.siit.finalproject.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private Integer status;

    private String errorMsg;

    @Builder.Default
    private List<String> errors = new ArrayList<>();
}
