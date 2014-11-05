package se.inera.fmu.application.impl;

import javax.inject.Inject;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * Created by Rasheed on 8/7/14.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public abstract class AbstractServiceImpl {

    protected RuntimeService runtimeService;
    protected TaskService taskService;

    @Inject
    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Inject
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     *
     * @param processInstance
     * @param definitionKey
     * @return
     */
    protected Task findTaskByDefinitionKey(ProcessInstance processInstance, String definitionKey) {
        return taskService.createTaskQuery().
            processInstanceId(processInstance.getId()).
            taskDefinitionKey(definitionKey).
            singleResult();
    }

}
