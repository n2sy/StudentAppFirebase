package nidhal.student.appfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lst;
    ArrayList<String> allStudents = new ArrayList<>();// = {"Sara", "Samira", "Sami"};
    ArrayList<String> Notes = new ArrayList<>();
    String[] allTopics = {"Android", "Angular", "UX", "Databases", "C++", "Big Data"};

    String selectedStudent;

    //1st Version
//    String[] NotesSara = {"12", "7", "3.5", "15", "8", "10.5"};
//    String[] NotesSamira = {"2", "4", "18.5", "16", "18", "5.75"};
//    String[] NotesSami = {"11", "17", "5.5", "10", "2", "10.25"};

    //2nd Version
//    String[][] NotesAll = {
//            {"12", "7", "3.5", "15", "8", "10.5"},
//            {"2", "4", "18.5", "16", "18", "5.75"},
//            {"11", "17", "5.5", "10", "2", "10.25"}
//    };

    AutoCompleteTextView autoSaisie;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lst = this.findViewById(R.id.notesLst);

        this.autoSaisie = findViewById(R.id.saisieAuto);

        databaseReference = FirebaseDatabase.getInstance().getReference("Student");



        autoSaisie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedStudent = ((TextView) view).getText().toString();

                MyAdapter adapter;

                getNotes(selectedStudent);
                adapter = new MyAdapter(MainActivity.this, Notes);

                lst.setAdapter(adapter);

                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String selectedTopic = allTopics[position];
                        //TextView myNote = (TextView) view;
                        TextView txt = (TextView) view.findViewById(R.id.note);
                        Double note = Double.parseDouble(txt.getText().toString());



                        if (note >= 10)
                            Toast.makeText(getApplicationContext(), selectedTopic + ": Pass", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), selectedTopic + ": Fail", Toast.LENGTH_LONG).show();
                    }
                });



            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        getStudentsName();

        ArrayAdapter ar = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, this.allStudents);

        autoSaisie.setAdapter(ar);

    }

    public void goToAdd(View v) {
        Intent i = new Intent(this, AddActivity.class);
        i.putExtra("child_name", "Student");
        startActivityForResult(i, 1);
    }

    private void getStudentsName() {
        this.allStudents.clear();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    Student s = ds.getValue(Student.class);
                    allStudents.add(s.getName());

                }

                //Toast.makeText(InfosActivity.this, value, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getNotes(final String studentName) {
        Notes.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Student s = new Student();
                    s = snapshot.child(studentName).getValue(Student.class);
                    if(s.getName().equals(studentName)) { //test facultatif
                        Notes.add(String.valueOf(s.getNoteAndroid()));
                        Notes.add(String.valueOf(s.getNoteAngular()));
                        Notes.add(String.valueOf(s.getNoteUX()));
                        Notes.add(String.valueOf(s.getNoteDB()));
                        Notes.add(String.valueOf(s.getNoteC()));
                        Notes.add(String.valueOf(s.getNoteBigData()));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
