package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public class CaloriesWorkoutFilter implements WorkoutFilter {
    private int min;
    private int max;

    public CaloriesWorkoutFilter(int min, int max)
    {
        setMin(min);
        setMax(max);
    }

    public void setMin(int min){
        this.min = min;
    }

    public void setMax(int max){
        this.max = max;
    }
    @Override
    public boolean matches(Workout workout) {
        return workout.getCaloriesBurned() >= min && workout.getCaloriesBurned() <= max;
    }
}
