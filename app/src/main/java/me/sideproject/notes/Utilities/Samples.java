package me.sideproject.notes.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import me.sideproject.notes.DataBase.Note;

public class Samples {
    private static final String text1 = "Hello that's my first note i hope you are gonna understand it " +
            "and be happy  with us. ";
    private static final String text2 = "Silicon Valley Luxor is the first co-working space here" +
            "we are happy to join us ";
    private static final String text3 = "Here is  long text : First of all  you  have to  remember that going to mars is not impossible thing" +
            "and it is not easy but you can do it. Here is the question How Can i do it? the answer is depends on you  and you are ready for that! so" +
            "you just need to prepare your self for long term  plan to make yourself ready to  start your journey to going mars.";


    private static Date getDiff(int i) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.add(Calendar.MILLISECOND, i);
        return gregorianCalendar.getTime();

    }

    public static List<Note> getSamoles() {
        List<Note> list = new ArrayList<>();
        list.add(new Note( text1, getDiff(-1)));
        list.add(new Note( text2, getDiff(-2)));
        list.add(new Note( text3, getDiff(-3)));


    return list ;}
}
