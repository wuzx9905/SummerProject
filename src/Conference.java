import sim.util.*;


public class Conference {
    public Agent originator;
    public Agent callee;
    public int switchOption;
    //0 for audio on and video on;
    //1 for audio on and video off;
    //2 for audio off and video on;
    //3 for audio off and video off;
    
    public int action; //0 for ignored, 1 for answered.
    public Bag feedbacks;
    
    public int location; //keep location, since agents move around
    public long step; //keep step number
    
    public Conference(Agent originator, Agent callee, int switchOption, long step){
        this.originator = originator;
        this.callee = callee;
        this.switchOption = switchOption;
        this.action = -1;
        this.feedbacks = new Bag();
        this.location = callee.location;
        this.step = step;
    }
    
    public Conference(Agent originator, Agent callee, int switchOption, int location, long step){
        this.originator = originator;
        this.callee = callee;
        this.switchOption = switchOption;
        this.action = -1;
        this.feedbacks = new Bag();
        this.location = location;
        this.step = step;
    }
    
    public boolean isFamily(){
        return originator.familyCircle==callee.familyCircle;
    }
    
    public boolean isColleague(){
        return originator.colleagueCircle==callee.colleagueCircle;
    }
    
    public boolean isFriend(){
        return originator.friendCircle==callee.friendCircle;
    }
    
    public boolean isStranger(){
        return !isFamily() && !isColleague() && !isFriend();
    }
}
