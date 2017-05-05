package hudson.plugins.parameterizedtrigger;

import hudson.model.*;
import hudson.plugins.parameterizedtrigger.ParameterizedTriggerUtils.CustomParametersAction;

import java.util.List;
import java.util.ArrayList;

/**
 * Ensure the given project's parameters with default values exist in the parameter list.
 *
 * If they do not, append them with the specified default value.
 */
public class DefaultParameterValuesActionsTransform implements ITransformProjectParametersAction {
    @Override
    public CustomParametersAction transformParametersAction(CustomParametersAction a, Job<?,?> project) {
        return ParameterizedTriggerUtils.mergeParameters(getDefaultParameters(project), a);
    }

    private static CustomParametersAction getDefaultParameters(Job<?,?> project) {

        ParametersDefinitionProperty property = project.getProperty(ParametersDefinitionProperty.class);

        if (property == null) {
            return new CustomParametersAction();
        }

        List<ParameterValue> parameters = new ArrayList<ParameterValue>();
        for (ParameterDefinition pd : property.getParameterDefinitions()) {
            ParameterValue param = pd.getDefaultParameterValue();
            if (param != null) {
                parameters.add(param);
            }
        }

        return new CustomParametersAction(parameters);
    }
}
