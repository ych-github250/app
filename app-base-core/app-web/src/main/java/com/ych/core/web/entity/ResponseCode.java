package com.ych.core.web.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS(200, "操作成功┗|｀O′|┛ 嗷~~"),
    FAIL(500, "程序猿去送外卖了(╯‵□′)╯炸弹！•••*～●"),
    ;

    private final int code;

    private final String message;

}
