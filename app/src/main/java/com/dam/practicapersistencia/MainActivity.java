package com.dam.practicapersistencia;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends Activity {
    private final static int LISTA = 0;

    TextView txtID;
    EditText editNombre,editMensaje, editSala;
    private Button botonGuardar;
    private Button botonEnviar;
    private Button botonListar;
    private Button botonCerrar;
    private Button botonEliminar;
    private Button botonSala;
    private MiBaseDatos MDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID   = (TextView) findViewById(R.id.txtID);
        editNombre   = (EditText) findViewById(R.id.txtNombre);
        editSala   = (EditText) findViewById(R.id.txtSala);
        editMensaje = (EditText) findViewById(R.id.txtMensaje);

        //Botones
        botonEnviar = (Button) findViewById(R.id.btEnviar_mensaje);




        MDB=new MiBaseDatos(getApplicationContext());

        //Guardar el contacto actual en la agenda
        botonSala.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                boolean res=MDB.insertarMENSAJE(0,editNombre.getText().toString(),
                        editMensaje.getText().toString());
                if(res)
                    Toast.makeText(getApplicationContext(),
                            "añadido correctamente", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),
                            "No se ha podido guardar el contacto" ,   Toast.LENGTH_LONG).show();
            }

        });

        //Llamar al contacto actual por teléfono
        botonEnviar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Mostrar un mensaje de confirmación antes de realizar la llamada
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage("¿Desea enviar este mensaje a la sala seleccionada?");
                alertDialog.setTitle("Mandando mensaje...");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            EditText texto_nombre=(EditText)findViewById(R.id.txtNombre);
                            EditText texto_sala=(EditText)findViewById(R.id.txtSala);
                            EditText texto_mensaje=(EditText)findViewById(R.id.txtMensaje);
                            int sala = Integer.parseInt(texto_sala.getText().toString());
                            String nombre = texto_nombre.getText().toString().trim();
                            String mensaje = texto_mensaje.getText().toString().trim();
                            Toast.makeText(getApplicationContext(),
                                    "Enviando mensaje a la sala: " + texto_sala.getText().toString().trim(),
                                    Toast.LENGTH_LONG).show();
                            MDB.insertarMENSAJE(sala, nombre, mensaje);
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),
                                    "No se ha podido enviar el mensaje.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(getApplicationContext(),
                                "Envío cancelado", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.show();
            }
        });

        //Cerrar aplicación Android
       /* botonCerrar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }
        });
        boton
        // Escribimos 4 registros en nuestra tabla
        MDB.insertarCONTACTO(1, "Lorenzo", "912668282", "lorenzo@miemail.es");
        MDB.insertarCONTACTO(2, "Joaquín", "954626262", "joaquin@gmail.com");
        MDB.insertarCONTACTO(3, "Elena", "607000000", "elena@hotmail.es");
        MDB.insertarCONTACTO(4, "Rocío", "648121212", "rocio@miemail.es");

        */

    }
    public void clicEnBoton_IrSala (View view) {
        Intent intent = new Intent(this, ListaActivity.class);
        startActivityForResult(intent, LISTA);
    }
    /*
    public void clicEnBoton_IrBorrarContacto (View view) {
        boolean res = MDB.borrarCONTACTO(Integer.parseInt(txtID.getText().toString()));
        if(res)
            Toast.makeText(getApplicationContext(),
                    "Contacto eliminado : "+editNombre.getText().toString(), Toast.LENGTH_LONG).show();
    }

    public void clicEnBoton_IrModificarContacto (View view) {
        boolean res = MDB.modificarCONTACTO(Integer.parseInt(txtID.getText().toString()), editNombre.getText().toString(), editTelefono.getText().toString(), editEmail.getText().toString());
        if(res)
            Toast.makeText(getApplicationContext(),
                    "Contacto modificado : "+editNombre.getText().toString(), Toast.LENGTH_LONG).show();
    }


*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == LISTA) {

            if(resultCode == RESULT_OK){
                String nombre=data.getStringExtra("nombre");
                String telefono=data.getStringExtra("telefono");
                String email=data.getStringExtra("email");
                String ID=data.getStringExtra("ID");

                editNombre.setText(nombre);
                editMensaje.setText(telefono);
                txtID.setText(ID);
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}