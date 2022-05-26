package app.easyeducation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TestAnswersAdapter extends ArrayAdapter<TestAnswer> {
    public TestAnswersAdapter(@NonNull Context context, List<TestAnswer> answers) {
        super(context, 0,answers);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        TestAnswer answer=getItem(position);
        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.itemtestanswer,parent,false);

            TextView usernom= convertView.findViewById(R.id.elevenom);
            TextView userprenom= convertView.findViewById(R.id.eleveprenom);
            TextView testname= convertView.findViewById(R.id.testnameA);
            TextView testdate= convertView.findViewById(R.id.datetestA);


            usernom.setText(answer.getNom());
            userprenom.setText(answer.getPrenom());
            testname.setText(answer.getNomtest());
            testdate.setText(answer.getDatetest());


        return convertView;
    }
}
