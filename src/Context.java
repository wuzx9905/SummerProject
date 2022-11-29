import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a context. Maybe a partially filled context view.
 */
public class Context {
    public Boolean callUrgency;
    public Relationship callerRelationship;
    public Location callerLocation;
    public Location calleeLocation;

    public static Context fromVector(List<Object> vector) {
        Context context = new Context();
        context.callUrgency = (Boolean) vector.get(1);
        context.callerRelationship = (Relationship) vector.get(2);
        context.callerLocation = (Location) vector.get(3);
        context.calleeLocation = (Location) vector.get(4);
        return context;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Context other) {
        return new Builder()
                .callUrgency(other.callUrgency)
                .callerRelationship(other.callerRelationship)
                .callerLocation(other.callerLocation)
                .calleeLocation(other.calleeLocation);
    }

    public static class Builder {
        public Boolean callUrgency;
        public Relationship callerRelationship;
        public Location callerLocation;
        public Location calleeLocation;


        public Builder callUrgency(Boolean callUrgency) {
            this.callUrgency = callUrgency;
            return this;
        }

        public Builder callerRelationship(Relationship callerRelationship) {
            this.callerRelationship = callerRelationship;
            return this;
        }

        public Builder callerLocation(Location callerLocation) {
            this.callerLocation = callerLocation;
            return this;
        }

        public Builder calleeLocation(Location calleeLocation) {
            this.calleeLocation = calleeLocation;
            return this;
        }

        public Context build() {
            Context context = new Context();
            context.callUrgency = this.callUrgency;
            context.callerRelationship = this.callerRelationship;
            context.callerLocation = this.callerLocation;
            context.calleeLocation = this.calleeLocation;
            return context;
        }
    }

    //Only fills missing values
    public Context merge(Context other) {
        Context context = Context.builder(this).build();
        if (context.callUrgency == null) context.callUrgency = other.callUrgency;
        if (context.callerRelationship == null) context.callerRelationship = other.callerRelationship;
        if (context.callerLocation == null) context.callerLocation = other.callerLocation;
        if (context.calleeLocation == null) context.calleeLocation = other.calleeLocation;
        return context;
    }

    public void mergeFrom(Context other) {
        if (this.callUrgency == null) this.callUrgency = other.callUrgency;
        if (this.callerRelationship == null) this.callerRelationship = other.callerRelationship;
        if (this.callerLocation == null) this.callerLocation = other.callerLocation;
        if (this.calleeLocation == null) this.calleeLocation = other.calleeLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Context context = (Context) o;
        return Objects.equals(callUrgency, context.callUrgency) &&
                callerRelationship == context.callerRelationship &&
                callerLocation == context.callerLocation &&
                calleeLocation == context.calleeLocation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(callUrgency, callerRelationship, callerLocation, calleeLocation);
    }

    @Override
    public String toString() {
        return "Context{" +
                (callUrgency == null ? "" : ("; callUrgency=" + callUrgency)) +
                (callerRelationship == null ? "" : ("; callerRelationship=" + callerRelationship)) +
                (callerLocation == null ? "" : ("; callerLocation=" + callerLocation)) +
                (calleeLocation == null ? "" : ("; calleeLocation=" + calleeLocation)) +
                '}';
    }

    public List<Object> getVector() {
        List<Object> vector = new ArrayList<>();
        vector.add(callUrgency);
        vector.add(callerRelationship);
        vector.add(callerLocation);
        vector.add(calleeLocation);
        return vector;
    }

    public double getPrivacy() {
        List<Object> vector = getVector();
        int unknowns = 0;
//        for (Object element : vector) {
//            if (element == null) {
//                unknowns++;
//            }
//        }
        //return ((double)unknowns) / vector.size();
        //TODO: Temporarily using 3 as all vector fields are not relevant right now
        if (callUrgency == null) unknowns++;
        if (callerRelationship == null) unknowns++;
        //if (calleeLocation == null) unknowns++;
        return ((double)unknowns) / 2;
    }
}
