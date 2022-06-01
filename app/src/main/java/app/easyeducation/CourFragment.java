package app.easyeducation;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CourFragment extends Fragment implements RecycleViewInterface{

    RecyclerView coursListview;
    DatabaseReference database;
    CourAdapter adapter;
    ArrayList<Cour> cours;
    FloatingActionButton inbox;
    Button post,logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        database= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("Cours");
        View view=inflater.inflate(R.layout.fragment_cour,container,false);
        post=view.findViewById(R.id.post);

        coursListview=view.findViewById(R.id.recyclerViewCour);
        inbox=view.findViewById(R.id.inbox_cour);
        logout=view.findViewById(R.id.logoutCour);





        String type="";

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor=sharedPreferences.edit();

        if (sharedPreferences.contains("type"))
        {

            type=sharedPreferences.getString("type",""); //second is empty cause its the default for String
            if (type.equals("etud"))
            {
                //we need to hide the btn
                //Toast.makeText(getActivity(),"etud",Toast.LENGTH_SHORT).show();
            }
            else if (type.equals("prof"))
            {

                post.setVisibility(View.VISIBLE);
                inbox.setVisibility(View.VISIBLE);

                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent=new Intent(getActivity(),AddNewCour.class);
                        //Toast.makeText(getActivity(),"prof",Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }
                });

                inbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog();
                    }
                });



            }

        }






        coursListview.setLayoutManager(new LinearLayoutManager(getActivity()));

        cours=new ArrayList<>();
        adapter =new CourAdapter(getActivity(),cours,this);
        coursListview.setAdapter(adapter);



        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren())
                {

                    Cour cour=snap.getValue(Cour.class);
                    cours.add(cour);
                }


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),connexion.class);

                setLoggedout();
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    private void setLoggedout() {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean("hasLoggedIn",false);
        editor.apply();
    }

    private void showDialog() {
        Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_devoirtest);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.backgroundrounded);
        Button showtest=dialog.findViewById(R.id.tests);
        Button showdevoirs=dialog.findViewById(R.id.devoirs);

        showtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DiplayTestAnswers.class);
                startActivity(intent);

            }
        });

        showdevoirs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),DisplayDevoirAnswers.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    private void EmptySharedPref()
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    @Override
    public void OnItemClick(int position) {


        Intent intent=new Intent(getActivity(),CourCheck.class);
        Cour selectedCour=cours.get(position); //getting selected cour from list

        intent.putExtra("Courname",selectedCour.getNom());
        intent.putExtra("Courdate",selectedCour.getDate());
        intent.putExtra("CourLink",selectedCour.getLien());
        intent.putExtra("CourModule",selectedCour.getModule());

        startActivity(intent);



    }
}
