package bg.sofia.uni.fmi.mjt.fittrack.workout.filter;

//import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;
import bg.sofia.uni.fmi.mjt.fittrack.workout.Workout;

public class NameWorkoutFilter implements WorkoutFilter {
    String keyword;
    boolean caseSensitive;

    public NameWorkoutFilter(String keyword, boolean caseSensitive){
        setKeyword(keyword);
        this.caseSensitive = caseSensitive;
    }

    @Override
    public boolean matches(Workout workout) {
        String name = caseSensitive ? workout.getName() : workout.getName().toLowerCase();
        String key = caseSensitive ? keyword : keyword.toLowerCase();

        return name.equals(key) || name.contains(key);
    }

    public void setKeyword(String keyword){
        if(keyword == null || keyword.isEmpty())
        {
            throw new IllegalArgumentException("Keyword cannot be nullptr or empty");
        }

        this.keyword = keyword;
    }
}
