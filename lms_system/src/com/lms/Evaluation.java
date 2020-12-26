package com.lms;

public class Evaluation {
    String Name;
    String Type_of_Evaluation;
    int Weight;
    float Marks_Obtained;
    int Total_Marks;

    public Evaluation(String Name, String TOE, int Weight, int Total_Marks){
        this.Name = Name;
        this.Type_of_Evaluation = TOE;
        this.Weight = Weight;
        this.Total_Marks = Total_Marks;
    }

    public int getTotal_Marks() {
        return Total_Marks;
    }

    public void setTotal_Marks(int total_Marks) {
        Total_Marks = total_Marks;
    }

    public float getMarks_Obtained() {
        return Marks_Obtained;
    }

    public void setMarks_Obtained(float marks_Obtained) {
        Marks_Obtained = marks_Obtained;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType_of_Evaluation() {
        return Type_of_Evaluation;
    }

    public void setType_of_Evaluation(String type_of_Evaluation) {
        Type_of_Evaluation = type_of_Evaluation;
    }

    public int getWeight() { return Weight; }

    public void setWeight(int weight) {
        Weight = weight;
    }
}
