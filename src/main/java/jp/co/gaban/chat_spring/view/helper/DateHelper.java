package jp.co.gaban.chat_spring.view.helper;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by DaikiTakeuchi on 2016/11/23.
 */
@Component
public class DateHelper {

    public String format(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.JAPANESE);
            return sdf.format(date);
        }
        return "***";
    }
}
