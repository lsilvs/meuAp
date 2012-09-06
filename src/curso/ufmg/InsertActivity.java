package curso.ufmg;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/*variaveis Banco de Dados*/

import static curso.ufmg.Constants.TABLE_NAME;
import static curso.ufmg.Constants.TYPE;
import static curso.ufmg.Constants.STATUS;
import static curso.ufmg.Constants.SIZE;
import static curso.ufmg.Constants.PHONE;

public class InsertActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private EstatesData db_imovel;
	
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
	    		
				db_imovel = new EstatesData(this);
				
	    		SQLiteDatabase db = db_imovel.getWritableDatabase();
	    		
	    		ContentValues values = new ContentValues();
	    		
	    		values.put(TYPE, tipoSelected.getText().toString());
	    		values.put(SIZE,tamanhoSelected.getText().toString());
	    		values.put(STATUS,EmConstrucao.isChecked() ? "sim" : "nao");
	    		values.put(PHONE,Integer.parseInt(fone.getText().toString()));
	    		
	    		db.insertOrThrow(TABLE_NAME, null, values);
	    		
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