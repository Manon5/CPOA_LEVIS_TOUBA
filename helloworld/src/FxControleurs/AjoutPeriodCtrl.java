package FxControleurs;

import FxVues.AjoutPeriodVue;
import FxVues.AjoutRevueVue;

public class AjoutPeriodCtrl {
	
	private AjoutPeriodVue vue;

	public void setVue(AjoutPeriodVue V) {
		vue = V;
	}
	
	public void creerClient() {
		
	}
	

	public void retourAccueil() {
		this.vue.close();
	}
}
