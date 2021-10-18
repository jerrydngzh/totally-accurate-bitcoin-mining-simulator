package cmpt276.assignments.assignment3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cmpt276.assignments.assignment3.R;
import cmpt276.assignments.assignment3.model.OptionsManager;

public class MainMenuActivity extends AppCompatActivity {
    private OptionsManager options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        options = OptionsManager.getInstance();

        updateGameData();
        setHelpButton();
        setOptionsButton();
        setGameButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateGameData();
    }

    private void updateGameData() {
        options.setTotalGames(GameActivity.getTotalGames(this));
        options.setNumMines(OptionsActivity.getNumMines(this));
        options.setBoardDimensionY(OptionsActivity.getBoardDimensionY(this));
        options.setBoardDimensionX(OptionsActivity.getBoardDimensionX(this));

        // generate key based on the game configuration
        String key = options.getBoardDimensionX()
                + "x" + options.getBoardDimensionY()
                + "-" + options.getNumMines();

        options.setTempKey(key);
        options.setBestScore(GameActivity.getGameConfigScore(this, key));
    }

    // https://stackoverflow.com/questions/18404271/android-back-button-to-specific-activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // https://developer.android.com/reference/android/app/Activity
        super.moveTaskToBack(true);
    }

    private void setHelpButton() {
        Button helpBtn = findViewById(R.id.helpBtn);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // make intent to launch HelpActivity
                Intent helpIntent = HelpActivity.makeIntent(MainMenuActivity.this);
                startActivity(helpIntent);
            }
        });
    }

    private void setOptionsButton() {
        Button optionsBtn = findViewById(R.id.optionsBtn);
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent optionsIntent = OptionsActivity.makeIntent(MainMenuActivity.this);
                startActivity(optionsIntent);
            }
        });
    }

    private void setGameButton() {
        Button gameBtn = findViewById(R.id.gameBtn);
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameIntent = GameActivity.makeIntent(MainMenuActivity.this);
                startActivity(gameIntent);
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainMenuActivity.class);
    }
}