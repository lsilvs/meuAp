package curso.ufmg;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*variaveis Banco de Dados*/
import static curso.ufmg.Constants.TABLE_NAME;
import static curso.ufmg.Constants.TYPE;
import static curso.ufmg.Constants.STATUS;
import static curso.ufmg.Constants.SIZE;
import static curso.ufmg.Constants.PHONE;

public class ShowActivity extends ListActivity {
	
	
	private EstateData db_imovel;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_list);
        String[] lista_imoveis = convert_rows_to_strings(getImoveis());
        ListAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista_imoveis);
        
        setListAdapter(adapter);
        
    }
    
    private Cursor getImoveis(){
    	db_imovel = new EstateData(this);
    	SQLiteDatabase db = db_imovel.getReadableDatabase();
    	
   	
    	Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
    	startManagingCursor(cursor);
    	return(cursor);
    }
    
	private String[] convert_rows_to_strings(Cursor cursor){
    	
    	String[] mString = new String[cursor.getCount()]; int i = 0;
    	
    	while(cursor.moveToNext()){
    		StringBuilder builder = new StringBuilder();
    		String id = cursor.getString(0);
    		String type = cursor.getString(1);
    		String size = cursor.getString(2);
    		String status = cursor.getString(3);
    		String phone = cursor.getString(6);
    		
    	    builder.append(id).append(":");
    	    builder.append(type).append(":");
    	    builder.append(size).append(":");
    	    builder.append(status).append(":");
    	    builder.append(phone).append("");
    		
    		mString[i] = builder.toString();
    		
    		i++;
    		
    	}
    	return (mString);
    }
    			

}
