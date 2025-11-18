package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;

public class TypeWorkoutFilter implements WorkoutFilter{
    WorkoutType type;

    public TypeWorkoutFilter(WorkoutType type){
        setType(type);
    }

    public void setType(WorkoutType type)
    {
        if(type == null)
            throw new IllegalArgumentException("Type cannt be null");

        this.type = type;
    }
    @Override
    public boolean matches(Workout workout) {
        return workout.getType() == type;
    }
}
