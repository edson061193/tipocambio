package com.edsuarez.tipocambio.dto;

import com.edsuarez.tipocambio.exception.ErrorEx;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseWebResponse<T> {
    private ErrorEx errorCode;
    private T data;

    public static BaseWebResponse successNoData() {
        return BaseWebResponse.builder()
                .build();
    }

    public static <T> BaseWebResponse<T> successWithData(T data) {
        return BaseWebResponse.<T>builder()
                .data(data)
                .build();
    }

    public static BaseWebResponse error(ErrorEx errorCode) {
        return BaseWebResponse.builder()
                .errorCode(errorCode)
                .build();
    }
}