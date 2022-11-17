
public class Feedback {
    public Conference conference;
    public Agent giver;
    public double payoff;//instead of boolean feedback
    
    public Feedback(Conference conference, Agent giver, double payoff){
        this.conference = conference;
        this.giver = giver;
        this.payoff = payoff;
    }
}
