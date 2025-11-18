package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public class DurationWorkoutFilter implements WorkoutFilter {
    private int min;
    private int max;

    public DurationWorkoutFilter(int min, int max)
    {
        setMin(min);
        setMax(max);
    }

    @Override
    public boolean matches(Workout workout) {
        return workout.getDuration() >= min && workout.getDuration() <= max;
    }

    public void setMin(int min){
        if(min < 0)
            throw new IllegalArgumentException("Min cannot be negative");

        this.min = min;
    }

    public void setMax(int max){
        if(max < 0 || max < min)
            throw new IllegalArgumentException("Max cannot be negative ot max be lower than min");

        this.max = max;
    }
}
