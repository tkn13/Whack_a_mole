import java.util.ArrayList;
public class test {
    static ArrayList<heart> arrHeart = new ArrayList<>();
    public static void main(String[] args) {
        arrHeart.add(new heart(0));
        arrHeart.add(new heart(1));
        arrHeart.add(new heart(2));
        System.out.println(arrHeart.toString());
        for(heart h : arrHeart){
            h.setIcon();
        }
    }
    static class heart{
        public int id;
        heart(int i){
            id = i;
        }

        public void setIcon(){
            System.out.println("set " + id);
        }

        public String toString(){
            return ""  + id;
        }
    }
    
}



