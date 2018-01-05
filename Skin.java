public class Skin {

	private int _wrinkles;
	private boolean _sun_damage;
	private int _pimples;
	private boolean _dry; 
	private boolean _rosacea;
	private int _state;

	public Skin() {	}

	public int getWrinkles(){	return _wrinkles;	}
	public void setWrinkles(int wrinkles){	
		if (wrinkles == 0 || wrinkles == 1 || wrinkles == 2 || wrinkles == 3) {
			_wrinkles = wrinkles;	
		} else {
			throw new IllegalArgumentException("wrinkles value out of range. should be one of the following: 0-non existent, 1-low, 2-medium, 3-high");
		}
	}

	public boolean getSunDamage(){	return _sun_damage;	}
	public void setSunDamage(boolean sun_damage){	_sun_damage = sun_damage;	}
	public void setSunDamage(String sun_damage){	
		if(sun_damage.equals("y") || sun_damage.equals("Y") ||	sun_damage.equals("yes") || sun_damage.equals("YES")){	
			setSunDamage(true);
		} else if(sun_damage.equals("n") || sun_damage.equals("N") || sun_damage.equals("no") || sun_damage.equals("NO")){	
			setSunDamage(false);
		} else{	
			throw new IllegalArgumentException("invalid sun damage input: must be y for yes or n for no");
		}
	}

	public int getPimples(){	return _pimples;	}
	public void setPimples(int pimples){	
		if (pimples == 0 || pimples == 1 || pimples == 2 || pimples == 3) {
			_pimples = pimples;	
		} else {
			throw new IllegalArgumentException("pimples value out of range. should be one of the following: 0-non existent, 1-low, 2-medium, 3-high");
		}
	}

	public boolean getDry(){	return _dry;	}
	public void setDry(boolean dry){	_dry = dry;	}
	public void setDry(String dry){	
		if(dry.equals("y") || dry.equals("Y") ||	dry.equals("yes") || dry.equals("YES")){	
			setDry(true);
		} else if(dry.equals("n") || dry.equals("N") || dry.equals("no") || dry.equals("NO")){	
			setDry(false);
		} else{	
			throw new IllegalArgumentException("invalid dry skin input: must be Y for yes or N for no");
		}
	}

	public boolean getRosacea(){	return _rosacea;	}
	public void setRosacea(boolean rosacea){	_rosacea = rosacea;	}
	public void setRosacea(String rosacea){	
		if(rosacea.equals("y") || rosacea.equals("Y") ||	rosacea.equals("yes") || rosacea.equals("YES")){	
			setRosacea(true);
		} else if(rosacea.equals("n") || rosacea.equals("N") || rosacea.equals("no") || rosacea.equals("NO")){	
			setRosacea(false);
		} else{	
			throw new IllegalArgumentException("invalid rosacea input: must be Y for yes or N for no");
		}
	}

	public int getState(){	return _state;	}
	public void setState(int state){	
		if (state == 1 || state == 2 || state == 3) {
			_state = state;	
		} else {
			throw new IllegalArgumentException("skin state value out of range. should be one of the following: 1-bad, 2-medium, 3-good");
		}
	}

}