package br.com.springboot.essentials.model;

import java.io.Serializable;

/**
 * @author Murilo Victor on 20/05/2019
 */
public interface AbstractEntity extends Serializable {
    Long getId();
}
