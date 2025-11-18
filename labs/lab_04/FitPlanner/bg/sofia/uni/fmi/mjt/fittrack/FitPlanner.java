package bg.sofia.uni.fmi.mjt.fittrack;

import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.WorkoutType;
import bg.sofia.uni.fmi.mjt.fittrack.workout.comparator.WorkoutCaloriesDescendingComparator;
import bg.sofia.uni.fmi.mjt.fittrack.workout.comparator.WorkoutDifficultyDescendingComparator;
import bg.sofia.uni.fmi.mjt.fittrack.workout.filter.WorkoutFilter;

import java.util.*;

public class FitPlanner implements FitPlannerAPI{
    private List<Workout> availableWorkouts;

    FitPlanner(Collection<Workout> availableWorkouts)
    {
        this.availableWorkouts = new ArrayList<>();
        copyWorkouts(availableWorkouts);
    }

    @Override
    public List<Workout> findWorkoutsByFilters(List<WorkoutFilter> filters) {
        if(filters == null)
            throw new IllegalArgumentException("Filters are null");

        return findWorkoutsByFilters(filters);
    }

    private List<Workout> filterWorkouts(List<WorkoutFilter> filters){
        List<Workout> workoutByFilters = new ArrayList<>();

        for(Workout w : availableWorkouts)
        {
            boolean isFiltered = true;
            for(WorkoutFilter wf : filters)
            {
                if(wf == null)
                {
                    continue;
                }
                if(!wf.matches(w))
                {
                    isFiltered = false;
                    break;
                }
            }
            if(isFiltered)
            {
                workoutByFilters.add(w);
            }
        }

        return workoutByFilters;
    }

    @Override
    public List<Workout> generateOptimalWeeklyPlan(int totalMinutes) throws OptimalPlanImpossibleException {
        if(totalMinutes < 0)
            throw new IllegalArgumentException("Duration cannot be negative");

        if(totalMinutes == 0)
        {
            return new ArrayList<>();
        }

        Map<Integer, List<Workout>> workouts = new HashMap<>();
        for(int i = 0; i <= totalMinutes; i++)
        {
            workouts.putIfAbsent(i, new ArrayList<>());
        }

        int[] dp = new int[totalMinutes + 1];
        findOptimalPlan(totalMinutes, dp, workouts);

        if(dp[totalMinutes] == 0)
        {
            throw new OptimalPlanImpossibleException("Not optimal plan possible!");
        }

        List<Workout> optimalPlan = new ArrayList<>(workouts.get(totalMinutes));
        sortOptimalPlan(optimalPlan);

        return optimalPlan;
    }

    private void sortOptimalPlan(List<Workout> optimalPlan){
        optimalPlan.sort(new WorkoutCaloriesDescendingComparator().thenComparing(new WorkoutDifficultyDescendingComparator());
    }

    private void findOptimalPlan(int totalMinutes, int[] dp, Map<Integer, List<Workout>> workouts){
        for(Workout w : availableWorkouts)
        {
            for(int j = totalMinutes; j >= w.getDuration(); j--)
            {
                int newCal = dp[j-w.getDuration()] + w.getCaloriesBurned();

                if(dp[j] < newCal)
                {
                    dp[j] = newCal;
                    workouts.put(j, new ArrayList<>(workouts.get(j-w.getDuration())));
                    workouts.get(j).add(w);
                }
            }
        }
    }

    @Override
    public Map<WorkoutType, List<Workout>> getWorkoutsGroupedByType() {
        Map<WorkoutType, List<Workout>> groupedWorkouts = new Hashtable<>();

        for(Workout w : availableWorkouts){
            WorkoutType type = w.getType();
            groupedWorkouts.putIfAbsent(type, new ArrayList<>());
            groupedWorkouts.get(type).add(w);
        }

        return Collections.unmodifiableMap(groupedWorkouts);
    }

    @Override
    public List<Workout> getWorkoutsSortedByCalories() {
        List<Workout> sortedWorkoutByCalories = new ArrayList<>(availableWorkouts);
        sortedWorkoutByCalories.sort(new WorkoutCaloriesDescendingComparator());
        return sortedWorkoutByCalories;
    }

    @Override
    public List<Workout> getWorkoutsSortedByDifficulty() {
        List<Workout> sortedWorkoutByDifficulty = new ArrayList<>(availableWorkouts);
        sortedWorkoutByDifficulty.sort(new WorkoutDifficultyDescendingComparator());
        return sortedWorkoutByDifficulty;
    }

    @Override
    public Set<Workout> getUnmodifiableWorkoutSet() {
        Set<Workout> workouts = new HashSet<Workout>(availableWorkouts);
        return Collections.unmodifiableSet(workouts);
    }

    private void copyWorkouts(Collection<Workout> availableWorkouts) {
        if (availableWorkouts == null) {
            throw new IllegalArgumentException("Available workouts must be not null!");
        }

        for (Workout availableWorkout : availableWorkouts) {
            if (availableWorkout == null) {
                continue;
            }
            this.availableWorkouts.add(availableWorkout);
        }
    }
}
