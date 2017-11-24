
package ohtu.intjoukkosovellus;

public class IntJoukko {

	public final static int KAPASITEETTI = 5, // aloitustalukon koko
			OLETUSKASVATUS = 5; // luotava uusi taulukko on
	// näin paljon isompi kuin vanha
	private int kasvatuskoko; // Uusi taulukko on tämän verran vanhaa suurempi.
	private int[] luvut; // Joukon luvut säilytetään taulukon alkupäässä.
	private int alkioidenLkm; // Tyhjässä joukossa alkioiden_määrä on nolla.

	public IntJoukko() {
		luvut = new int[KAPASITEETTI];
		alkioidenLkm = 0;
		this.kasvatuskoko = OLETUSKASVATUS;
	}

	public IntJoukko(int kapasiteetti) {
		if (kapasiteetti < 0) {
			return;
		}
		luvut = new int[kapasiteetti];
		alkioidenLkm = 0;
		this.kasvatuskoko = OLETUSKASVATUS;
	}

	public IntJoukko(int kapasiteetti, int kasvatuskoko) {
		if (kapasiteetti < 0 || kasvatuskoko < 0) {
			throw new IndexOutOfBoundsException("Kapasitteetti väärin");// heitin vaan jotain :D
		}
		luvut = new int[kapasiteetti];
		alkioidenLkm = 0;
		this.kasvatuskoko = kasvatuskoko;

	}

	public boolean lisaa(int luku) {
		if (!kuuluukoTaulukkoon(luku)) {
			luvut[alkioidenLkm] = luku;
			alkioidenLkm++;
			if (alkioidenLkm == luvut.length) {
				uusiTaulukko();
			}
			return true;
		}
		return false;
	}
	
	private void uusiTaulukko() {
		int[] taulukkoOld = new int[luvut.length];
		taulukkoOld = luvut;
		kopioiTaulukko(luvut, taulukkoOld);
		luvut = new int[alkioidenLkm + kasvatuskoko];
		kopioiTaulukko(taulukkoOld, luvut);
	}

	public boolean kuuluukoTaulukkoon(int luku) {
		for (int i = 0; i < alkioidenLkm; i++) {
			if (luku == luvut[i]) {
				return true;
			}
		}
		return false;
	}

	public boolean poista(int luku) {
		for (int i = 0; i < alkioidenLkm; i++) {
			if (luku == luvut[i]) {
				luvut[i] = 0;
				asetaTaulukonLoputNolliksi(i);
				return true;
			}
		}
		return false;
	}

	private void asetaTaulukonLoputNolliksi(int i) {
		int apu = 0;
		for (int j = i; j < alkioidenLkm - 1; j++) {
			apu = luvut[j];
			luvut[j] = luvut[j + 1];
			luvut[j + 1] = apu;
		}
		alkioidenLkm--;
	}

	private void kopioiTaulukko(int[] vanha, int[] uusi) {
		for (int i = 0; i < vanha.length; i++) {
			uusi[i] = vanha[i];
		}

	}

	public int alkioidenLkm() {
		return alkioidenLkm;
	}

	@Override
	public String toString() {
		String tuotos = "{";
		if (alkioidenLkm >= 1) {
			for (int i = 0; i < alkioidenLkm - 1; i++) {
				tuotos += luvut[i];
				tuotos += ", ";
			}
			tuotos += luvut[alkioidenLkm - 1];
		}
		tuotos += "}";
		return tuotos;
	}

	public int[] toIntArray() {
		int[] taulu = new int[alkioidenLkm];
		for (int i = 0; i < taulu.length; i++) {
			taulu[i] = luvut[i];
		}
		return taulu;
	}

	public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
		IntJoukko yhdiste = new IntJoukko();
		int[] aTaulu = a.toIntArray();
		int[] bTaulu = b.toIntArray();
		for (int i = 0; i < aTaulu.length; i++) {
			yhdiste.lisaa(aTaulu[i]);
		}
		for (int i = 0; i < bTaulu.length; i++) {
			yhdiste.lisaa(bTaulu[i]);
		}
		return yhdiste;
	}

	public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
		IntJoukko leikkaus = new IntJoukko();
		int[] aTaulu = a.toIntArray();
		int[] bTaulu = b.toIntArray();
		for (int i = 0; i < aTaulu.length; i++) {
			for (int j = 0; j < bTaulu.length; j++) {
				if (aTaulu[i] == bTaulu[j]) {
					leikkaus.lisaa(bTaulu[j]);
				}
			}
		}
		return leikkaus;

	}

	public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
		IntJoukko erotus = new IntJoukko();
		int[] aTaulu = a.toIntArray();
		int[] bTaulu = b.toIntArray();
		for (int i = 0; i < aTaulu.length; i++) {
			erotus.lisaa(aTaulu[i]);
		}
		for (int i = 0; i < bTaulu.length; i++) {
			erotus.poista(i);
		}

		return erotus;
	}

}