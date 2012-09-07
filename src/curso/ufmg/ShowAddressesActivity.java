package curso.ufmg;

import static curso.ufmg.Constants.TABLE_NAME;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;


import static curso.ufmg.Constants.LATITUDE;
import static curso.ufmg.Constants.LONGITUDE;

public class ShowAddressesActivity extends MapActivity {
private List<GeoPoint> coords;
private EstateData db_imovel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_addresses);
        MapView mapView = (MapView)findViewById(R.id.map);
        mapView.setBuiltInZoomControls(true);
        
        MapController mc = mapView.getController();
        
        List<Overlay> listOfOverlays = mapView.getOverlays();
        listOfOverlays.clear();
        coords = new ArrayList<GeoPoint>();
        coords.clear();
        
        Cursor cursor = getImoveis();
        while (cursor.moveToNext()){

        	
        	
            String coordenates[] = {cursor.getString(0),cursor.getString(1)};
            double lat = Double.parseDouble(coordenates[0]);
            double lng = Double.parseDouble(coordenates[1]);
            coords.add(new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6)));
            
        	if(cursor.isFirst()){
                mc.animateTo(new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6)));
                mc.setZoom(17);
        	}
        }
        
        MapOverlay mapOverlay = new MapOverlay();
        listOfOverlays.add(mapOverlay);
        
        mapView.invalidate();
        
        
    }


	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	class MapOverlay extends com.google.android.maps.Overlay{
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when){
			super.draw(canvas, mapView, shadow);
			for (GeoPoint gp : coords){
				Point screenPts = new Point();
				mapView.getProjection().toPixels(gp, screenPts);
				Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.e1);
				canvas.drawBitmap(bmp, screenPts.x, screenPts.y -50, null);
			}
			return true;
		}
	}
	
    private Cursor getImoveis(){
    	db_imovel = new EstateData(this);
    	SQLiteDatabase db = db_imovel.getReadableDatabase();
    	
   	
    	Cursor cursor = db.query(TABLE_NAME, new String[] {LATITUDE, LONGITUDE}, null, null, null, null, null);
    	startManagingCursor(cursor);
    	return(cursor);
    }
}
