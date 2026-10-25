public class Main {
    public static void main(String[] args) {


        TaskEval taskEval = new TaskEval();
        var fullList = taskEval.getFullTasksList();
        TaskEval.printTaskOut(fullList, "Full list");

        var assignedToMoreThan = taskEval.getAtLeastOneOfTeamMember();
        TaskEval.printTaskOut(assignedToMoreThan, "Assigned to more than one player");

        var assignedToAllEmployees = taskEval.assignedToMultipleEmployee();
        TaskEval.printTaskOut(assignedToAllEmployees, "Assigned to all users");

        var taskNotInMainList = taskEval.getTaskNotInTheMainList();
        TaskEval.printTaskOut(taskNotInMainList, "Task not in the main list");

        var taskThatHasNotBeenAssigned = taskEval.stillNeedTobeAssigned();
        TaskEval.printTaskOut(taskThatHasNotBeenAssigned, "Task that has not been assigned");








    }
}
