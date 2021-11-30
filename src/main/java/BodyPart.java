import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public final class BodyPart implements Serializable {
    private final String name;
    protected final  Set<Exercise> exercises;
    private static final long serialVersionUID = 1L;

    public BodyPart(String name) {
        this.name = name;
        this.exercises = new LinkedHashSet<Exercise>();
    }

    //BodyPart getters:
    public String getName() {
        return name;
    }
    //Return unmodifiable map to sub-class
    public Set<Exercise> getExercises() {
        return this.exercises;
    }

}
