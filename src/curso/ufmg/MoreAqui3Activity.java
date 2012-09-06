package curso.ufmg;

import static curso.ufmg.Constants.TABLE_NAME;

import java.io.Serializable;

import server.Command;
import server.DAO;
import server.DaoImpl;
import server.Invoker;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MoreAqui3Activity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private EstatesData db_imovel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //final EditText newApButton = (EditText) findViewById(R.id.newApButton);
        // final EditText searchApButton = (EditText) findViewById(R.id.searchApButton);
        // final EditText mapApButton = (EditText) findViewById(R.id.mapApButton);
        
        Button newApButton = (Button) findViewById(R.id.newApButton);
        newApButton.setOnClickListener(this);
        
        Button searchApButton = (Button) findViewById(R.id.searchApButton);
        searchApButton.setOnClickListener(this);
        
        Button mapApButton = (Button) findViewById(R.id.mapApButton);
        mapApButton.setOnClickListener(this);
   
        Button recordButton = (Button) findViewById(R.id.recordApButton);
        recordButton.setOnClickListener(this);
        
        
    }

	//@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case R.id.newApButton: 
    		i = new Intent(this, InsertActivity.class);
    		startActivity(i);
    		break;
		case R.id.searchApButton: 
    		i = new Intent(this, ShowActivity.class);
    		startActivity(i);
    		break;
		case R.id.mapApButton: 
    		i = new Intent(this, InsertActivity.class);
    		startActivity(i);
    		break;
		case R.id.recordApButton:

			DaoImpl daoImpl = new DaoImpl();
			Command cmd = new Command() {
				
				public void execute(DaoImpl d) {
					String[] imovel = new String[4];
					
					
			    
					Cursor cursor = getImoveis(); 
					while (cursor.moveToNext()){
			    		imovel[0] = cursor.getString(1);
			    		imovel[1] = cursor.getString(2);
			    		imovel[2] = cursor.getString(3);
			    		imovel[3] = cursor.getString(4);
			    		Imovel imovel_ser = new Imovel(imovel[0],imovel[1],imovel[2],imovel[3]);
			    		d.add(cursor.getLong(0),imovel_ser.toString());
					}					
				}
			};
			
			Invoker invoker = new Invoker(DAO.HOST, DAO.PORT);
			invoker.invoke(daoImpl, cmd);
			
			
						
			
			break;
    	}
	}

    private Cursor getImoveis(){
    	db_imovel = new EstatesData(this);
    	SQLiteDatabase db = db_imovel.getReadableDatabase();
    	
   	
    	Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
    	startManagingCursor(cursor);
    	return(cursor);
    }
    
    private class Imovel implements Serializable
    {
		private static final long serialVersionUID = 1L;

		public Imovel(String type, String size, String status, String phone){
    		this.type = type;
    		this.size = size;
    		this.status = status;
    		this.phone = phone;
    	}
    	
    	public final String type;
    	public final String size;
    	public final String status;
    	public final String phone;
    	
	   	public String toString(){
	   		return "Type: "+type+", Size: "+size+", Status: "+status+", Phone: "+phone;
	   	}
    	
    }
    
}
    
