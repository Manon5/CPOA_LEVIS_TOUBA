package FxControleurs;

import FxVues.AjoutRevueVue;

public class AjoutRevueCtrl {
	
	private AjoutRevueVue vue;

	public void setVue(AjoutRevueVue V) {
		vue = V;
	}
	
	public void creerRevue(){
		System.out.println("ça marche");
	}

	public void retourAccueil() {
		this.vue.close();
	}

}
