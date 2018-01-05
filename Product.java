public class Product{
	
	private String _name;
	private String _series;
	private boolean _female;
	private boolean _male;
	private int _price;

	public Product(){ }
	public Product(String name, String series, String gender, String price){
		setName(name);
		setSeries(series);
		setPrice(Integer.parseInt(price));
		if (gender.equals("female")){
			setFemale(true);
			setMale(false);
		}else if (gender.equals("male")){
			setFemale(false);
			setMale(true);
		}else if (gender.equals("both")){
			setFemale(true);
			setMale(true);
		}else{
			System.out.println(name);
			throw new IllegalArgumentException("product's gender unknown");
		}
	}

	public String getSeries(){	return _series;	}
	public void setSeries(String series){	_series = series;	}

	public String getName(){	return _name;	}
	public void setName(String name){	_name = name;	}

	public boolean getFemale(){	return _female;	}
	public void setFemale(boolean female){	_female = female;	}

	public boolean getMale(){	return _male;	}
	public void setMale(boolean male){	_male = male;	}

	public int getPrice(){	return _price;	}
	public void setPrice(int price){	_price = price;	}

}