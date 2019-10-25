package FxControleurs;

import FxVues.AjoutClientVue;

public class AjoutClientCtrl {
	
	private AjoutClientVue vue;
	
	public void setVue(AjoutClientVue V) {
		vue = V;
	}
	
	public void creerClient() {
		
	}

	public void retourAccueil() {
		this.vue.close();
	}

}
