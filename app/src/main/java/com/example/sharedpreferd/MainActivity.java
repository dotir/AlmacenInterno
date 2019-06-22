package com.example.sharedpreferd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {


    private EditText txtValor;
    public static final String PREFS_NAME="MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtValor=findViewById(R.id.eValor);

    }

    public void SharedPreferences_nClickGuardar(View v){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor= settings.edit();
        String mSilentMode = txtValor.getText().toString();
        editor.putString("silentMode",mSilentMode);
        editor.commit();
        Log.d("SharedPreferences-Guardar",String.valueOf(mSilentMode));
        Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();

    }

    public void SharedPreferences_nClickLeer(View v){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,0);
        String silent = settings.getString("silentMode","");
        Log.d("SharedPreferences-Guardar",String.valueOf(silent));
        Toast.makeText(this,String.valueOf(silent),Toast.LENGTH_SHORT).show();
    }

    public void AlmacenamientoInterno_OnClickGuardar(View v){
        String FILENAME="hello_file";
        String string= txtValor.getText().toString();

        FileOutputStream fos = null;
        try {
            fos= openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(string.getBytes());
            fos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void AlmacenamientoInterno_OnClickLeer(View v){
        String FILENAME="hello_file";

        FileInputStream fis = null;
        try {
            fis= openFileInput(FILENAME);
            String resultado = getFileContent(fis,"UTF-8");
            Toast.makeText(this,resultado,Toast.LENGTH_SHORT).show();
            fis.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getFileContent(FileInputStream fis, String encoding) throws IOException
    {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(fis,encoding)))
        {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line= br.readLine())!=null){
                sb.append(line);
                //sb.append('\n');

            }
            return sb.toString();
        }
    }
}
