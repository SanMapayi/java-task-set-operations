import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class TaskData {

    private final String allTasks = """		
            Infrastructure,	 Logging,	 High
            Infrastructure,	 DB Access,	 Medium
            Infrastructure,	 Security,	 High
            Infrastructure,	 Password Policy,	 Medium
            Data Design,	 Task Table,	 Medium
            Data Design,	 Employee Table,	 Medium
            Data Design,	 Cross Reference Tables,	 High
            Data Design,	 Encryption Policy,	 High
            Data Access,	 Write Views,	 Low
            Data Access,	 Set Up Users,	 Low
            Data Access	, Set Up Access Policy,	 Low   
            """;

    private final String annTasks = """
            Infrastructure,	 Security,	 High,	 In Progress
            Infrastructure,	 Password Policy,	Medium,	 In Progress
            Research,	 Cloud solutions,	 Medium,	 In Progress
            Data Design,	 Encryption Policy,	 High
            Data Design,	 Project Table,	 Medium
            Data Access,	 Write Views,	Low,	 In Progress       
            """;

    private final String bobTasks = """
            Infrastructure,	 Security,	 High,	 In Progress
            Infrastructure,	 Password Policy,	 Medium
            Data Design,	Encryption Policy,	High
            Data Access,	Write Views,	 Low,	 In Progress  
            """;


    private final String carolTasks = """
            Infrastructure,	 Logging,	 High,	 In Progress
            Infrastructure,	 DB Access,	 Medium
            Infrastructure,	 Password Policy,	 Medium
            Data Design,	 Task Table,	 High
            Data Access,	 Write Views,	 Low     
            """;


    public Set<Task> getData(String owner) {
        Set<Task> taskSet = new HashSet<>();
        String assignee = "ann,bob,carol".contains(owner.toLowerCase()) ? owner : null;
        String dataList = switch(owner.toLowerCase()) {
            case "ann" -> annTasks;
            case "bob" -> bobTasks;
            case "carol" -> carolTasks;
            default -> allTasks;
        };

        for (var task : dataList.split("\n")) {
            var taskArray = task.split(",");
            Arrays.setAll(taskArray, i -> taskArray[i].trim().strip());
            Status status = taskArray.length <=3 ? Status.NOT_ASSIGNED : Status.valueOf(taskArray[3].toUpperCase().replaceAll(" ", "_"));
            Priority priority = Priority.valueOf(taskArray[2].toUpperCase().trim().strip());
            UnaryOperator<String> f0 = s -> String.valueOf(s.charAt(0)) + s.substring(1);
//            assert assignee != null;
//            assignee = assignee.transform(f0);
            taskSet.add(new Task(assignee, taskArray[0], taskArray[1], status, priority));
        }
        return taskSet;
    }

    public static void printTaskSet(Collection<Task> setIn, String title, Comparator<Task> sortType) {
        Consumer<String> printHeader = System.out::println;
        Consumer<String> printLines = s -> System.out.println( s.repeat(90));
        Consumer<String> combined = printHeader.andThen(printLines);
        printHeader.accept(title);
        printLines.accept("_");
        List<Task> taskList = new ArrayList<>(setIn);
        taskList.sort(sortType);
        taskList.forEach(System.out::println);
    }
}
