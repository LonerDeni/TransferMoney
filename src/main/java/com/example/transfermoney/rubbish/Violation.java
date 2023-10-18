package com.example.transfermoney.rubbish;

import com.example.transfermoney.model.ResponseTransfer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Violation {

    private final List<ResponseTransfer> errors;

}