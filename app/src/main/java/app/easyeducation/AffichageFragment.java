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

public class AffichageFragment extends Fragment implements RecycleViewInterface{
    Button post,logout;
    RecyclerView affichagelistview;
    DatabaseReference database;
    AffichageAdapter adapter;
    ArrayList<Affichage> affichages;
    FloatingActionButton inbox;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_affichage, container, false);
        post=view.findViewById(R.id.postaffichage);
        inbox=view.findViewById(R.id.inbox_affichage);
        logout=view.findViewById(R.id.logoutAffich);
        database= FirebaseDatabase.getInstance().getReferenceFromUrl("https://easyeducation-80f1b-default-rtdb.firebaseio.com/").child("Affichages");

        affichagelistview =view.findViewById(R.id.recyclerViewaffichage);



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
                        Intent intent =new Intent(getActivity(),AddNewAffichage.class);
                        startActivity(intent);

                    }
                });;


                inbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openDialog();
                    }
                });



            }

        }









        affichagelistview.setLayoutManager(new LinearLayoutManager(getActivity()));

        affichages=new ArrayList<>();
        adapter =new AffichageAdapter(getActivity(),affichages,this);
        affichagelistview.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren())
                {

                    Affichage affichageitem=snap.getValue(Affichage.class);
                    affichages.add(affichageitem);

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

    private void EmptySharedPref()
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


    private void setLoggedout() {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean("hasLoggedIn",false);
        editor.apply();
    }

    private void openDialog() {

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

    @Override
    public void OnItemClick(int position) {
        Intent intent=new Intent(getActivity(),AffichageCheck.class);
        Affichage selectedAffichage=affichages.get(position); //getting selected cour from list

        intent.putExtra("Affichagename",selectedAffichage.getNom());
        intent.putExtra("Affichagedate",selectedAffichage.getDate());
        intent.putExtra("AffichageLink",selectedAffichage.getLien());
        intent.putExtra("AffichageModule",selectedAffichage.getModule());

        startActivity(intent);
    }
}
