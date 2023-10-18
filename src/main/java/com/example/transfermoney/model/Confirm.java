package com.example.transfermoney.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Getter
public class Confirm {

    @Size(min = 36, max = 36)
    @NotNull
    private String operationId;
    @Size(min = 4, max = 4)
    @NotNull
    private String code;
}