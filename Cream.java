public class Cream extends Product{
	
	private int _spf;
	private int _pimples;
	private boolean _sun_damage;
	private boolean _dry_skin;
	private int _wrinkles;
	private int _strength;
	private boolean _anti_aging;
	private String _season;
	private int _min_age;
	private int _max_age;
	private String _time_of_day;

	public Cream(String name, String series, String gender, String price, String spf, String pimples, String sun, String dry, String wrinkles, String strength, String anti_aging, String season, String min, String max, String time){
		super(name,series,gender,price);
		setSpf(Integer.parseInt(spf));
		setPimples(Integer.parseInt(pimples));
		setSunDamage(Boolean.parseBoolean(sun));
		setDrySkin(Boolean.parseBoolean(dry));
		setWrinkles(Integer.parseInt(wrinkles));
		setStrength(Integer.parseInt(strength));
		setAntiAging(Boolean.parseBoolean(anti_aging));
		setSeason(season);
		setMinAge(Integer.parseInt(min));
		setMaxAge(Integer.parseInt(max));
		setTimeOfDay(time);
	}
	public int getSpf(){	return _spf;	}
	public void setSpf(int spf){	
		if (spf >= 0 && spf <= 100) {
			_spf = spf;	
		} else {
			throw new IllegalArgumentException("invalid cream's spf value.");
		}
	}

	public int getPimples(){	return _pimples;	}
	public void setPimples(int pimples){	
		if (pimples == 0 || pimples == 1 || pimples == 2 || pimples == 3) {
			_pimples = pimples;	
		} else {
			throw new IllegalArgumentException("cream's pimple value out of range. should be one of the following: 0-non existent, 1-low, 2-medium, 3-high");
		}
	}

	public boolean getSunDamage(){	return _sun_damage;	}
	public void setSunDamage(boolean sun_damage){	_sun_damage = sun_damage;	}

	public boolean getDrySkin(){	return _dry_skin;	}
	public void setDrySkin(boolean dry_skin){	_dry_skin = dry_skin;	}

	public int getWrinkles(){	return _wrinkles;	}
	public void setWrinkles(int wrinkles){	
		if (wrinkles == 0 || wrinkles == 1 || wrinkles == 2 || wrinkles == 3) {
			_wrinkles = wrinkles;	
		} else {
			throw new IllegalArgumentException("cream's wrinkle value out of range. should be one of the following: 0-non existent, 1-low, 2-medium, 3-high");
		}
	}

	public int getStrength(){	return _strength;	}
	public void setStrength(int strength){	
		if (strength == 1 || strength == 2 || strength == 3) {
			_strength = strength;	
		} else {
			throw new IllegalArgumentException("cream's strength value out of range. should be one of the following: 1-weak, 2-medium, 3-strong");
		}
	}

	public boolean getAntiAging(){	return _anti_aging;	}
	public void setAntiAging(boolean anti_aging){	_anti_aging = anti_aging;	}

	public String getSeason(){	return _season;	}
	public void setSeason(String season){	
		if((season.startsWith("summer") || season.startsWith("winter") || season.startsWith("spring") || season.startsWith("autumn")) &&
			(season.endsWith("summer") || season.endsWith("winter") || season.endsWith("spring") || season.endsWith("autumn"))){
				_season = season;	
			} else if(!season.equals("null")){
				throw new IllegalArgumentException("invalid value of treatment's season");
			}
	}

	public int getMinAge(){	return _min_age;	}
	public void setMinAge(int age){	
		if (age >= 0 && age <= 150) {
			_min_age = age;
		} else {
			throw new IllegalArgumentException("invalid value of product's minimum age");
		}
	}

	public int getMaxAge(){	return _max_age;	}
	public void setMaxAge(int age){	
		if (age >= 0 && age <= 150) {
			_max_age = age;
		} else {
			throw new IllegalArgumentException("invalid value of product's maximum age");
		}
	}
	public String getTimeOfDay(){	return _time_of_day;	}
	public void setTimeOfDay(String time){	
		if(time.equals("day") || time.equals("night") || time.equals("both")){
			_time_of_day = time;
		} else {
			throw new IllegalArgumentException("invalid value of cream's time of day ");
		}
	}
}