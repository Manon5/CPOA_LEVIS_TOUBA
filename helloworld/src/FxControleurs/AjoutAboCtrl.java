package FxControleurs;

import FxVues.AjoutAboVue;

public class AjoutAboCtrl {
	
	private AjoutAboVue vue;

	public void setVue(AjoutAboVue V) {
		vue = V;
	}
	
	public void creerRevue(){
		System.out.println("ça marche");
	}
	
	public void retourAccueil() {
		this.vue.close();
	}

}
