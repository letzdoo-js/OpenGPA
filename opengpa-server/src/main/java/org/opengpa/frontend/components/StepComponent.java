package org.opengpa.frontend.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.jetbrains.annotations.NotNull;
import org.opengpa.core.agent.AgentStep;
import org.opengpa.core.workspace.Document;
import org.opengpa.frontend.utils.MarkdownConverter;
import org.springframework.util.StringUtils;

public class StepComponent extends VerticalLayout {

    private final HorizontalLayout actionContainer = new HorizontalLayout();

    public StepComponent(AgentStep step) {
        setClassName("step-component");

        actionContainer.setClassName("step-action");
        actionContainer.setVisible(false);

        Component outputComponent = outputComponent(step);
        Component reasoningComponent = reasoningComponent(step);
        Component linksComponent = getLinksComponent(step);

        Button expandButton = new Button(new Icon(VaadinIcon.LIGHTBULB));
        expandButton.addClassName("step-details-button");
        expandButton.addClickListener(e -> reasoningComponent.setVisible(!reasoningComponent.isVisible()));

        add(actionContainer, outputComponent, reasoningComponent, linksComponent, expandButton);
        expand(actionContainer);
    }

    public void setActionComponent(Component component) {
        actionContainer.add(component);
        actionContainer.setVisible(true);
    }

    private @NotNull Component outputComponent(AgentStep step) {
        var htmlOutput = MarkdownConverter.getInstance().convertToHtml(step.getResult().getOutput());
        var component = new Html("<div>" +  htmlOutput + "</div>");
        component.addClassName("step-output");
        component.setVisible(StringUtils.hasText(step.getResult().getOutput()));
        return component;
    }

    private @NotNull Component reasoningComponent(AgentStep step) {
        var component = new Div();
        component.setText("Reasoning: " + step.getReasoning());
        component.addClassName("step-reasoning");
        component.setVisible(false);
        return component;
    }

    private @NotNull Component getLinksComponent(AgentStep step) {
        var component = new Div();
        component.setClassName("step-links");

        if (step.getResult().getDocuments().size() > 0) {
            for (Document document : step.getResult().getDocuments()) {
                var anchor = new Anchor(String.format("/api/tasks/%s/documents/%s", document.getWorkspaceId(), document.getName()), document.getName());
                anchor.setClassName("download-button");
                anchor.getElement().setAttribute("download", true);
                component.add(anchor);
            }
        } else {
            component.setVisible(false);
        }

        return component;
    }
}

