import java.lang.String;

public class Customer {

	private Skin _skin;
	private int _age; 
	private boolean _female;
	private boolean _smoker;
	private boolean _outside;
	private boolean _medicines;
	private String _previous_care;
	private boolean _allergies;
	private int _budget;
	private int _motivation;
	private boolean _night_cream;


	public Customer(){	}

	public Skin getSkin(){	return _skin;	}
	public void setSkin(Skin skin){	_skin = skin;	}

	public int getAge(){	return _age;	}
	public void setAge(int age){	
		if (age >= 0 && age <= 150) {
			_age = age;
		} else {
			throw new IllegalArgumentException("invalid value of age");
		}
	}

	public boolean getFemale(){	return _female;	}
	public void setFemale(boolean female){	_female = female;	}
	public void setGender(String gender){	
		if(gender.equals("male") || gender.equals("MALE")){	
			setFemale(false);
		} else if(gender.equals("female") || gender.equals("FEMALE")){	
			setFemale(true);
		} else{	
			throw new IllegalArgumentException("invalid customer's gender");
		}
	}

	public boolean getSmoker(){	return _smoker;	}
	public void setSmoker(boolean smoker){	_smoker = smoker;	}
	public void setSmoker(String smoker){
		if(smoker.equals("y") || smoker.equals("Y") ||	smoker.equals("yes") || smoker.equals("YES")){	
			setSmoker(true);
		} else if(smoker.equals("n") || smoker.equals("N") || smoker.equals("no") || smoker.equals("NO")){	
			setSmoker(false);
		} else{	
			throw new IllegalArgumentException("invalid smoker input: must be y for yes or n for no");
		}
	}

	public boolean getOutside(){	return _outside;	}
	public void setOutside(boolean outside){	_outside = outside;	}
	public void setOutside(String outside){
		if(outside.equals("y") || outside.equals("Y") ||	outside.equals("yes") || outside.equals("YES")){
			setOutside(true);
		} else if(outside.equals("n") || outside.equals("N") || outside.equals("no") || outside.equals("NO")){	
			setOutside(false);
		} else{
			throw new IllegalArgumentException("invalid outside input: must be y for yes or n for no");
		}
	}

	public boolean getMedicines(){	return _medicines;	}
	public void setMedicines(boolean medicines){	_medicines = medicines;	}
	public void setMedicines(String medicines){
		if(medicines.equals("y") || medicines.equals("Y") ||	medicines.equals("yes") || medicines.equals("YES")){
			setMedicines(true);
		} else if(medicines.equals("n") || medicines.equals("N") || medicines.equals("no") || medicines.equals("NO")){
			setMedicines(false);
		} else{
			throw new IllegalArgumentException("invalid medicines input: must be y for yes or n for no");
		}
	}

	public String getPreviousCare(){	return _previous_care;	}
	public void setPreviousCare(String prev_care){	
		if (prev_care.equals("synergie") || prev_care.equals("sensitive")|| prev_care.equals("66days")) {
			_previous_care = prev_care;	
		}else if( prev_care.equals("none")){
			_previous_care = null;
		}else {
			throw new IllegalArgumentException("previous care unknown");
		}
	}

	public boolean getAllergies(){	return _allergies;	}
	public void setAllergies(boolean allergies){	_allergies = allergies;	}
	public void setAllergies(String allergies){
		if(allergies.equals("y") || allergies.equals("Y") ||	allergies.equals("yes") || allergies.equals("YES")){
			setAllergies(true);
		} else if(allergies.equals("n") || allergies.equals("N") || allergies.equals("no") || allergies.equals("NO")){
			setAllergies(false);
		} else{	
			throw new IllegalArgumentException("invalid allergies input: must be y for yes or n for no");	
		}
	}

	public int getBudget(){	return _budget;	}
	public void setBudget(int budget){	
		if (budget == 1 || budget == 2 || budget == 3 || budget == 4) {
			_budget = budget;
		} else {
			throw new IllegalArgumentException("budget value out of range. should be one of the following: 1-low, 2-medium, 3-high, 4-really high");
		}
	}
	public int getMotivation(){	return _motivation;	}
	public void setMotivation(int motivation){	
		if (motivation == 1 || motivation == 2 || motivation == 3) {
			_motivation = motivation;	
		} else {
			throw new IllegalArgumentException("motivation value out of range. should be one of the following: 1-low, 2-medium, 3-high");
		}
	}
	public boolean getNightCream(){	return _night_cream;	}
	public void setNightCream(boolean night){	_night_cream = night;	}
	public void setNightCream(String night){
		if(night.equals("y") || night.equals("Y") ||	night.equals("yes") || night.equals("YES")){
			setNightCream(true);
		} else if(night.equals("n") || night.equals("N") || night.equals("no") || night.equals("NO")){
			setNightCream(false);
		} else{	
			throw new IllegalArgumentException("invalid night input: must be y for yes or n for no");	
		}
	}
}