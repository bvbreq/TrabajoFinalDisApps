package com.dam.practicapersistencia;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaActivity extends Activity {
//version andres
    private ListView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        final MiBaseDatos MDB=new MiBaseDatos(getApplicationContext());

        ArrayList<Salas> datos = MDB.recuperarSalas();
        lista = (ListView) findViewById(R.id.ListView_listado);
        lista.setAdapter(new Lista_adaptador(this, R.layout.contacto, datos){
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_sala = (TextView) view.findViewById(R.id.textView_sala);
                    if (texto_sala != null)
                        texto_sala.setText(((Salas) entrada).getSALA());

                    /*TextView texto_mensaje = (TextView) view.findViewById(R.id.textView_mensaje);
                    if (texto_mensaje != null)
                        texto_mensaje.setText(((Salas) entrada).getMENSAJE());

                    TextView texto_ID = (TextView) view.findViewById(R.id.textView_ID);
                    if (texto_ID != null)
                        texto_ID.setText(Integer.toString(((Salas) entrada).getSALA()));

                     */

                }
            }
        });
//      lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textoSala = view.findViewById(R.id.textView_sala);
                CharSequence texto = "Sala seleccionada: Sala " + textoSala.getText();
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();

                // Crear un intento para iniciar la nueva actividad
                Intent intent = new Intent(getApplicationContext(), SalaActivity.class);

                // Poner los datos necesarios en el intento
                intent.putExtra("sala", textoSala.getText().toString());

                // Iniciar la nueva actividad
                startActivity(intent);
            }


        }
           /* @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView textoSala = (TextView) view.findViewById(R.id.textView_sala);
                //TextView textoNombre = (TextView) view.findViewById(R.id.textView_nombre);
               // TextView textomensaje = (TextView) view.findViewById(R.id.textView_mensaje);
                CharSequence texto = "Sala seleccionada: Sala " + textoSala.getText();
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
                /*Intent returnIntent = new Intent();
                returnIntent.putExtra("nombre",textoContacto.getText());
                returnIntent.putExtra("mensaje",textoTelefono.getText());
                returnIntent.putExtra("email",textoEmail.getText());
                returnIntent.putExtra("ID",textoID.getText());
                setResult(RESULT_OK,returnIntent);
                finish();


            }
        });

             */

    }

}
