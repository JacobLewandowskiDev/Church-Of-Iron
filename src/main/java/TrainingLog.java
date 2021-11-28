import java.text.SimpleDateFormat;
import java.util.*;

public class TrainingLog {
    private final String todayDate;

    private final Set<Exercise> exerciseList;

    public TrainingLog() {
        String pattern = "dd-MM-yyyy";
        this.todayDate = new SimpleDateFormat(pattern).format(new Date());
        this.exerciseList = new LinkedHashSet<Exercise>();
    }

    //Get today's date and time
    public String getTodayDate() {
        return todayDate;
    }

    //Add exercise from existing database to your training log
    public final boolean addExerciseToTrainingLog(String bodyPart, String exerciseName) {
        for (Map.Entry<BodyPart, Set<Exercise>> entry : ExerciseList.exerciseList.entrySet()) {
            if (entry.getKey().getName().equals(bodyPart.toUpperCase())) {
                Set<Exercise> list = entry.getKey().exercises;
                if (list != null) {
                    for (Exercise exercise : list) {
                        if (exercise.getName().equals(exerciseName.toUpperCase())) {
                            this.exerciseList.add(exercise);
                            System.out.println("Exercise " + exerciseName + " added to training log");
                            return true;
                        }
                    }
                }
            }
        }
        System.out.println("No such exercise exists in database.");
        return false;
    }

    //Delete exercise from training log
    public final boolean deleteExerciseFromTrainingLog(String exerciseName) {
        for(Exercise exercise : this.exerciseList) {
            if(exercise.getName().equals(exerciseName.toUpperCase())) {
                this.exerciseList.remove(exercise);
                System.out.println("Exercise: " + exerciseName + " was removed from training log");
                return true;
            } else {
                System.out.println("Exercise " + exerciseName + " doesn't exist inside training log");
                return false;
            }
        }
        return false;
    }

    //Change the amount of sets of exercise in training log
    public final boolean setSets(int sets, String exerciseName) {
        for (Exercise exercise : exerciseList) {
            if (exercise.getName().equals(exerciseName.toUpperCase())) {
                exercise.setSets(sets);
                return true;
            }
        }
        return false;
    }

    //Change the amount of reps of exercise in training log
    public final boolean setReps(int reps, String exerciseName) {
        for (Exercise exercise : exerciseList) {
            if (exercise.getName().equals(exerciseName.toUpperCase())) {
                exercise.setReps(reps);
                return true;
            }
        }
        return false;
    }

    //Change the amount of weight used in exercise in training log
    public final boolean setWeight(double weight, String exerciseName) {
        for (Exercise exercise : exerciseList) {
            if (exercise.getName().equals(exerciseName.toUpperCase())) {
                exercise.setWeight(weight);
                return true;
            }
        }
        return false;
    }

    //Display workout routine to user
    public void displayTrainingLog() {
        int i = 1;
        System.out.println("\nYour workout routine for: " + getTodayDate() + ": ");
        for (Exercise exercise : exerciseList) {
            System.out.println("\t" + i + "." + exercise.getName() + ": " + exercise.getSets() +
                    " sets of " + exercise.getReps() + " reps with " + exercise.getWeight() + " kg");
            i++;
        }
    }
}