public class Treatment{
	
	private double _price;
	private String _season;
	private String _base_series;

	private Cleanser _cleanser;
	private Freshner _freshner;
	private Gel _gel;
	private Oil _oil;
	private Moisturizer _moisturuzer; 
	//private Cream _cream;
	private Cream _day_cream;
	private Cream _night_cream;

	public Treatment(){	
		setBaseSeries("synergie");
	}

	public double getPrice(){	return _price;	}
	public void setPrice(double price){	_price = price;	}

	public String getSeason(){	return _season;	}
	public void setSeason(String season){	
		if (season.equals("summer") || season.equals("winter") || season.equals("spring") || season.equals("autumn")){
			_season = season;	
		} else{
			throw new IllegalArgumentException("invalid value of treatment's season");
		}
	}

	public String getBaseSeries(){	return _base_series;	}
	public void setBaseSeries(String series){	_base_series = series;	}

	public Cleanser getCleanser(){	return _cleanser;	}
	public void setCleanser(Cleanser cleanser){	_cleanser = cleanser;	}

	public Freshner getFreshner(){	return _freshner;	}
	public void setFreshner(Freshner freshner){	_freshner = freshner;	}

	public Gel getGel(){	return _gel;	}
	public void setGel(Gel gel){	_gel = gel;	}

	public Oil getOil(){	return _oil;	}
	public void setOil(Oil oil){	_oil = oil;	}

	public Moisturizer getMoisturizer(){	return _moisturuzer;	}
	public void setMoisturizer(Moisturizer moisturuzer){	_moisturuzer = moisturuzer;	}

	public Cream getDayCream(){	return _day_cream;	}
	public void setDayCream(Cream dcream){	_day_cream = dcream;	}

	public Cream getNightCream(){	return _night_cream;	}
	public void setNightCream(Cream ncream){	_night_cream = ncream;	}


	/*public Cream getCream(){	return _cream;	}
	public void setCream(Cream cream){	_cream = cream;	}*/
}