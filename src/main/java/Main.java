import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
public class Main {


    private static Map<String, TrainingLog> trainingLogData = new LinkedHashMap<String, TrainingLog>();
    private static TrainingLog trainingLog;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        bodypart and exercise test
//        BodyPart bodyPart = new BodyPart("CHEST"); // -> SHOULD CAUSE ERROR, BECAUSE IT'S A PRIVATE ACCESS CLASS
//        Exercise exercise = new Exercise(new BodyPart("Not going to work"), "Not getting created"); // -> Should cause error

        //creation test
        addExerciseToDatabase("chest", "test");
        addExerciseToDatabase("chest", "bench press");
        addExerciseToDatabase("chest", "bench press");
        addExerciseToDatabase("chest", "bench press2");
        addExerciseToDatabase("chest", "testuser");
        addExerciseToDatabase("chest", "testuser");
        addExerciseToDatabase("abc", "testuser");
        displayBasicMuscleGroupExercises();


        //deletion test
        deleteExerciseFromDatabase("chest", "test");
        deleteExerciseFromDatabase("chest", "bench press2");
        deleteExerciseFromDatabase("chest", "testuser");
        displayBasicMuscleGroupExercises();

        //Training log test
        trainingLog = new TrainingLog();
        trainingLogData.put(trainingLog.getTodayDate(), trainingLog);
        trainingLog.addExerciseToTrainingLog("chest", "bench press");
        trainingLog.displayTrainingLog();
        trainingLog.addExerciseToTrainingLog("chest", "bench press");
        trainingLog.addExerciseToTrainingLog("chest", "bench press");
        trainingLog.addExerciseToTrainingLog("back", "front squat");
        trainingLog.addExerciseToTrainingLog("back", "back squat");
        trainingLog.displayTrainingLog();

        //Change sets/reps/weight test
        trainingLog.setSets(10, "bench press");
        trainingLog.setReps(5, "bench press");
        trainingLog.setWeight(175.0, "bench press");
        trainingLog.deleteExerciseFromTrainingLog("bench press");
        trainingLog.deleteExerciseFromTrainingLog("push up");
        trainingLog.displayTrainingLog();
    }

    //Data Base methods:
    public static void addExerciseToDatabase(String bodyPart, String exerciseName) {
        ExerciseList.createExercise(bodyPart, exerciseName);
    }

    public static void deleteExerciseFromDatabase(String bodyPart, String exerciseName) {
        ExerciseList.deleteExercise(bodyPart, exerciseName);
    }

    //Training log methods:
    public static void displayBasicMuscleGroupExercises() {
        ExerciseList.displayBasicMuscleGroupExercises();
    }
}