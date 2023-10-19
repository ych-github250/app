package com.ych.core.web.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseQuery {

    private Long current = 1L;

    private Long size = 10L;

}
