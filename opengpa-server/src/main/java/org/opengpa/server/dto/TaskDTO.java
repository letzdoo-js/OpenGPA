package org.opengpa.server.dto;

import lombok.Data;

import java.util.Map;

@Data
public class TaskDTO {
    private String id;
    private String query;
    private String summary;
    private Map<String, String> context;
}
