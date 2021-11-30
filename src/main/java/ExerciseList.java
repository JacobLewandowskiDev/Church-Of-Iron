import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ExerciseList implements Serializable {
    protected static final Map<BodyPart, Set<Exercise>> exerciseList = new LinkedHashMap<BodyPart, Set<Exercise>>();
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws IOException {
        //Save object to file.dat
        try (ObjectOutputStream saveFIle = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("exercises.dat")))) {
            for (BodyPart body : exerciseList.keySet()) {
                saveFIle.writeObject(body);
            }
        }
    }
    //Write to file.txt (proper naming test)
//        try (BufferedWriter file = new BufferedWriter(new FileWriter("list.txt"))) {
//            for (BodyPart body : exerciseList.keySet()) {
//                file.write(body.getName() + ",");
//                if (body.exercises != null) {
//                    for (Exercise exercises : exerciseList.get(body)) {
//                        file.write(exercises.getName());
//                        file.write(",");
//                    }
//                }
//                file.write("\n");
//            }
//        }
//    }

    static {
        //Load objects from file.dat
        try (ObjectInputStream loadFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("exercises.dat")))) {
            boolean eof = false;
            while (!eof) {
                try {
                    BodyPart body = (BodyPart) loadFile.readObject();
                    System.out.println("Loaded body part: " + body.getName());
                    int i = 1;
                    System.out.println("Found exercises for " + body.getName() + ": ");
                    for (Exercise exercise : body.exercises) {
                        System.out.println(i + "." + exercise.getName());
                        i++;
                    }
                    System.out.println();
                    exerciseList.put(body, body.getExercises());
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //App created exercises -> Are undeletable by user
    private static boolean createExercise(BodyPart bodyPart, String exerciseName) {
        Set<Exercise> list = exerciseList.getOrDefault(bodyPart, null);
        if (list == null) {
            bodyPart.exercises.add(new Exercise(bodyPart, exerciseName.toUpperCase()));
            exerciseList.put(bodyPart, bodyPart.exercises);
            return true;
        } else {
            for (Exercise exist : list) {
                if (!exist.getName().equals(exerciseName.toUpperCase())) {
                    bodyPart.exercises.add(new Exercise(bodyPart, exerciseName.toUpperCase()));
                    exerciseList.put(bodyPart, bodyPart.exercises);
                    return true;
                }
            }
        }
        return false;
    }

    //User made exercises -> Are deletable by user
    public static boolean createExercise(String bodyPart, String exerciseName) {
        for (BodyPart body : exerciseList.keySet()) {
            if (body.getName().equals(bodyPart.toUpperCase())) {
                Exercise newExercise = new Exercise(body, exerciseName.toUpperCase());
                newExercise.userCreated = true;
                if (body.exercises.isEmpty()) {
                    body.exercises.add(newExercise);
                    exerciseList.put(body, body.exercises);
                    System.out.println("Exercise \"" + exerciseName + "\" was added to the database.");
                    return true;
                } else {
                    if (!body.exercises.contains(newExercise)) {
                        body.exercises.add(newExercise);
                        exerciseList.put(body, body.exercises);
                        System.out.println("Exercise \"" + exerciseName + "\" was added to the database.");
                        return true;
                    }
                    System.out.println("Exercise \"" + exerciseName + "\" is already inside the database.");
                    return false;
                }
            }
        }
        System.out.println("Body part \"" + bodyPart + "\" does not exist.");
        return false;
    }

    //Delete user made exercise from dataBase
    public static boolean deleteExercise(String bodyPart, String exerciseName) {
        for (BodyPart temp : exerciseList.keySet()) {
            if (temp.getName().equals(bodyPart.toUpperCase())) {
                for (Exercise exercise : temp.exercises) {
                    if ((exercise.getName().equals(exerciseName.toUpperCase())) && (exercise.userCreated)) {
                        temp.exercises.remove(exercise);
                        System.out.println("Exercise \"" + exerciseName + "\" was deleted.");
                        return true;
                    }
                }
            }
        }
        System.out.println("Spelling error or exercise non existing for user input: " + exerciseName);
        return false;
    }

    // Print exerciseList
    public static void displayBasicMuscleGroupExercises() {
        for (Map.Entry<BodyPart, Set<Exercise>> entry : exerciseList.entrySet()) {
            System.out.println(entry.getKey().getName() + ": ");
            int i = 1;
            for (Exercise exercise : entry.getKey().getExercises()) {
                System.out.println("\t" + i + "." + exercise.getName());
                i++;
            }
        }
    }

    //List of basic muscle groups and default exercises for application
//    static {
//        BodyPart bodyPart = new BodyPart("CHEST");
//        createExercise(bodyPart, "Bench press");
//        createExercise(bodyPart, "Incline dumbbell press");
//
//        bodyPart = new BodyPart("BACK");
//        createExercise(bodyPart, "Back squat");
//        createExercise(bodyPart, "Front squat");
//        createExercise(bodyPart, "Leg press");
//
//        bodyPart = new BodyPart("TRAPS");
//        exerciseList.put(bodyPart, bodyPart.exercises);
//
//        bodyPart = new BodyPart("SHOULDERS");
//        exerciseList.put(bodyPart, bodyPart.exercises);
//
//        bodyPart = new BodyPart("BICEPS");
//        exerciseList.put(bodyPart, bodyPart.exercises);
//
//        bodyPart = new BodyPart("TRICEPS");
//        exerciseList.put(bodyPart, bodyPart.exercises);
//
//        bodyPart = new BodyPart("FOREARMS");
//        exerciseList.put(bodyPart, bodyPart.exercises);
//
//        bodyPart = new BodyPart("ABS");
//        exerciseList.put(bodyPart, bodyPart.exercises);
//
//        bodyPart = new BodyPart("LEGS");
//        exerciseList.put(bodyPart, bodyPart.exercises);
//
//        bodyPart = new BodyPart("CALVES");
//        exerciseList.put(bodyPart, bodyPart.exercises);


    //Read file.txt (proper naming test)
//        boolean eof = false;
//        String delimiter = ",";
//        while (!eof) {
//            try (Scanner load = new Scanner(new BufferedReader(new FileReader("list.txt")))) {
//                while (load.hasNextLine()) {
//                    load.useDelimiter(delimiter);
//                    BodyPart body = new BodyPart(load.next());
//                    load.skip(delimiter);
//                    String exercises = load.nextLine();
//                    if (exercises.length() > 1) {
//                        String[] words = exercises.split(",");
//                        System.out.println(body.getName() + ":");
//                        for (int i = 0; i < words.length; i++) {
//                            body.exercises.add(new Exercise(body, words[i]));
//                            System.out.println(i + 1 + "." + words[i]);
//                        }
//                    }
//                    exerciseList.put(body, body.exercises);
//                }
//            } catch (FileNotFoundException e) {
//                System.out.println(e.getMessage());
//            } finally {
//                eof = true;
//            }
//        }
//    }
//    }
//    }
}
