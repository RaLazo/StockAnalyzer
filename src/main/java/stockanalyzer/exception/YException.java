package stockanalyzer.exception;
import java.sql.Timestamp;

public class YException extends Exception {

    private Timestamp timestamp = null;
    public YException(String msg){
        super(msg);
        timestamp = new Timestamp(System.currentTimeMillis());
    }
    public YException(){
        this("Any Issue occurred during the fetch of the data");
    }

    public String getMsg(){
        return "YException: "+getTimestamp()+" "+this.getMessage();
    }

    public String getTimestamp(){
        return timestamp.toString();
    }

    @Override
    public void printStackTrace(){
        System.out.println(this.getMsg());
    }
}
