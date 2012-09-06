package curso.ufmg;

public class EstateLocation extends Estate{
	  /** The lat of the imovel. */
	  public final Double LAT;

	  /** The lon of the imovel. */
	  public final Double LON;
	  
	public EstateLocation(String type, String size, int phone,
			String inConstruction, Double Lat, Double Lon) {
		super(type, size, phone, inConstruction);
		
		this.LON = Lon;
		this.LAT = Lat;
		
	}	
}
