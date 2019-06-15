package br.com.springboot.essentials.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Murilo Victor on 14/06/2019
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {
    protected String title;
    protected int status;
    protected String detail;
    protected long timestamp;
    protected String developerMessage;
}
