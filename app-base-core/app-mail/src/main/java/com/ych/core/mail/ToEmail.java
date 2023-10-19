package com.ych.core.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToEmail implements Serializable {

    private String[] tos;

    private String subject;

    private String content;

}
