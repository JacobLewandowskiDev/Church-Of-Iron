import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestTrainingLog {
    private TrainingLog trainingLog;

    @BeforeEach
    void setup() {
        trainingLog = new TrainingLog();
    }

    @Test
    void getTodayDate() throws Exception{
        String pattern = "dd-MM-yyyy";
        String dateInString =new SimpleDateFormat(pattern).format(new Date());
        boolean test = false;
        if(dateInString.equals(trainingLog.getTodayDate())) {
            test = true;
        }
        else {
            test = false;
        }
        assertEquals(true,test);
    }

    @Test
    void addExerciseToTrainingLog() {
    }

    @Test
    void deleteExerciseFromTrainingLog() {
    }

    @Test
    void setSets() {
    }

    @Test
    void setReps() {
    }

    @Test
    void setWeight() {
    }

    @Test
    void displayTrainingLog() {
    }
}