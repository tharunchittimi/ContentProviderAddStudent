package com.example.contentprovideraddstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextAdd;
    EditText editTextGrade;

    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextAdd = findViewById(R.id.editText);
        editTextGrade = findViewById(R.id.editText2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

    }

    public void onClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(StudentDetails.NAME, editTextAdd.getText().toString());
        values.put(StudentDetails.GRADE, editTextGrade.getText().toString());
        Uri uri = getContentResolver().insert(StudentDetails.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();

    }

    public void onClickRetrieveStudents(View view) {
        String URL = "content://com.example.ContentProviderAddStudent.StudentDetails";
        Uri students = Uri.parse(URL);
        Cursor cursor = managedQuery(students, null, null, null, "name");
        if (validate()) {

            String Name = editTextAdd.getText().toString();
            String Grade = editTextGrade.getText().toString();

            if (cursor.moveToFirst()) {
                do {
                    if (Name.equals(cursor.getString(cursor.getColumnIndex(StudentDetails.NAME)))) {
                        Toast.makeText(this, cursor.getString(cursor.getColumnIndex(StudentDetails._ID)) + ". " +
                                        cursor.getString(cursor.getColumnIndex(StudentDetails.NAME)) +
                                        "," + cursor.getString(cursor.getColumnIndex(StudentDetails.GRADE)),
                                Toast.LENGTH_SHORT).show();
                    }
                } while (cursor.moveToNext());
            }
        }
    }

    private boolean validate() {
        boolean valid = false;

        String Name = editTextAdd.getText().toString();
        String Grade = editTextGrade.getText().toString();

        if (Name.isEmpty()) {
            valid = false;
            editTextAdd.setError("Enter User Name");
        } else if (!Patterns.DOMAIN_NAME.matcher(Name).matches()) {
            valid = true;
            editTextAdd.setError(null);
            if (Grade.isEmpty()) {
                valid = false;
                editTextGrade.setError("Enter Grade");
            } else if (!Patterns.DOMAIN_NAME.matcher(Grade).matches()) {
                valid = true;
                editTextGrade.setError(null);
                if (!Patterns.DOMAIN_NAME.matcher(Name).matches()) {
                    valid = false;
                    editTextAdd.setError("User Name Not Match");
                } else if (!Patterns.DOMAIN_NAME.matcher(Name).matches()) {
                    valid = false;
                    editTextAdd.setError("User Name Not Match");
                }
            }
        }
        return true;
    }

}
