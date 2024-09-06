package org.opengpa.server.mapper;

import org.opengpa.server.dto.TaskDTO;
import org.opengpa.server.model.Task;

public class TaskMapper {

    public static TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getTaskId());
        dto.setQuery(task.getInput());
        dto.setContext(task.getContext());

        // TODO - We should have a proper summarization on the other side
        String input = task.getInput();
        if (input != null && input.length() > 27) {
            input = input.substring(0, 27) + "...";
        }
        dto.setSummary(input);

        return dto;
    }
}
