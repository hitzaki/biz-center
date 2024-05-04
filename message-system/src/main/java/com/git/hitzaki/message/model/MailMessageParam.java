package com.git.hitzaki.message.model;

import lombok.Data;

/**
 * TODO
 * @author hitzaki
 */
@Data
public class MailMessageParam {
    private String title;
    private String text;
    private String from;
    private String to;
}
