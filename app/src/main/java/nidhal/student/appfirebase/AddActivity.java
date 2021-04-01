package nidhal.student.appfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    EditText editName;
    EditText editnote1;
    EditText editnote2;
    EditText editnote3;
    EditText editnote4;
    EditText editnote5;
    EditText editnote6;
    DatabaseReference reff;

    Student student;


// String[] allTopics = {"Android", "Angular", "UX", "Databases", "C++", "Big Data"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.editName = findViewById(R.id.editName);
        this.editnote1 = findViewById(R.id.editNoteAndroid);
        this.editnote2 = findViewById(R.id.editNoteAngular);
        this.editnote3 = findViewById(R.id.editNoteUX);
        this.editnote4 = findViewById(R.id.editNoteDB);
        this.editnote5 = findViewById(R.id.editNoteC);
        this.editnote6 = findViewById(R.id.editNoteBigData);

        getIntent();


        reff = FirebaseDatabase.getInstance().getReference().child("Student");
        student = new Student();


    }

    public void CommitData(View v) {

        String name = editName.getText().toString();
        float note1 = Float.parseFloat(editnote1.getText().toString());
        float note2 = Float.parseFloat(editnote2.getText().toString());
        float note3 = Float.parseFloat(editnote3.getText().toString());
        float note4 = Float.parseFloat(editnote4.getText().toString());
        float note5 = Float.parseFloat(editnote5.getText().toString());
        float note6 = Float.parseFloat(editnote6.getText().toString());


        student.setName(name);
        student.setNoteAndroid(note1);
        student.setNoteAngular(note2);
        student.setNoteUX(note3);
        student.setNoteDB(note4);
        student.setNoteC(note5);
        student.setNoteBigData(note6);


        //reff.push().setValue(student);
        reff.child(name).setValue(student);


        Toast.makeText(getApplicationContext(), "Data inserted !", Toast.LENGTH_LONG).show();

        finish();

    }
}