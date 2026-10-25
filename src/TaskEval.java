import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Getter
public class TaskEval {

    private Set<Task> fullTasks = new HashSet<>();
    private Set<Task> annTask = new HashSet<>();
    private Set<Task> bobTask = new HashSet<>();
    private Set<Task> carolTask = new HashSet<>();
    private final Set<Task> allTask;

    public TaskEval() {
        TaskData taskDataInstance = new TaskData();
        annTask = taskDataInstance.getData("Ann");
        bobTask = taskDataInstance.getData("Bob");
        carolTask = taskDataInstance.getData("Carol");
        allTask = taskDataInstance.getData("All");
        fullTasks = fullTaskList();
    }


    private Set<Task> fullTaskList() {

        Set<Task> fullDataSet = new HashSet<>(carolTask);
        fullDataSet.addAll(annTask);
        fullDataSet.addAll(bobTask);
        fullDataSet.addAll(allTask);
        fullTasks = fullDataSet;
        return fullDataSet;
    }

    public Set<Task> getFullTasksList(){
        //new ArrayList<>(fullTasks).forEach(System.out::println);
        return fullTasks;
    }


    public Set<Task> getAtLeastOneOfTeamMember(){
        Set<Task> assignedToAtLeastOne = new HashSet<>(getAllTask());
        assignedToAtLeastOne.removeAll(stillNeedTobeAssigned());
        //System.out.println(assignedToAtLeastOne.size());
        //new ArrayList<>(assignedToAtLeastOne).forEach(System.out::println);
        return assignedToAtLeastOne;
    }


    public Set<Task> stillNeedTobeAssigned(){
        Set<Task> StillNeedTobeAssigned = new HashSet<>(getAllTask());
        StillNeedTobeAssigned.removeAll(annTask);
        StillNeedTobeAssigned.removeAll(bobTask);
        StillNeedTobeAssigned.removeAll(carolTask);
        return StillNeedTobeAssigned;
    }
    public static void printTaskOut(Set<Task> taskIn, String title)  {
        List<Task> taskList = new ArrayList<>(taskIn);
        taskList.sort(Task.sortByNameAndDescription);
        Consumer<List<Task>> f0 = s -> System.out.println("Size" + " = " + s.size() );
        Consumer<List<Task>> f1 = s -> s.forEach(System.out::println);
        BiConsumer<String, String> f3 = (s1, s2) -> System.out.println(s1.repeat(90) + "\n"
        + title);
        f3.accept("_", title);
        f0.andThen(f1).accept(taskList);
    }

    public Set<Task> getTaskNotInTheMainList(){
        Set<Task> noInTheMainList = new HashSet<>(getFullTasks());
        noInTheMainList.removeAll(getAllTask());
        return noInTheMainList;
    }

    public Set<Task> assignedToMultipleEmployee() {
        Set<Task> multipleEmployeeTask = new HashSet<>(getFullTasks());
        multipleEmployeeTask.retainAll(annTask);
        multipleEmployeeTask.retainAll(bobTask);
        multipleEmployeeTask.retainAll(carolTask);
        return multipleEmployeeTask;
    }

    public Set<Task> getUnion(List<Set<Task>> listOfSets) {
        Set<Task> unionOFSetList = new HashSet<>();
        for (var sets : listOfSets) {
            unionOFSetList.addAll(sets);
        }
        return unionOFSetList;
    }

    public Set<Task> getIntersect(Set<Task> setA, Set<Task> setB) {
        Set<Task> intersect = new HashSet<>(setA);
        intersect.retainAll(setB);
        return intersect;
    }
    public Set<Task> getDifference(Set<Task> setA, Set<Task> setB) {
        Set<Task> difference = new HashSet<>(setA);
        difference.removeAll(setB);
        return difference;
    }
}
