package br.com.springboot.essentials.error;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Murilo Victor on 12/06/2019
 */

@Builder
@Getter
public class ResourceNotFoundDetails {

    private String title;
    private int status;
    private String detail;
    private long timestamp;
    private String developerMessage;

}