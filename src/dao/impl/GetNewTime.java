package dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetNewTime {
    public static String  GetTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return dateFormat.format(date);
    }
}
