package nidhal.student.appfirebase;

public class Student {
    private String name;

    private float noteAndroid;
    private float noteAngular;
    private float noteUX;
    private float noteDB;
    private float noteC;
    private float noteBigData;

    public Student() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNoteAndroid(float noteAndroid) {
        this.noteAndroid = noteAndroid;
    }

    public void setNoteAngular(float noteAngular) {
        this.noteAngular = noteAngular;
    }

    public void setNoteUX(float noteUX) {
        this.noteUX = noteUX;
    }

    public void setNoteDB(float noteDB) {
        this.noteDB = noteDB;
    }

    public void setNoteC(float noteC) {
        this.noteC = noteC;
    }

    public void setNoteBigData(float noteBigData) {
        this.noteBigData = noteBigData;
    }

    public String getName() {
        return name;
    }

    public float getNoteAndroid() {
        return noteAndroid;
    }

    public float getNoteAngular() {
        return noteAngular;
    }

    public float getNoteUX() {
        return noteUX;
    }

    public float getNoteDB() {
        return noteDB;
    }

    public float getNoteC() {
        return noteC;
    }

    public float getNoteBigData() {
        return noteBigData;
    }
}
