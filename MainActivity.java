package com.example.myapplication; // This should be changed to your unique package name 

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText course1Grade, course2Grade, course3Grade, course4Grade, course5Grade;
    Button computeGPA;
    TextView gpaResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the EditTexts
        course1Grade = findViewById(R.id.course1Grade);
        course2Grade = findViewById(R.id.course2Grade);
        course3Grade = findViewById(R.id.course3Grade);
        course4Grade = findViewById(R.id.course4Grade);
        course5Grade = findViewById(R.id.course5Grade);

        // Initialize the Button and TextView
        computeGPA = findViewById(R.id.computeGPA);
        gpaResult = findViewById(R.id.gpaResult);

        computeGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (computeGPA.getText().toString().equals("Clear Form")) {
                    clearForm();
                } else {
                    computeGPA();
                }
            }
        });
    }

    private void computeGPA() {
        String[] grades = {course1Grade.getText().toString(), course2Grade.getText().toString(),
                course3Grade.getText().toString(), course4Grade.getText().toString(),
                course5Grade.getText().toString()};
        double sum = 0;
        for (String grade : grades) {
            if (grade.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            sum += Double.parseDouble(grade);
        }
        double gpa = sum / grades.length;
        gpaResult.setText("GPA: " + String.format("%.2f", gpa));
        setBackgroundColor(gpa);

        computeGPA.setText("Clear Form");
    }

    private void setBackgroundColor(double gpa) {
        View rootView = findViewById(android.R.id.content);
        if (gpa < 60) {
            rootView.setBackgroundColor(Color.RED);
        } else if (gpa >= 60 && gpa <= 79) {
            rootView.setBackgroundColor(Color.YELLOW);
        } else if (gpa >= 80 && gpa <= 100) {
            rootView.setBackgroundColor(Color.GREEN);
        } else {
            rootView.setBackgroundColor(Color.WHITE); // Default or error state
        }
    }

    private void clearForm() {
        course1Grade.setText("");
        course2Grade.setText("");
        course3Grade.setText("");
        course4Grade.setText("");
        course5Grade.setText("");
        gpaResult.setText("GPA: ");
        computeGPA.setText("Compute GPA");
        findViewById(android.R.id.content).setBackgroundColor(Color.WHITE);
    }
}
