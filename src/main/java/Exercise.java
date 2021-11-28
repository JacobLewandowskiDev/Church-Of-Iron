import java.io.Serializable;

public final class Exercise implements Serializable {
    protected String name;
    protected int sets;
    protected int reps;
    protected double weight;
    protected boolean userCreated;
    private final BodyPart bodyPart;
    private static final long serialVersionUID = 1L;

    //Exercise constructor
    protected Exercise(BodyPart bodyPart, String name) {
        this.name = name;
        this.sets = 0;
        this.reps = 0;
        this.weight = 0.0;
        this.userCreated = false;
        this.bodyPart = bodyPart;
    }

    //Getters for Exercise
    protected String getName() {
        return name;
    }

    protected int getSets() {
        return sets;
    }

    protected int getReps() {
        return reps;
    }

    protected double getWeight() {
        return weight;
    }

    //Setters for Exercise
    protected void setSets(int sets) {
        if (sets >= 0) {
            this.sets = sets;
        }
    }

    protected void setReps(int reps) {
        if (reps >= 0) {
            this.reps = reps;
        }
    }

    protected void setWeight(double weight) {
        if (weight >= 0) {
            this.weight = weight;
        }
    }

    //Check if exercise exists in database already and block out duplicates
    @Override
    public int hashCode() {
        return this.name.hashCode() + 69 + this.bodyPart.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Exercise) {
            Exercise theObject = (Exercise) obj;
            return (this.bodyPart == theObject.bodyPart);
        }
        return false;
    }
}
