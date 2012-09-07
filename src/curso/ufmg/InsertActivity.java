package curso.ufmg;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import static curso.ufmg.Constants.LATITUDE;
import static curso.ufmg.Constants.LONGITUDE;

public class InsertActivity extends Activity implements OnClickListener, LocationListener {
	
	
	/** Called when the activity is first created. */
	private EstateData db_imovel;
	private Double latitude;
	private Double longitude;
	
	
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
			
			/*pegar coordenada GPS*/
			LocationManager locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
			Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, this);
			
			
			try{
	    		
				db_imovel = new EstateData(this);
				
	    		SQLiteDatabase db = db_imovel.getWritableDatabase();
	    		
	    		ContentValues values = new ContentValues();
	    		
	    		values.put(TYPE, tipoSelected.getText().toString());
	    		values.put(SIZE,tamanhoSelected.getText().toString());
	    		values.put(STATUS,EmConstrucao.isChecked() ? "1" : "0");
	    		values.put(PHONE,Integer.parseInt(fone.getText().toString()));
	    		values.put(LATITUDE, location.getLatitude());
	    		values.put(LONGITUDE, location.getLongitude());
	    		
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

	public void onLocationChanged(Location location) {
			    latitude = location.getLatitude();
			    longitude = location.getLongitude();
     }
	

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	


}