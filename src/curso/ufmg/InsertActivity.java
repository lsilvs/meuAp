package curso.ufmg;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import curso.ufmg.Estate;

public class InsertActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);
        
        Button newProntoButton = (Button) findViewById(R.id.btnPronto);
        newProntoButton.setOnClickListener(this);

    }
	
	//@Override
	public void onClick(View v) {
		Context context = getApplicationContext();
		
		switch (v.getId()) {
		case R.id.btnPronto:
			RadioGroup tipo = (RadioGroup)findViewById(R.id.rGroupTipo);
			RadioButton tipoSelected = (RadioButton)findViewById(tipo.getCheckedRadioButtonId());
			
			RadioGroup tamanho = (RadioGroup)findViewById(R.id.rGroupTamanho);
			RadioButton tamanhoSelected = (RadioButton)findViewById(tamanho.getCheckedRadioButtonId());
			
			EditText fone = (EditText) findViewById(R.id.tbxFone);
			
			CheckBox EmConstrucao = (CheckBox) findViewById(R.id.ckbEmConstrucao);
			
			try{

	    		Estate imovel = new Estate(tipoSelected.getText().toString(), tamanhoSelected.getText().toString(), Integer.parseInt(fone.getText().toString()), EmConstrucao.isChecked() ? "sim" : "nao");
   		
	    		Log.v("New", imovel.toString());
				Toast toast = Toast.makeText(context, R.string.InfoSucess, Toast.LENGTH_SHORT);
				toast.show();
	    		
			}
			catch (NumberFormatException e) {
			
					
					Toast toast = Toast.makeText(context, R.string.ErrorNumber, Toast.LENGTH_SHORT);
					toast.show();
				}
			
			break;
    		
	    }
	}
}