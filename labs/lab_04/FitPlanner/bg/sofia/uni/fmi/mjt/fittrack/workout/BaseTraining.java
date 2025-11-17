package bg.sofia.uni.fmi.mjt.fittrack.workout;

public abstract class BaseTraining {
    private String name;
    private int duration;
    private int caloriesBurned;
    private int difficulty;
    private WorkoutType type;

    public BaseTraining(String name, int duration, int caloriesBurned, int difficulty, WorkoutType type){
        setName(name);
        setDuration(duration);
        setCaloriesBurned(caloriesBurned);
        setDifficulty(difficulty);
        setType(type);
    }

    public void setName(String name)
    {
        if(name == null || name.isEmpty())
        {
            throw new InvalidWorkoutException("Invalid name for workout");
        }

        this.name = name;
    }

    public void setDuration(int duration)
    {
        if(duration < 0)
        {
            throw new InvalidWorkoutException("Invalid duration for workout");
        }

        this.duration = duration;
    }

    public void setCaloriesBurned(int caloriesBurned)
    {
        if(caloriesBurned < 0)
        {
            throw new InvalidWorkoutException("Invalid calories burned for workout");
        }

        this.caloriesBurned = caloriesBurned;
    }

    public void setDifficulty(int difficulty){
        if(difficulty < 0)
        {
            throw new InvalidWorkoutException("Invalid calories burned for workout");
        }

        this.difficulty = difficulty;
    }

    public void setType(WorkoutType type)
    {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public WorkoutType getType() {
        return type;
    }
}
