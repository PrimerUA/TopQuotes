package top.quotes.pkg;

import top.quotes.pkg.R;
import top.quotes.pkg.util.PreferencesLoader;
import top.quotes.pkg.util.controllers.LanguageController;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

public class PreferencesScreen extends Activity implements OnCheckedChangeListener {

	private LinearLayout contentLayout;

	private RadioButton themePinkRadio;
	private RadioButton themeWhiteRadio;

	private RadioButton languageRusRadio;
	private RadioButton languageEngRadio;

	private CheckBox dailyCheckBox;

	private Button moreButton;
	private Button rateButton;

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferences_screen);

		initScreen();

		loadPreferencesOnScreen();
	}

	private void loadPreferencesOnScreen() {
		LanguageController language = PreferencesLoader.getLanguage();
		int theme = PreferencesLoader.getTheme();
		Boolean daily = PreferencesLoader.isDaily();

		if (language == LanguageController.RUS) {
			languageRusRadio.setChecked(true);
		} else {
			languageEngRadio.setChecked(true);
		}

		if (theme == 0) {
			themePinkRadio.setChecked(true);
		} else {
			themeWhiteRadio.setChecked(true);
		}

		dailyCheckBox.setChecked(daily);
	}

	private void initScreen() {
		context = this;

		contentLayout = (LinearLayout) findViewById(R.id.PreferencesScreen_contentLayout);

		moreButton = (Button) findViewById(R.id.PreferencesScreen_moreButton);
		rateButton = (Button) findViewById(R.id.WelcomeScreen_startButton);

		themePinkRadio = (RadioButton) findViewById(R.id.PreferencesScreen_themeRadioPink);
		themeWhiteRadio = (RadioButton) findViewById(R.id.PreferencesScreen_themeRadioWhite);

		languageRusRadio = (RadioButton) findViewById(R.id.PreferencesScreen_languageRadioRussian);
		languageEngRadio = (RadioButton) findViewById(R.id.PreferencesScreen_languageRadioEnglish);

		dailyCheckBox = (CheckBox) findViewById(R.id.PreferencesScreen_dailyCheckBox);

		OnCheckedChangeListener themeChangeListener = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (themePinkRadio.isChecked()) {
					PreferencesLoader.setTheme(0);
					contentLayout.setBackgroundResource(R.drawable.quote_border_pink);
				} else {
					PreferencesLoader.setTheme(1);
					contentLayout.setBackgroundResource(R.drawable.quote_border_white);
				}
			}
		};

		themeWhiteRadio.setOnCheckedChangeListener(themeChangeListener);
		themePinkRadio.setOnCheckedChangeListener(themeChangeListener);

		OnCheckedChangeListener languageChangeListener = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (languageRusRadio.isChecked()) {
					PreferencesLoader.setLanguage(LanguageController.RUS);
				} else {
					PreferencesLoader.setLanguage(LanguageController.ENG);
				}
			}
		};

		languageRusRadio.setOnCheckedChangeListener(languageChangeListener);
		languageEngRadio.setOnCheckedChangeListener(languageChangeListener);

		dailyCheckBox.setOnCheckedChangeListener(this);

		moreButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(getApplicationContext(), MoreScreen.class);
				startActivity(browserIntent);
			}
		});

		rateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle(R.string.rate_title_text);
				builder.setMessage(R.string.rate_content_text);
				builder.setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_market_url)));
						startActivity(browserIntent);
					}
				});
				builder.setCancelable(false);
				builder.show();
			}
		});
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		PreferencesLoader.setDaily(isChecked);
	}
}