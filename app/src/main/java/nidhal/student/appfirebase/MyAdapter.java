package nidhal.student.appfirebase;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    Activity context;
    ArrayList<String> items;

    MyAdapter(Activity c, ArrayList<String> a){
        super(c,R.layout.one_element, a);
        this.context = c;
        this.items = a;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View element = inflater.inflate(R.layout.one_element, null);

        ImageView img = (ImageView) element.findViewById(R.id.image);
        TextView note = (TextView) element.findViewById(R.id.note);

        note.setText(items.get(position));
        float valeur = Float.valueOf(items.get(position));

        if(valeur >= 10)
            img.setImageResource(R.drawable.icon_mood);
        else
            img.setImageResource(R.drawable.icon_mood_cry);

        return element;

    }
}
