package org.opengpa.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.opengpa.core.workspace.Document;

import java.util.List;
import java.util.Map;

@Data
public class StepDTO {

    String input;

    ActionDTO action;

    String reasoning;

    @JsonIgnore
    Map<String, String> context;

    List<DocumentDTO> documents;

    boolean isFinal;
}
