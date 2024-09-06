package org.opengpa.server.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ActionDTO {

    private String name;

    private Map<String, String> parameters;

    private String status;

    private Object result;

    private String error;

    private String summary;

    private String message;
}
