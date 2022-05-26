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

public class DevoirAnswersAdapter extends ArrayAdapter<DevoirAnswer> {
    public DevoirAnswersAdapter(@NonNull Context context, List<DevoirAnswer> answers) {
        super(context, 0,answers);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        DevoirAnswer answer=getItem(position);
        if (convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.itemdevoiranswer,parent,false);

            TextView usernom= convertView.findViewById(R.id.elevenomd);
            TextView userprenom= convertView.findViewById(R.id.eleveprenomd);
            TextView testname= convertView.findViewById(R.id.testnamed);
            TextView testdate= convertView.findViewById(R.id.datetestd);


            usernom.setText(answer.getNom());
            userprenom.setText(answer.getPrenom());
            testname.setText(answer.getNomdevoir());
            testdate.setText(answer.getDatedvoir());


        return convertView;
    }
}
