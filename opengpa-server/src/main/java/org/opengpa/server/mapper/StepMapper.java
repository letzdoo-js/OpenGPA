package org.opengpa.server.mapper;

import org.opengpa.core.agent.AgentStep;
import org.opengpa.server.dto.ActionDTO;
import org.opengpa.server.dto.StepDTO;

public class StepMapper {

    public static StepDTO toDTO(AgentStep step) {
        StepDTO stepDTO = new StepDTO();

        // Here are ActionInvocation -> ActionDTO conversion
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setName(step.getAction().getName());
        actionDTO.setParameters(step.getAction().getParameters());

        // Here are ActionResult -> ActionDTO conversion
        actionDTO.setStatus(step.getResult().getStatus().toString());
        actionDTO.setError(step.getResult().getError());
        actionDTO.setSummary(step.getResult().getSummary());
        actionDTO.setResult(step.getResult().getResult());
        actionDTO.setMessage(step.getResult().getOutput());

        // List all artifcats created in this step
        stepDTO.setDocuments(step.getResult().getDocuments().stream().map(DocumentMapper::toDocumentDTO).toList());

        // Setting ActionDTO for StepDTO
        stepDTO.setInput(step.getInput());
        stepDTO.setAction(actionDTO);
        stepDTO.setReasoning(step.getReasoning());
        stepDTO.setContext(step.getContext());
        stepDTO.setFinal(step.isFinal());

        return stepDTO;
    }
}
