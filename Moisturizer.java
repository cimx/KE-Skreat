public class Moisturizer extends Product{
	
	private int _pimples;

	public Moisturizer(){	}

	public Moisturizer(String name, String series, String gender, String price, String pimples){
		super(name,series,gender,price);
		setPimples(Integer.parseInt(pimples));
	}

	public int getPimples(){	return _pimples;	}
	public void setPimples(int pimples){	
		if (pimples == 0 || pimples == 1 || pimples == 2 || pimples == 3) {
			_pimples = pimples;	
		} else {
			throw new IllegalArgumentException("moisturizer's pimple value out of range. should be one of the following: 0-non existent, 1-low, 2-medium, 3-high");
		}
	}
}