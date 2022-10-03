package udemy.java.minhas_anotaes.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesNotes {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String KEY_NAME = "name";
    private final String NOTES_DATABASE = "notes.preferences";

    public PreferencesNotes(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences(NOTES_DATABASE, 0);
        editor = preferences.edit();
    }


    public void saveNotes(String notes){
        editor.putString(KEY_NAME, notes);
        editor.commit();
    }

    public String retrieveNotes() {
        return preferences.getString(KEY_NAME , "");
    }

}
