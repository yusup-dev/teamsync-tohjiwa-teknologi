package com.tohjiwa.teamsync.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RestResponseBody<T> {
    private Date timestamp;
    private int status;
    @JsonInclude(Include.NON_NULL)
    private String error;
    private String message;
    private String path;
    @JsonInclude(Include.NON_NULL)
    private T data;

    @JsonInclude(Include.NON_NULL)
    private Integer hashCode;

    public RestResponseBody(int status, T data) {
        this.timestamp = new Date(Instant.now().toEpochMilli());
        this.status = status;
        this.data = data;
    }
}
