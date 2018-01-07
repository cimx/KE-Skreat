import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Skreat{

	private List<Cleanser> _cleansers = new ArrayList<Cleanser>();
	private List<Freshner> _freshners = new ArrayList<Freshner>();
	private List<Gel> _gels = new ArrayList<Gel>();
	private List<Oil> _oils = new ArrayList<Oil>();
	private List<Moisturizer> _moisturizers = new ArrayList<Moisturizer>();
	private List<Cream> _creams = new ArrayList<Cream>();
	private List<Cream> _night_creams = new ArrayList<Cream>();
	private Customer _customer = new Customer();
	private Treatment _treatment = new Treatment();

	public static void main(String[] args) {	
		Skreat s = new Skreat();
		s.getProductsInfo();	//Products -- read products' files
		s.getCustomerInfo();	//	Customer -- read specialist's input

		s.chooseBaseSeries();
		s.chooseFirstProducts();
		s.changeProducts();

		s.giveTreatment();

	}

	public Treatment getTreatment(){	return _treatment;	}
	public Customer getCustomer(){	return _customer;	}

	public void giveTreatment(){
		System.out.println();
		System.out.println("---RECOMMENDED TREATMENT---");
		System.out.println();
		System.out.println("Base Series: " + getTreatment().getBaseSeries());
		System.out.println();
		System.out.println("Cleanser: " + getTreatment().getCleanser().getName());
		System.out.println("Freshener: " + getTreatment().getFreshner().getName());
		if(getTreatment().getGel() != null){
			System.out.println("Gel: " + getTreatment().getGel().getName());
		}
		if(getTreatment().getOil() != null){
			System.out.println("Oil: " + getTreatment().getOil().getName());
		}
		System.out.println("Moisturizer: " + getTreatment().getMoisturizer().getName());
		if(getTreatment().getNightCream().getName().equals(getTreatment().getDayCream().getName())){
			System.out.println("Cream: " + getTreatment().getDayCream().getName());
		} else{
			System.out.println("Day Cream: " + getTreatment().getDayCream().getName());
			System.out.println("Night Cream: " + getTreatment().getNightCream().getName());
		}
		System.out.println();
	}

	public void changeProducts(){
		if (getTreatment().getBaseSeries().equals("sensitive") || getTreatment().getBaseSeries().equals("synergie")){
			if (getCustomer().getBudget() == 1 && getCustomer().getAge() <= 25){
				Cleanser cheapest_cleanser = _cleansers.get(0);
				int lowest_price = _cleansers.get(0).getPrice();
				for (Cleanser c: _cleansers){
					if(c.getPrice() < lowest_price){
						lowest_price = c.getPrice();
						cheapest_cleanser = c;
					}
				}
				getTreatment().setCleanser(cheapest_cleanser);
			}
			else if(getCustomer().getBudget() == 2){
				Cream new_cream = changeCream(_creams);
				getTreatment().setNightCream(new_cream);
				getTreatment().setDayCream(new_cream);
			}
			else if(getCustomer().getBudget() == 3){
				Cream new_cream = changeCream(_creams);
				getTreatment().setNightCream(new_cream);
				getTreatment().setDayCream(new_cream);
				if(getCustomer().getNightCream())
					getTreatment().setNightCream(changeCream(_night_creams));
			}
			else if(getCustomer().getBudget() == 4){
				getTreatment().setCleanser(changeCleanser());

				if(getCustomer().getFemale() || (!getCustomer().getFemale() && !(getCustomer().getMotivation() < 3 && getCustomer().getSkin().getState() > 1)))
					getTreatment().setOil(changeOil());

				getTreatment().setMoisturizer(changeMoisturizer());
				
				Cream new_cream = changeCream(_creams);
				getTreatment().setNightCream(new_cream);
				getTreatment().setDayCream(new_cream);
				if(getCustomer().getNightCream())
					getTreatment().setNightCream(changeCream(_night_creams));
			}
		}
	}

	public Cleanser changeCleanser(){
		for(Cleanser c: _cleansers){
			if(c.getSeries().equals("explicit"))
				return c;
		}
		return getTreatment().getCleanser();
	}

	public Oil changeOil(){
		if((getCustomer().getSkin().getDry() || getCustomer().getSkin().getRosacea() || getCustomer().getSkin().getSunDamage()) && getCustomer().getMotivation() < 2){
			for(Oil o: _oils){
				if(o.getSeries().equals("explicit"))
					return o;
			}
		}
		return getTreatment().getOil();
	}

	public Moisturizer changeMoisturizer(){
		for (Moisturizer m: _moisturizers) {
			if(m.getSeries().equals(getTreatment().getBaseSeries())){
				if(getCustomer().getSkin().getPimples() >= 2 && getCustomer().getAge() <= 25 && m.getPimples() > 2)					//restrict cream's strength to not too strong
					return m;
			}
		}
		return getTreatment().getMoisturizer();
	}

	public Cream changeCream(List<Cream> creams_list){
		List<Cream> invalid_creams = new ArrayList<Cream>();

		//restrict creams to the ones for the costumer's treatment series or explicit (if budget high+)
		if(getCustomer().getBudget() > 2 && getCustomer().getAge() >= 30){
			for (Cream c: creams_list) {
				if(!c.getSeries().equals("explicit") && !c.getSeries().equals(getTreatment().getBaseSeries())){
					invalid_creams.add(c);
				}
			}
		} else {
			for (Cream c: creams_list) {
				if(!c.getSeries().equals(getTreatment().getBaseSeries())){
					invalid_creams.add(c);
				}
			}
		}
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}
		invalid_creams = new ArrayList<Cream>();

		//restrict creams to the ones for the customer's gender
		for (Cream c: creams_list) {	
			if(getCustomer().getFemale() && !c.getFemale()){
				invalid_creams.add(c);
			} else if(!getCustomer().getFemale() && !c.getMale()){
				invalid_creams.add(c);
			}
		}
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}
		invalid_creams = new ArrayList<Cream>();

		//restrict creams to the ones compatible with the treatment's season
		for (Cream c: creams_list) {	
			if(c.getSeason() != null){
				if(!c.getSeason().startsWith(getTreatment().getSeason())  && !c.getSeason().endsWith(getTreatment().getSeason())){
					invalid_creams.add(c);
				}
			}
		}
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}
		invalid_creams = new ArrayList<Cream>();

		//Restrict cream's strength
		if(getCustomer().getAllergies() || getCustomer().getMedicines()){
			for (Cream c: creams_list) {
				if(c.getStrength() > 2)					//restrict cream's strength to not too strong
					invalid_creams.add(c);
			}
		} else if(getCustomer().getSmoker() || getCustomer().getMotivation() == 3){
			for (Cream c: creams_list) {
				if(c.getStrength() < 3){
					invalid_creams.add(c);
				}
			}
		}
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}
		invalid_creams = new ArrayList<Cream>();
		
		//cream.wrinkle >= customer.skin.wrinkles
		for (Cream c: creams_list) {
			if(c.getWrinkles() < getCustomer().getSkin().getWrinkles()){
				invalid_creams.add(c);
			}
		}
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}
		invalid_creams = new ArrayList<Cream>();

		//pimples 
		if(getCustomer().getSkin().getPimples() <= 2 ){
			for (Cream c: creams_list) {
				if(c.getPimples() < getCustomer().getSkin().getPimples())
					invalid_creams.add(c);
			}
		} 
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}
		invalid_creams = new ArrayList<Cream>();

		//customer.age >= 37 AND customer.skin.state >= 2	RESTRICTS	cream.anti_aging = true
		if(getCustomer().getAge() >= 37 && getCustomer().getSkin().getState() <= 2){
			for (Cream c: creams_list) {
				if(!c.getAntiAging()){
					invalid_creams.add(c);
				}
			}
		} 
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}
		invalid_creams = new ArrayList<Cream>();


		//customer.skin.dry = true RESTRICTS cream.dry = true
		if(getCustomer().getSkin().getDry() ){
			for (Cream c: creams_list ) {
				if(!c.getDrySkin())
					invalid_creams.add(c);
			}
		} 
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}
		invalid_creams = new ArrayList<Cream>();

		//sun damage
		if(getCustomer().getSkin().getSunDamage() ){
			for (Cream c: creams_list) {
				if(!c.getSunDamage())
					invalid_creams.add(c);
			}
		} 
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}
		invalid_creams = new ArrayList<Cream>();

		//outside
		if(getCustomer().getOutside() ){
			for (Cream c: creams_list) {
				if(c.getSpf() <= 0)
					invalid_creams.add(c);
			}
		}
		if(creams_list.size() > invalid_creams.size()){
			creams_list.removeAll(invalid_creams);
		}

		return finalCreamChange(creams_list);
	}

	public Cream finalCreamChange(List<Cream> creams_list){
		if( getCustomer().getMotivation() >= 2 && getCustomer().getBudget() >= 3){
			Cream strongest_cream = creams_list.get(0);
			int highest_strength = creams_list.get(0).getStrength();
			for(Cream c: creams_list){
				if(c.getStrength() < highest_strength){
					strongest_cream = c;
					highest_strength = c.getStrength();
				}
			}
			return strongest_cream;
		}else{
			Cream cheapest_cream = creams_list.get(0);
			int lowest_price = creams_list.get(0).getPrice();
			for(Cream c: creams_list){
				if(c.getPrice() < lowest_price){
					cheapest_cream = c;
					lowest_price = c.getPrice();
				}
			}
			return cheapest_cream;
		} 
	}

	public void chooseBaseSeries(){
		if (getCustomer().getPreviousCare() == null && getCustomer().getMotivation() == 1)
			getTreatment().setBaseSeries("sensitive");
		else if (getCustomer().getAge() <= 22 && getCustomer().getSkin().getState() >= 2)
			getTreatment().setBaseSeries("sensitive");
		else if (getCustomer().getPreviousCare() != null && getCustomer().getPreviousCare().equals("synergie") && getCustomer().getMotivation() == 3 && getCustomer().getBudget() >= 3 && getCustomer().getAge() >= 30)
			getTreatment().setBaseSeries("66days");
	}

	public void chooseFirstProducts(){
		chooseCleanser();
		chooseFreshner();
		if(getCustomer().getAge() > 21 && (getCustomer().getFemale() || (!getCustomer().getFemale() && !(getCustomer().getMotivation() < 3 && getCustomer().getSkin().getState() > 1)))){
			chooseGel();
			chooseOil();
		}
		chooseMoisturizer();
		chooseCream();
	}

	public void chooseCleanser(){
		int lowest_price = Integer.MAX_VALUE;
		Cleanser cheapest_cleanser = _cleansers.get(0);

		for(Cleanser c: _cleansers){
			if(c.getSeries().equals(getTreatment().getBaseSeries())){
				if(c.getPrice() < lowest_price){
					if(getCustomer().getFemale() && c.getFemale() || !getCustomer().getFemale() && c.getMale() ){
						lowest_price = c.getPrice();
						cheapest_cleanser = c;
					}
				}
			}
		}
		getTreatment().setCleanser(cheapest_cleanser);
	}
	public void chooseFreshner(){
		int lowest_price = Integer.MAX_VALUE;
		Freshner cheapest_freshner = _freshners.get(0);

		for(Freshner f: _freshners){
			if(f.getSeries().equals(getTreatment().getBaseSeries())){
				if(f.getPrice() < lowest_price){
					if(getCustomer().getFemale() && f.getFemale() || !getCustomer().getFemale() && f.getMale() ){
						lowest_price = f.getPrice();
						cheapest_freshner = f;
					}
				}
			}
		}
		getTreatment().setFreshner(cheapest_freshner);
	}
	public void chooseGel(){
		int lowest_price = Integer.MAX_VALUE;
		Gel cheapest_gel = _gels.get(0);

		for(Gel g: _gels){
			if(g.getSeries().equals(getTreatment().getBaseSeries())){
				if(g.getPrice() < lowest_price){
					if(getCustomer().getFemale() && g.getFemale() || !getCustomer().getFemale() && g.getMale() ){
						lowest_price = g.getPrice();
						cheapest_gel = g;
					}
				}
			}
		}
		getTreatment().setGel(cheapest_gel);
	}
	public void chooseOil(){
		int lowest_price = Integer.MAX_VALUE;
		Oil cheapest_oils = _oils.get(0);

		for(Oil o: _oils){
			if(o.getSeries().equals(getTreatment().getBaseSeries())){
				if(o.getPrice() < lowest_price){
					if(getCustomer().getFemale() && o.getFemale() || !getCustomer().getFemale() && o.getMale() ){
						lowest_price = o.getPrice();
						cheapest_oils = o;
					}
				}
			}
		}
		getTreatment().setOil(cheapest_oils);
	}
	public void chooseMoisturizer(){
		int lowest_price = Integer.MAX_VALUE;
		Moisturizer cheapest_moisturizer = _moisturizers.get(0);

		for(Moisturizer m: _moisturizers){
			if(m.getSeries().equals(getTreatment().getBaseSeries())){
				if(m.getPrice() < lowest_price){
					if(getCustomer().getFemale() && m.getFemale() || !getCustomer().getFemale() && m.getMale() ){
						lowest_price = m.getPrice();
						cheapest_moisturizer = m;
					}
				}
			}
		}
		getTreatment().setMoisturizer(cheapest_moisturizer);
	}
	public void chooseCream(){
		int lowest_price = Integer.MAX_VALUE;;
		Cream cheapest_cream = _creams.get(0);

		if(getTreatment().getBaseSeries().equals("66days")){
			for(Cream c: _creams){
				if(c.getSeries().equals(getTreatment().getBaseSeries())){
					getTreatment().setDayCream(c);
					break;
				}
			}
			for(Cream c: _night_creams){
				if(c.getSeries().equals(getTreatment().getBaseSeries())){
					getTreatment().setNightCream(c);
					break;
				}
			}
		} else{
			for(Cream c: _creams){
				if(c.getSeries().equals(getTreatment().getBaseSeries())){
					if(c.getPrice() < lowest_price){
						if(getCustomer().getFemale() && c.getFemale() || !getCustomer().getFemale() && c.getMale() ){
							if(c.getTimeOfDay().equals("both")){
								lowest_price = c.getPrice();
								cheapest_cream = c;
							}
						}
					}
				}
			}
			getTreatment().setDayCream(cheapest_cream);
			getTreatment().setNightCream(cheapest_cream);
		}
	}

	public void getProductsInfo(){
		listCleansers();
		listFreshners();
		listGels();
		listOils();
		listMoisturizers();
		listCreams();
	}
	public void listCleansers(){
		String name;
		String series;
		String gender;
		String price;
		String line;

	    try {
	    	Scanner sc = new Scanner(new File("products/cleansers.txt"));
			while(sc.hasNext()){
				line = sc.nextLine();
				name = line;
				line = sc.nextLine();
				series = line;
				line = sc.nextLine();
				gender = line;
				line = sc.nextLine();
				price = line;
				_cleansers.add( new Cleanser(name,series,gender,price));
			}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	}
	public void listFreshners(){
		String name;
		String series;
		String gender;
		String price;
		String line;

	    try {
	    	Scanner sc = new Scanner(new File("products/freshners.txt"));
			while(sc.hasNext()){
				line = sc.nextLine();
				name = line;
				line = sc.nextLine();
				series = line;
				line = sc.nextLine();
				gender = line;
				line = sc.nextLine();
				price = line;
				_freshners.add( new Freshner(name,series,gender,price));
			}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	}
	public void listGels(){
		String name;
		String series;
		String gender;
		String price;
		String line;

	    try {
	    	Scanner sc = new Scanner(new File("products/gels.txt"));
			while(sc.hasNext()){
				line = sc.nextLine();
				name = line;
				line = sc.nextLine();
				series = line;
				line = sc.nextLine();
				gender = line;
				line = sc.nextLine();
				price = line;
				_gels.add( new Gel(name,series,gender,price));
			}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	}
	public void listOils(){
		String name;
		String series;
		String gender;
		String price;
		String line;

	    try {
	    	Scanner sc = new Scanner(new File("products/oils.txt"));
			while(sc.hasNext()){
				line = sc.nextLine();
				name = line;
				line = sc.nextLine();
				series = line;
				line = sc.nextLine();
				gender = line;
				line = sc.nextLine();
				price = line;
				_oils.add( new Oil(name,series,gender,price));
			}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	}
	public void listMoisturizers(){
		String name;
		String series;
		String gender;
		String price;
		String pimples;
		String line;

	    try {
	    	Scanner sc = new Scanner(new File("products/moisturizers.txt"));
			while(sc.hasNext()){
				line = sc.nextLine();
				name = line;
				line = sc.nextLine();
				series = line;
				line = sc.nextLine();
				gender = line;
				line = sc.nextLine();
				price = line;
				line = sc.nextLine();
				pimples = line;
				_moisturizers.add( new Moisturizer(name,series,gender,price,pimples));
			}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	}
	public void listCreams(){
		String name;
		String series;
		String gender;
		String price;
		String spf;
		String pimples;
		String sun_damage;
		String dry_skin;
		String wrinkles;
		String strength;
		String anti_aging;
		String season;
		String time_of_day;
		String min_age;
		String max_age;
		String line;

	    try {
	    	Scanner sc = new Scanner(new File("products/creams.txt"));
			while(sc.hasNext()){
				line = sc.nextLine();
				name = line;
				line = sc.nextLine();
				time_of_day = line;
				line = sc.nextLine();
				series = line;
				line = sc.nextLine();
				gender = line;
				line = sc.nextLine();
				spf = line;
				line = sc.nextLine();
				pimples = line;
				line = sc.nextLine();
				sun_damage = line;
				line = sc.nextLine();
				dry_skin = line;
				line = sc.nextLine();
				wrinkles = line;
				line = sc.nextLine();
				strength = line;
				line = sc.nextLine();
				anti_aging = line;
				line = sc.nextLine();
				season = line;
				line = sc.nextLine();
				min_age = line;
				line = sc.nextLine();
				max_age = line;
				line = sc.nextLine();
				price = line;
				if(time_of_day.equals("day") || time_of_day.equals("both"))
					_creams.add( new Cream(name,series,gender,price,spf,pimples,sun_damage,dry_skin,wrinkles,strength,anti_aging,season,min_age,max_age,time_of_day));
				else
					_night_creams.add( new Cream(name,series,gender,price,spf,pimples,sun_damage,dry_skin,wrinkles,strength,anti_aging,season,min_age,max_age,time_of_day));

			
			}
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	}
	public void getCustomerInfo(){
		Skin _skin = new Skin(); 	
		
		
		Scanner reader = new Scanner(System.in); 
		try{
			System.out.println("Customer's personal information: ");
			System.out.print("Age: ");
			getCustomer().setAge(reader.nextInt());
			System.out.print("Gender (female/male): ");
			getCustomer().setGender(reader.next());
			System.out.print("Smoker? (y/n): ");
			getCustomer().setSmoker(reader.next());
			System.out.print("Spends time outside? (y/n): ");
			getCustomer().setOutside(reader.next());
			System.out.print("Takes medicines? (y/n): ");
			getCustomer().setMedicines(reader.next()); 
			System.out.print("Allergies? (y/n): ");
			getCustomer().setAllergies(reader.next()); 
			System.out.print("Different cream during the night? (y/n): ");
			getCustomer().setNightCream(reader.next());
			System.out.print("previous care? (synergie/sensitive/66days/none): ");
			getCustomer().setPreviousCare(reader.next()); 
			System.out.print("Budget (1-low | 2-medium | 3-high | 4-really high ): ");
			getCustomer().setBudget(reader.nextInt());
			System.out.print("Motivation (1-low | 2-medium | 3-high): ");
			getCustomer().setMotivation(reader.nextInt());
			
			
			//Skin
			System.out.println("\nCustomer's skin information: ");
			
			System.out.print("Sun damage? (y/n): ");
			_skin.setSunDamage(reader.next());
			System.out.print("Dry skin? (y/n): ");
			_skin.setDry(reader.next());
			System.out.print("Rosacea? (y/n): ");
			_skin.setRosacea(reader.next());
			System.out.print("Pimples (0-no | 1-low | 2-medium | 3-high): ");
			_skin.setPimples(reader.nextInt());
			System.out.print("Wrinkles (0-no | 1-low | 2-medium | 3-high): ");
			_skin.setWrinkles(reader.nextInt());
			System.out.print("Overall skin state (1-bad | 2-medium | 3-good): ");
			_skin.setState(reader.nextInt());

			System.out.println();
			System.out.print("Treatment's season? (winter/spring/summmer/autumn) ");
			getTreatment().setSeason(reader.next());
		} catch ( InputMismatchException e){	
			System.out.println("Input not valid");
		}

		reader.close(); 
		getCustomer().setSkin(_skin);
	}
}
