package br.com.springboot.essentials.error;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Murilo Victor on 12/06/2019
 */

@Getter
public class ResourceNotFoundDetails extends ErrorDetails {

    @Builder
    public ResourceNotFoundDetails(String title, int status, String detail, long timestamp, String developerMessage) {
        super(title, status, detail, timestamp, developerMessage);
    }
}