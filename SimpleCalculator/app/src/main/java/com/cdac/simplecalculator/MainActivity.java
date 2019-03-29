package com.cdac.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        final EditText editTextA = findViewById(R.id.editTextValueA);
        final EditText editTextB = findViewById(R.id.editTextValueB);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSub = findViewById(R.id.buttonSub);
        Button buttonMul = findViewById(R.id.buttonMul);
        Button buttonDiv = findViewById(R.id.buttonDiv);
        final TextView textViewResult = findViewById(R.id.textViewResult);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strA = editTextA.getText().toString();
                String strB = editTextB.getText().toString();
                int a = Integer.parseInt(strA);
                int b = Integer.parseInt(strB);

                int add = a+b;
                textViewResult.setText("Addition is :: "+add);
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strA = editTextA.getText().toString();
                String strB = editTextB.getText().toString();
                int a = Integer.parseInt(strA);
                int b = Integer.parseInt(strB);

                int sub = a-b;
                textViewResult.setText("Subtraction is :: "+sub);
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strA = editTextA.getText().toString();
                String strB = editTextB.getText().toString();
                int a = Integer.parseInt(strA);
                int b = Integer.parseInt(strB);

                int mul = a*b;
                textViewResult.setText("Multiplication is :: "+mul);
            }
        });

        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strA = editTextA.getText().toString();
                String strB = editTextB.getText().toString();
                int a = Integer.parseInt(strA);
                int b = Integer.parseInt(strB);

                int div = a/b;
                textViewResult.setText("Division is :: "+div);
            }
        });

    }
}
