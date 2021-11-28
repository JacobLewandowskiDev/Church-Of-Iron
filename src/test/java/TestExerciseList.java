import org.junit.jupiter.api.BeforeEach;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestExerciseList {
    private BodyPart bodyPart;
    private ExerciseList exerciseList;

    //Test for user created exercises
    //* Before testing uncomment private instance of bodyPart and created instance of bodyPart in setup()
    //** Before testing change BodyPart accessibility from private to public
    //*** Do remember to turn it back to private after finishing the testing phase

    @BeforeEach
    void setup() {
        bodyPart = new BodyPart("CHEST");
        exerciseList = new ExerciseList();
        ExerciseList.createExercise(bodyPart.getName(), "Dumbbell row");
        ExerciseList.createExercise(bodyPart.getName(), "Pull down");
        ExerciseList.createExercise(bodyPart.getName(), "Bench press");
        ExerciseList.createExercise(bodyPart.getName(), "Dumbbell press");

    }

    @org.junit.jupiter.api.Test
    public void createExercise() throws Exception {
        boolean test = ExerciseList.createExercise("buttocks", "won't work");
        assertEquals(false, test); //Method should return false as the bodyPart does not exist in the database.

        test = ExerciseList.createExercise("chest", "bench press");
        assertEquals(false, test); //Method should return false as the exercise "bench press" is a default existing exercise.

        test = ExerciseList.createExercise("chest", "push up");
        assertEquals(true, test);   //Method should return true, as it doesn't exist in the designated muscle group.

        test = ExerciseList.createExercise("chest", "push up");
        assertEquals(false, test);  //Method should return false, as it was created and added to the database in the test above.
    }

    @org.junit.jupiter.api.Test
    void deleteExercise() {
        boolean test = ExerciseList.deleteExercise(bodyPart.getName(), "Bench press");
        assertEquals(false, test);  //test should return false as exercise is a default non deletable

        test = ExerciseList.deleteExercise(bodyPart.getName(), "Pull down");
        assertEquals(true, test);   //test should return true, because exercise exists inside the database

        test = ExerciseList.deleteExercise(bodyPart.getName(), "Pull down");
        assertEquals(false, test);   //test should return false, because exercise was deleted in the test above from the database

        test = ExerciseList.deleteExercise(bodyPart.getName(), "Dumbbell press");
        assertEquals(true, test);   //test should return true
    }

    //Should just print a list of available exercises --> Never false if default exercises exist
    @org.junit.jupiter.api.Test
    void displayBasicMuscleGroupExercises() {
        for (Map.Entry<BodyPart, Set<Exercise>> entry : ExerciseList.exerciseList.entrySet()) {
            System.out.println(entry.getKey().getName() + ":");
            int i = 1;

            for (Exercise exercise : entry.getValue()) {
                System.out.println(i + ". " + exercise.getName());
                i++;
            }
        }
    }
}