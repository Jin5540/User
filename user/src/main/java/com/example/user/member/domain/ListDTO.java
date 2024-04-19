package com.example.user.member.domain;

import com.example.user.valid.ValidEnum;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ListDTO {

    @NotNull(message = "페이지 번호는 NULL이 될 수 없습니다. 다시 확인하여 주십시오.")
    @Min(value = 0, message = "페이지 번호는 0 이상이여야 합니다. 다시 입력하여 주십시오.")
    private int page;

    @NotNull(message = "한 페이지에 표시될 최대 회원 수는 NULL이 될 수 없습니다. 다시 확인하여 주십시오.")
    @Min(value = 1, message = "회원 수는 1 이상이여야 합니다. 다시 입력하여 주십시오.")
    private int pageSize;

    @ValidEnum(enumClass = SortCheck.class, message = "유효하지 않은 정렬 기준입니다. CREATED_AT, NAME만 가능합니다. 다시 확인하여 주십시오.")
    @NotNull(message = "유효하지 않은 정렬 기준입니다. CREATED_AT, NAME만 가능합니다. 다시 확인하여 주십시오.")
    private SortCheck sort;
}
