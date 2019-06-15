package br.com.springboot.essentials.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Murilo Victor on 14/06/2019
 */
@Getter
public class ErrorDetail extends ErrorDetails{

    @Builder
    public ErrorDetail(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title, status, detail, timestamp, developerMessage);
    }
}
