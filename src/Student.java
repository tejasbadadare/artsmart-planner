import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class Student implements Comparable, java.io.Serializable {


   private static final long serialVersionUID = 7333993274080664156L;
   
   private boolean robo;
   private boolean half;
   private boolean art;
   private String name;
   private int age;
   private ArrayList daysEnrolled = new ArrayList<Integer>();


   /**
    * @return the age
    */
   public int getAge() {
      return age;
   }

   /**
    * @param age
    *           the age to set
    */
   public void setAge(int age) {
      this.age = age;
   }


   public Student() {
      this.name = "<unnamed>";
   }

   public Student(String name) {
      this.name = name;

   }

   public String toString() {
      String toReturn = getName() + " (Age " + getAge() + ") ";
      if (isHalf()){
         toReturn += " (HALF DAY)"; 
      }
      return toReturn;
   }

   public int compareTo(Object o) {
      Student s = (Student) o;
      if (s.getAge() > this.getAge()) {
         return -1;
      } else if (s.getAge() == this.getAge()) {
         return 0;
      }
      return 1;
   }

   /**
    * @return the robo
    */
   public boolean isRobo() {
      return robo;
   }

   /**
    * @param robo
    *           the robo to set
    */
   public void setRobo(boolean robo) {
      this.robo = robo;
   }

   /**
    * @return the half
    */
   public boolean isHalf() {
      return half;
   }

   /**
    * @param half
    *           the half to set
    */
   public void setHalf(boolean half) {
      this.half = half;
   }

   /**
    * @return the art
    */
   public boolean isArt() {
      return art;
   }

   /**
    * @param art
    *           the art to set
    */
   public void setArt(boolean art) {
      this.art = art;
   }


   /**
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * @param name
    *           the name to set
    */
   public void setName(String name) {
      this.name = name;
   }

   /**
    * @return the daysEnrolled
    */
   public ArrayList getDaysEnrolled() {
      return daysEnrolled;
   }

   /**
    * @param daysEnrolled
    *           the daysEnrolled to set
    */
   public void setDaysEnrolled(ArrayList daysEnrolled) {
      this.daysEnrolled = daysEnrolled;
   }

}
