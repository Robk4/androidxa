//package com.example.a2_paskaita;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class testavimas extends AppCompatActivity {
//
//    Button generateRandomNumberBtn;
//    EditText insertRandomNumberEditText;
//    int randomNumber = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_testavimas);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        getWindow().getDecorView().setBackgroundColor(Color.GRAY);
//
//        Button generateRandomNumberBtn = (Button) findViewById(R.id.generateRandomNumberBtn);
//
//        generateRandomNumberBtn.setOnClickListener(generateRandomNumberOnClick);
//        EditText insertRandomNumberEditText = (EditText) findViewById(R.id.editText);
//        insertRandomNumberEditText.addTextChangedListener(insertRandomNumberTextWatcher);
//    }
//    View.OnClickListener generateRandomNumberOnClick = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            randomNumber = (int) (Math.random()*1000);
//            Log.i("randomNumber",String.valueOf(randomNumber));
//            Toast toast = Toast.makeText(getApplicationContext(),
//                    String.valueOf(randomNumber),Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    };
//    TextWatcher insertRandomNumberTextWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            if(s.toString().length()>0)
//            {
//                if(Integer.valueOf(s.toString()) == randomNumber)
//                {
//                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
//                }
//                else {
//                    getWindow().getDecorView().setBackgroundColor(Color.RED);
//                }
//            }
//            else {
//                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
//            }
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//
//        }
//    };
//}