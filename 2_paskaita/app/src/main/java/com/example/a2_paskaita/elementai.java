package com.example.a2_paskaita;

import android.app.usage.UsageEvents;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class elementai extends AppCompatActivity {

    private TextView currentMonthTextView;
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_elementai);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentMonthTextView = (TextView) findViewById(R.id.current_month_textView);
        compactCalendarView =(CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);

        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);
        long timeInMls = dateTime.atOffset(ZoneOffset.of("+03:00")).toInstant().toEpochMilli();

        Event ev1 = new Event(Color.GREEN,1537747200000L,"Kazkokie duomenys");
        compactCalendarView.addEvent(ev1);

        dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Toast toast = Toast.makeText(getApplicationContext(),"Diena buvo paspausta: " + dateClicked,Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                currentMonthTextView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });
    }
}