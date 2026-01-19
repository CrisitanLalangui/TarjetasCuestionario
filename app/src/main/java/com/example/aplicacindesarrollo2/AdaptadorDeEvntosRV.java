package com.example.aplicacindesarrollo2;

import static java.lang.Thread.sleep;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class AdaptadorDeEvntosRV extends RecyclerView.Adapter<AdaptadorDeEvntosRV.SostenDeVistas> {

    //Se implmentan los tres dm´´etodos

    //static
    Context context; // El permiso

    static ArrayList<EventModel> events;

    public AdaptadorDeEvntosRV(Context context, ArrayList<EventModel> events) {
        this.context = context;
        this.events = events;

    }

    @NonNull
    @Override
    public AdaptadorDeEvntosRV.SostenDeVistas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cv_row, parent, false);

        return new AdaptadorDeEvntosRV.SostenDeVistas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorDeEvntosRV.SostenDeVistas sosten, int position) {

        sosten.tvName.setText(events.get(position).getEventName());
        sosten.tvLocation.setText(events.get(position).getEventLocation());



    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    public class SostenDeVistas extends RecyclerView.ViewHolder {

        TextView tvName, tvLocation;
        CardView card;

        public SostenDeVistas(@NonNull View itemView) {

            super(itemView);
            tvName = itemView.findViewById(R.id.textView);
            tvLocation = itemView.findViewById(R.id.textView3);
            card = itemView.findViewById(R.id.cardview);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
                    View view = inflater.inflate(R.layout.alert_dialog, null);
                    builder.setTitle(tvName.getText())
                            .setMessage("¿En que año ocurrió?")
                            .setView(view)
                            .setNegativeButton("Cancelar", (dialog, which) -> {

                            })
                            .setPositiveButton("Aceptar", (dialog, which) -> {

                                String nombre = tvName.getText().toString();
                                String localizacion = tvLocation.getText().toString();

                                EditText editText = view.findViewById(R.id.textInputEditText);



                                String respuesta = editText.getText().toString();

                                if (respuesta.isEmpty()){
                                    Toast.makeText(itemView.getContext(), "La respuesta no puede estar vacía", Toast.LENGTH_SHORT).show();

                                } else if (!respuesta.equals(events.get(getAdapterPosition()).getEventDate())){
                                    card.setCardBackgroundColor(card.getResources().getColor(R.color.cafeOscuro));
                                    Toast.makeText(itemView.getContext(), "La respuesta es incorrecta", Toast.LENGTH_SHORT).show();

                                } else {

                                    card.setCardBackgroundColor(card.getResources().getColor(R.color.rojoClaro));

                                        EventModel eventModel;
                                        int indice = -1;

                                        for (int i = 0; i < events.size(); i++) {
                                            eventModel = events.get(i);

                                            if (eventModel.getEventName().equals(nombre)
                                                    && eventModel.getEventDate().equals(events.get(getAdapterPosition()).getEventDate())
                                                    && eventModel.getEventLocation().equals(localizacion)) {
                                                indice = i;
                                            }
                                        }

                                        if (indice != -1) {
                                            events.remove(indice);
                                            AdaptadorDeEvntosRV.this.notifyItemRemoved(indice);
                                        }
                                         Toast.makeText(itemView.getContext(), "La respuesta es correcta", Toast.LENGTH_SHORT).show();
                                }

                            });


                    builder.create().show();

                }
            });
        }
    }
}






