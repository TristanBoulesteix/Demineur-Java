package game.demineur.menu;

public class SettingPopupInfo {
	private String setting, profil, gridSize;

	public SettingPopupInfo() {
	}

	public SettingPopupInfo(String setting, String profil, String gridSize) {
		this.setting = setting;
		this.profil = profil;
		this.gridSize = gridSize;
	}

	@Override
	public String toString() {
		String str;
		if (this.setting != null && this.gridSize != null && this.profil != null) {
			str = "Description de l'objet InfoZDialog";
			str += "Nom : " + this.setting + "\n";
			str += "Age : " + this.profil + "\n";
			str += "Taille : " + this.gridSize + "\n";
		} else {
			str = "Aucune information !";
		}
		return str;
	}
}
