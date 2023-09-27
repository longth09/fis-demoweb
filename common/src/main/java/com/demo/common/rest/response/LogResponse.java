package com.demo.common.rest.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogResponse {
    private UUID id;
    private String msg;
    private String caller;
    private String level;
    private String stacktrace;
    private String user_id;
    private Long start_time;
    private Long end_time;
    private Long execution_time;
    private Long ts;
    private String request_id;
    private String request_method;
    private String request_query;
    private String request_body;
    private String request_pattern;
    private String request_path;
    private String device_id;
    private String device_session_id;
    private String user_agent;
    private String client_ip;
    @JsonProperty("x-forwarded-for")
    private String x_forwarded_for;
    @JsonProperty("cf-connecting-ip")
    private String cf_connecting_ip;
    private int response_status;
    private int response_byte;
    private String app_name;
    private String version;

}
