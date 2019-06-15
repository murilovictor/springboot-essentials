package br.com.springboot.essentials.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Murilo Victor on 14/06/2019
 */
@Getter
@AllArgsConstructor
public class ErrorDetails {
    protected String title;
    protected int status;
    protected String detail;
    protected long timestamp;
    protected String developerMessage;
}
