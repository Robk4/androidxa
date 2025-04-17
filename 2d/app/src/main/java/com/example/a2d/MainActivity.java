package com.example.a2d;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private DrawView drawView;
    private Button triangleBtn;
    private Button circleBtn;
    private Button recktangleBtn;
    private Button clearBtn;
    private CheckBox fillCb;

    private boolean fill = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        drawView = (DrawView) findViewById(R.id.draw_view);

        triangleBtn = (Button) findViewById(R.id.triangle_btn);
        circleBtn = (Button) findViewById(R.id.circle_btn);
        recktangleBtn = (Button) findViewById(R.id.rectangle_btn);
        clearBtn = (Button) findViewById(R.id.clear_btn);
        fillCb = (CheckBox) findViewById(R.id.fill_cb);

        triangleBtn.setOnClickListener(triangleBtnC);
        circleBtn.setOnClickListener(circleBtnC);
        recktangleBtn.setOnClickListener(rectangleBtnC);
        clearBtn.setOnClickListener(clearBtnC);
        fillCb.setOnCheckedChangeListener(fillCbChange);


    }
    public void setFigure(final int figure, final boolean fill)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                drawView.setFigure(figure);
                drawView.setFill(fill);
                drawView.invalidate();
            }
        });

    }
    CompoundButton.OnCheckedChangeListener fillCbChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            fill = isChecked;
        }
    };

    View.OnClickListener triangleBtnC = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setFigure(1, fill);
        }
    };
    View.OnClickListener circleBtnC = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setFigure(2, fill);
        }
    };
    View.OnClickListener rectangleBtnC = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setFigure(3, fill);
        }
    };
    View.OnClickListener clearBtnC = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setFigure(0, fill);
        }
    };

}