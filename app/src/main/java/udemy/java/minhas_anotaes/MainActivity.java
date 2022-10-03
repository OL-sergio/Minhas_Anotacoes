package udemy.java.minhas_anotaes;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import udemy.java.minhas_anotaes.databinding.ActivityMainBinding;
import udemy.java.minhas_anotaes.preferences.PreferencesNotes;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    private ActivityMainBinding binding;
    private EditText editTextNote;
    private FloatingActionButton fabSave;

    private PreferencesNotes preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        editTextNote = findViewById(R.id.editText_textNote);
        fabSave = findViewById(R.id.fab_saveNote);

        preferences = new PreferencesNotes(getApplicationContext());

       fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textRetrieved = editTextNote.getText().toString();
                if (textRetrieved.equals("")){
                   Snackbar.make(view, "Prencha a anotação!", Snackbar.LENGTH_LONG).show();

                }else {
                  preferences.saveNotes(textRetrieved);
                    Snackbar.make(view, "Anotação salva com sucesso!!", Snackbar.LENGTH_LONG).show();
                }
            }
        });

       String notes = preferences.retrieveNotes();
        if ( !notes.equals("") ){
            editTextNote.setText( notes);

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}