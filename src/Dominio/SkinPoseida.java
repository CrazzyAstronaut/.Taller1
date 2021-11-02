package Dominio;

public class SkinPoseida {
	private Skin skin;
	private CampeonPoseido champPoseido;
	public SkinPoseida(Skin skin, CampeonPoseido champPoseido) {
		this.skin = skin;
		this.champPoseido = champPoseido;
	}
	public Skin getSkin() {
		return skin;
	}
	public void setSkin(Skin skin) {
		this.skin = skin;
	}
	public CampeonPoseido getChampPoseido() {
		return champPoseido;
	}
	public void setChampPoseido(CampeonPoseido champPoseido) {
		this.champPoseido = champPoseido;
	}
	
}
