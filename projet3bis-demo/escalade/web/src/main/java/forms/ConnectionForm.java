package forms;

import javax.servlet.http.HttpServletRequest;

public class ConnectionForm {
	private String resultat;
	
	public void verifierIdentifiant(HttpServletRequest request) {
		String login= request.getParameter("login");
		String mdp = request.getParameter("mdp");
		
		if (mdp.equals(login+"123")){
			resultat = "vous �tes bien connect�";
		}
		else{
			resultat ="identifiants incorrects";
		}
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
}
