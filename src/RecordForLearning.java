
public class RecordForLearning {
    
    //Weights of callee, caller and neighbor payoffs
    //PAYOFF = AVG(weights[0]*Callee_Payoff, weights[1]*Caller_Payoff, 
    //  weights[2]*AVG(Neighbor_Payoff))
	//Perfect
//    public static double[] weights = new double[]{1.0,1.0,1.0};
    //Selfish
//    public static double[] weights = new double[]{3.0,0.0,0.0};
    //Generous
    public static double[] weights = new double[]{1.5,0.0,1.5};
    
    public static String[] relationTypes = new String[]{"family","colleague","friend","stranger"};
    
    public int location;
    public int originatorRelation;
    public int switchOption;
    public int action; //0 for ignored, 1 for answered
    public boolean existsFamily;
    public boolean existsColleague;
    public boolean existsFriend;
    public double calleePayoff;
    public double originatorPayoff;
    public double averageNeighborPayoff;
    
    public RecordForLearning(){
        location = 0;
        originatorRelation = 3;
        switchOption = 0;
        action = 0;
        existsFamily = false;
        existsColleague = false;
        existsFriend = false;
        calleePayoff = 0.0;
        originatorPayoff = 0.0;
        averageNeighborPayoff = 0.0;
    }
    
    public RecordForLearning(Conference conference, Agents agents){
        this.location = (int)(conference.location/Agents.agentsCount);
        if (conference.isFamily())
            this.originatorRelation = 0;
        else if (conference.isColleague())
            this.originatorRelation = 1;
        else if (conference.isFriend())
            this.originatorRelation = 2;
        else
            this.originatorRelation = 3;
        
        this.switchOption = conference.switchOption;
        this.action = conference.action;
        
        this.existsFamily = false;
        this.existsColleague = false;
        this.existsFriend = false;

        //callee payoff and caller payoff
        this.calleePayoff = 0.0;
        this.originatorPayoff = 0.0;
        if (conference.action==1){
            //Callee payoff
            //if Callee does not know caller
            if (conference.isStranger()){
                calleePayoff = (conference.switchOption == 1 ?agents.payoff_a[6]:agents.payoff_a[4]);
            }else
                calleePayoff = (conference.switchOption == 1 ?agents.payoff_a[2]:agents.payoff_a[0]);
            //Caller payoff
            originatorPayoff = (conference.switchOption == 1 ?agents.payoff_a[10]:agents.payoff_a[8]);
        }else{
            //Callee payoff
            if (conference.isStranger()){
                calleePayoff = (conference.switchOption == 1 ?agents.payoff_a[7]:agents.payoff_a[5]);
            }else
                calleePayoff = (conference.switchOption == 1 ?agents.payoff_a[3]:agents.payoff_a[1]);
            //Caller payoff
            originatorPayoff = (conference.switchOption == 1 ?agents.payoff_a[11]:agents.payoff_a[9]);
        }
        
        averageNeighborPayoff = 0.0;
        Feedback feedback;
        for(int i = 0; i< conference.feedbacks.size(); i++){
            feedback = (Feedback) conference.feedbacks.get(i);
            if (feedback.giver.familyCircle== conference.callee.familyCircle)
                this.existsFamily = true;
            if (feedback.giver.colleagueCircle== conference.callee.colleagueCircle)
                this.existsColleague = true;
            if (feedback.giver.friendCircle== conference.callee.friendCircle)
                this.existsFriend = true;
            
            averageNeighborPayoff+= feedback.payoff;
        }
        if (conference.feedbacks.size()>0)
            averageNeighborPayoff/= conference.feedbacks.size();
    }
    
    public double getPayoff(){
        return (weights[0]*calleePayoff
                +weights[1]* originatorPayoff
                +weights[2]*averageNeighborPayoff)/3.0;
    }
    
    public int getOrdinalFeedback(){
        double payoff = this.getPayoff();
        if (payoff>=1) return 2;
        else if (payoff>=0.5)
            return 1;
        else if (payoff>=-0.5)
            return 0;
        else if (payoff>=-1)
            return -1;
        else
            return -2;
    }
    
    public String toCSVString(){
        return Agents.locations[location]+","
                +relationTypes[originatorRelation]+","
                + switchOption +","
                +existsFamily+","
                +existsColleague+","
                +existsFriend+","
                +(action==1?"Answer":"Ignore")+","
                +calleePayoff+","
                + originatorPayoff +","
                +averageNeighborPayoff;
    }
    
}
