/**
 * @file: Candidate.java
 * @description: This program creates a custom Candidate class that stores all the combine information
 * of any athlete that had participated in the NFL combine from 1987 to 2018
 * @author: Tucker Corwen
 * @date: October 23, 2024
 */

public class Candidate implements Comparable<Candidate> {

    // Declare metrics for the candidate
    float year;                // Year of the candidate's draft or event
    String name;               // Candidate's name
    String college;            // Candidate's college
    String pos;                // Candidate's playing position
    float height_in;           // Candidate's height in inches
    float weight_lbs;          // Candidate's weight in pounds
    float hand_size_in;        // Candidate's hand size in inches
    float arm_length_in;       // Candidate's arm length in inches
    float wonderlic;           // Wonderlic test score
    float forty_yard;          // Time for 40-yard dash (in seconds)
    float bench_press;         // Bench press weight (in pounds)
    float vert_leap_in;        // Vertical leap height in inches
    float broad_jump_in;       // Broad jump distance in inches
    float shuttle;             // Time for shuttle run (in seconds)
    float three_cone;          // Time for three-cone drill (in seconds)
    float sixty_yd_shuttle;    // Time for 60-yard shuttle (in seconds)

    // Constructor initializes all attributes to default values
    Candidate(String[] metrics){
        this.year = 0;
        this.name = null;
        this.college = null;
        this.pos = null;
        this.height_in = 0;
        this.weight_lbs = 0;
        this.hand_size_in = 0;
        this.arm_length_in = 0;
        this.wonderlic = 0;
        this.forty_yard = 0;
        this.bench_press = 0;
        this.vert_leap_in = 0;
        this.broad_jump_in = 0;
        this.shuttle = 0;
        this.three_cone = 0;
        this.sixty_yd_shuttle = 0;
    }

    // Setters to modify candidate metrics
    public void setYear(float year){
        this.year = year;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCollege(String college){
        this.college = college;
    }
    public void setPos(String pos){
        this.pos = pos;
    }
    public void setHeight_in(float height_in){
        this.height_in = height_in;
    }
    public void setWeight_lbs(float weight_lbs){
        this.weight_lbs = weight_lbs;
    }
    public void setHand_size_in(float hand_size_in){
        this.hand_size_in = hand_size_in;
    }
    public void setArm_length_in(float arm_length_in){
        this.arm_length_in = arm_length_in;
    }
    public void setWonderlic(float wonderlic){
        this.wonderlic = wonderlic;
    }
    public void setForty_yard(float forty_yard){
        this.forty_yard = forty_yard;
    }
    public void setBench_press(float bench_press){
        this.bench_press = bench_press;
    }
    public void setVert_leap_in(float vert_leap_in){
        this.vert_leap_in = vert_leap_in;
    }
    public void setBroad_jump_in(float broad_jump_in){
        this.broad_jump_in = broad_jump_in;
    }
    public void setShuttle(float shuttle){
        this.shuttle = shuttle;
    }
    public void setThree_cone(float three_cone){
        this.three_cone = three_cone;
    }
    public void setSixty_yd_shuttle(float sixty_yd_shuttle){
        this.sixty_yd_shuttle = sixty_yd_shuttle;
    }

    // Getters to retrieve candidate metrics
    public float getYear(){
        return this.year;
    }
    public String getName(){
        return this.name;
    }
    public String getCollege(){
        return this.college;
    }
    public String getPos(){
        return this.pos;
    }
    public float getHeight_in(){
        return this.height_in;
    }
    public float getWeight_lbs(){
        return this.weight_lbs;
    }
    public float getHand_size_in(){
        return this.hand_size_in;
    }
    public float getArm_length_in(){
        return this.arm_length_in;
    }
    public float getWonderlic(){
        return this.wonderlic;
    }
    public float getForty_yard(){
        return this.forty_yard;
    }
    public float getBench_press(){
        return this.bench_press;
    }
    public float getVert_leap_in(){
        return this.vert_leap_in;
    }
    public float getBroad_jump_in(){
        return this.broad_jump_in;
    }
    public float getShuttle(){
        return this.shuttle;
    }
    public float getThree_cone(){
        return this.three_cone;
    }
    public float getSixty_yd_shuttle(){
        return this.sixty_yd_shuttle;
    }

    // Override toString() to return a string of all candidate information
    @Override
    public String toString(){
        //Make year number int
        int year = (int)this.getYear();

        return name + ": " + "Year - " + year + ", College - " + college + ", Position - " + pos
                + ", Height(in) - " + height_in + ", Weight(lbs) - " + weight_lbs
                + ", Hand Size(in) - " + hand_size_in + ", Arm Length(in) - " + arm_length_in
                + ", Wonderlic - " + wonderlic + ", Forty Yard Dash(s) - " + forty_yard
                + ", Bench Press(lbs) - " + bench_press + ", Vertical Leap(in) - " + vert_leap_in
                + ", Broad Jump(in) - " + broad_jump_in + ", Shuttle(s) - " + shuttle
                + ", Three Cone(s) - " + three_cone + ", Sixty Yard Dash(s) - " + sixty_yd_shuttle;
    }

    // Check if two Candidate objects are equal based on type
    @Override
    public boolean equals(Object o){
        if (this == o) return true;          // Check if both references point to the same object
        if (o == null || getClass() != o.getClass()) {  // Ensure both are of the same class type
            return false;
        }

        return false;
    }

    // Implement compareTo() method for sorting based on weight in pounds
    @Override
    public int compareTo(Candidate candidate){
        if(this.weight_lbs == candidate.getWeight_lbs()){
            return 0;  // Both candidates have the same weight
        } else if(this.weight_lbs > candidate.getWeight_lbs()){
            return 1;  // This candidate is heavier
        } else if (this.weight_lbs < candidate.getWeight_lbs()) {
            return -1; // This candidate is lighter
        }
        return 0; // Fallback case (should never be reached)
    }
}
