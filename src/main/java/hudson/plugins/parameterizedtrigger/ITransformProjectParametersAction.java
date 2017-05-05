package hudson.plugins.parameterizedtrigger;

import hudson.model.Job;
import hudson.plugins.parameterizedtrigger.ParameterizedTriggerUtils.CustomParametersAction;

public interface ITransformProjectParametersAction {
    /**
     * Called if there's an existing ParametersAction to transform.
    */
    CustomParametersAction transformParametersAction(CustomParametersAction a, Job<?, ?> project);
}
